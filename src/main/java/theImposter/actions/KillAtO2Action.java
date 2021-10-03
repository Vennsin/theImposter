package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class KillAtO2Action extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;

    public KillAtO2Action() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
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
                    if (c.type == AbstractCard.CardType.POWER) {
                        temp.addToTop(c);
                    }
                }
                if (temp.isEmpty())
                {
                    this.isDone = true;
                }

                temp.sortAlphabetically(true);
                temp.sortByRarityPlusStatusCardType(false);
                AbstractDungeon.gridSelectScreen.open(temp, 1, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    AbstractCard c = (AbstractCard)var1.next();
                    AbstractDungeon.player.drawPile.group.remove(c);
                    AbstractDungeon.getCurrRoom().souls.remove(c);
                    this.addToBot(new NewQueueCardAction(c, true, false, true));
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
