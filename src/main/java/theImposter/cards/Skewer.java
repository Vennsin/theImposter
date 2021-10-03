package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class Skewer extends AbstractEasyCard {
    public final static String ID = makeID("Skewer");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Skewer() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower("Weak")) {
//            dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
            for (int i = 0; i < m.getPower("Weak").amount; i++) {
                this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}