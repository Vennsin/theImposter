package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

import static theImposter.util.Wiz.atb;

public class FixWeatherNodeAction extends AbstractGameAction {
    private int blockPerCard;

    public FixWeatherNodeAction(int blockPerCard) {
//        this.actionType = ActionType.CARD_MANIPULATION;
//        this.duration = Settings.ACTION_DUR_MED;
        this.blockPerCard = blockPerCard;
    }

    public void update() {
        int blockAmount = AbstractDungeon.player.drawPile.size() * this.blockPerCard;

        Iterator var2 = AbstractDungeon.player.drawPile.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
//            c.target_x = (float)DISCARD_PILE_X;
//            c.target_y = 0.0F;
            AbstractDungeon.player.discardPile.addToTop(c);
            GameActionManager.incrementDiscard(false);
//            c.discard();
        }

        AbstractDungeon.player.drawPile.group.clear();
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, blockAmount));

        this.isDone = true;
    }
}
