package theImposter.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class StoreArtifactsAction extends AbstractGameAction {

    public StoreArtifactsAction() {

    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.rarity == AbstractCard.CardRarity.RARE)
            {
                c.cost = 0;
                c.costForTurn = 0;
                c.isCostModified = true;
                c.superFlash(Color.GOLD.cpy());
            }
        }

        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.COMMON);

        isDone = true;
    }
}
