package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.GasLightingPower;

import static theImposter.ImposterMod.makeID;

public class GasLighting extends AbstractEasyCard {
    public final static String ID = makeID("GasLighting");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public GasLighting() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new ApplyPowerAction(p, p, new GasLightingPower(p, p, 1), 1));
        this.addToBot(new ApplyPowerAction(p, p, new GasLightingPower(p, p, 1), 1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}