package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import static theImposter.ImposterMod.makeID;

public class Backstab extends AbstractEasyCard {
    public final static String ID = makeID("Backstab");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Backstab() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
//        try using secondDamage here
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(SusPower.POWER_ID)) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage + m.getPower(SusPower.POWER_ID).amount * this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}