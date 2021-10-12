package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SabotageLightsPower;

import static theImposter.ImposterMod.makeID;

public class SabotageLights extends AbstractEasyCard {
    public final static String ID = makeID("SabotageLights");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public SabotageLights() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SabotageLightsPower(p, this.magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}