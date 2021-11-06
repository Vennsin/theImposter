package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class UnlockSafeAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numCards;

    public UnlockSafeAction(int numCards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numCards = numCards;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (this.player.drawPile.isEmpty()) {
                this.isDone = true;
            } else {
                CardGroup temp = new CardGroup(CardGroupType.UNSPECIFIED);
                Iterator var6 = this.player.drawPile.group.iterator();

                while(var6.hasNext()) {
                    AbstractCard c = (AbstractCard)var6.next();
                    temp.addToTop(c);
                }

                int numCards = this.numCards;
                if (numCards > temp.size())
                {
                    numCards = temp.size();
                }

                CardGroup randomCards = new CardGroup(CardGroupType.UNSPECIFIED);
                for (int i = 0; i < numCards; i++)
                {
                    AbstractCard c = temp.getRandomCard(true);
                    randomCards.addToTop(c);
                    temp.removeCard(c);
                }

                randomCards.sortAlphabetically(true);
                randomCards.sortByRarityPlusStatusCardType(false);
                AbstractDungeon.gridSelectScreen.open(randomCards, 1, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    AbstractCard c = (AbstractCard)var1.next();
//                    c.exhaust = true;
                    AbstractDungeon.player.drawPile.group.remove(c);
                    AbstractDungeon.getCurrRoom().souls.remove(c);
                    this.addToBot(new NewQueueCardAction(c, true, false, true));

//                    for(int i = 0; i < this.playAmt - 1; ++i) {
//                        AbstractCard tmp = c.makeStatEquivalentCopy();
//                        tmp.purgeOnUse = true;
//                        this.addToBot(new NewQueueCardAction(tmp, true, false, true));
//                    }
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("WishAction").TEXT;
    }
}
