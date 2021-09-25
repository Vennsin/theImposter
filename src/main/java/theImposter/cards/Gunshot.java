package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class Gunshot extends AbstractEasyCard {
    public final static String ID = makeID("Gunshot");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Gunshot() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (m.hasPower("Vulnerable")) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}