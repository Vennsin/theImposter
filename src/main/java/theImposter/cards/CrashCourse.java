package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.CrashCoursePower;

import static theImposter.ImposterMod.makeID;

public class CrashCourse extends AbstractEasyCard {
    public final static String ID = makeID("CrashCourse");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public CrashCourse() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CrashCoursePower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(5);
    }
}