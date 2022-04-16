package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.Iterator;

public class KillAtReactorEnergyGainAction extends AbstractGameAction {
    private AbstractPlayer p;

    public KillAtReactorEnergyGainAction(AbstractPlayer p) {
        this.p = p;
    }

    public void update() {
        int energyGain = 0;
        Iterator var3 = this.p.hand.group.iterator();

        while(var3.hasNext()) {
            AbstractCard c = (AbstractCard)var3.next();
            if (c.rarity == AbstractCard.CardRarity.RARE || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
                energyGain++;
            }
        }

        this.addToBot(new GainEnergyAction(energyGain));

        this.isDone = true;
    }
}
