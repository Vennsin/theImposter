package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theImposter.util.Wiz.atb;

public class SkewerAction extends AbstractGameAction {
    private AbstractMonster m;
    private int damage;
    private DamageInfo.DamageType damageTypeForTurn;

    public SkewerAction(AbstractMonster monster, int damage, DamageInfo.DamageType damageTypeForTurn) {
        this.m = monster;
        this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;
    }

    public void update() {
        if (m.hasPower(WeakPower.POWER_ID)) {
            for (int i = 0; i < m.getPower(WeakPower.POWER_ID).amount; i++) {
                atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }

        isDone = true;
    }
}
