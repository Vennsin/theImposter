package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import static theImposter.ImposterMod.makeID;

public class Backstab extends AbstractEasyCard {
    public final static String ID = makeID("Backstab");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Backstab() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        this.baseSecondDamage = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        int susStacks = 0;
        if (mo.hasPower(SusPower.POWER_ID))
        {
            susStacks = mo.getPower(SusPower.POWER_ID).amount;
        }
        this.baseDamage += this.baseSecondDamage * susStacks;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void upp() {
        upgradeDamage(3);
    }
}