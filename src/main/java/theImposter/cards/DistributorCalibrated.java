package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.VentAction;
import theImposter.powers.CleanVentsPower;


import static theImposter.ImposterMod.makeID;

public class DistributorCalibrated extends AbstractEasyCard {
    public final static String ID = makeID("DistributorCalibrated");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DistributorCalibrated() {
        super(ID, 3, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CleanVentsPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new VentAction());
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}