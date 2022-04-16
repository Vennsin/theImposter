package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.WeakPower;
import theImposter.powers.SusPower;
import theImposter.powers.VotePlayerPower;

public class NeckSnap extends AbstractEasyCard {
    public final static String ID = makeID("NeckSnap");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public NeckSnap() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
//        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (m.hasPower(SusPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, m.getPower(SusPower.POWER_ID).amount, false), m.getPower(SusPower.POWER_ID).amount));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}