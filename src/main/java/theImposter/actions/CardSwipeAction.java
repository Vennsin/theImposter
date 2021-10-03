package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CardSwipeAction extends AbstractGameAction {
    private static final com.megacrit.cardcrawl.localization.UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private float startingDuration;
    private AbstractPlayer p;

    public CardSwipeAction(AbstractPlayer p, int numCards) {
        this.amount = numCards;
        this.p = p;
        this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.EXHAUST;
        this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        if (this.duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, true, true);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
                AbstractDungeon.player.gainGold(20);

//                for(int i = 0; i < 20; ++i) {
//                    AbstractDungeon.effectList.add(new GainPennyEffect(this.source, this.target.hb.cX, this.target.hb.cY, this.source.hb.cX, this.source.hb.cY, true));
//                }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
    }
}
