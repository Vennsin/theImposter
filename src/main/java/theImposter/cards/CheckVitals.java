package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import java.lang.Math;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class CheckVitals extends AbstractEasyCard {
    public final static String ID = makeID("CheckVitals");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CheckVitals() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        while(var3.hasNext()) {
//            AbstractMonster mo = (AbstractMonster)var3.next();
//            this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
//        }

        float mHalfHP = (float)(m.maxHealth) / 2;
        float mCurrentHP = (float)(m.currentHealth);

        if (Math.abs(mCurrentHP - mHalfHP) < .1)
        {
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        else if (mCurrentHP > mHalfHP)
        {
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        else if (mCurrentHP < mHalfHP)
        {
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}