package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EmptyChuteAction extends AbstractGameAction {
    private static final com.megacrit.cardcrawl.localization.UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private float startingDuration;
    private AbstractPlayer p;
    private int blockPerCard;

    public EmptyChuteAction(AbstractPlayer p, int numCards, int blockPerCard) {
        this.amount = numCards;
        this.p = p;
        this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.EXHAUST;
        this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.blockPerCard = blockPerCard;
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
                this.p.addBlock(this.blockPerCard);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
    }
}
