package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theImposter.ImposterMod.makeID;

public class HolsterWeapons extends AbstractEasyCard {
    public final static String ID = makeID("HolsterWeapons");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public HolsterWeapons() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondMagic = this.baseSecondMagic = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.secondMagic, false), this.secondMagic, true));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}