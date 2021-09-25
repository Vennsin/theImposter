package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Jab extends AbstractEasyCard {
    public final static String ID = makeID("Jab");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Jab() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (m.hasPower("impostermod:Sus")) {
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, m.getPower("impostermod:Sus").amount, false), m.getPower("impostermod:Sus").amount));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}