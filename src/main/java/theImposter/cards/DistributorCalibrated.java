package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.actions.VentAction;
import theImposter.powers.DistributorCalibratedPower;


import static theImposter.ImposterMod.makeID;

public class DistributorCalibrated extends AbstractEasyCard {
    public final static String ID = makeID("DistributorCalibrated");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DistributorCalibrated() {
        super(ID, 3, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        this.isEthereal = true;
        tags.add(TheImposter.Enums.VENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DistributorCalibratedPower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}