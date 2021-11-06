package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import java.util.Iterator;

public class StackKillAction extends AbstractGameAction {
    private AbstractMonster m;
    private int damage;
    private int magicNumber;
    private DamageInfo.DamageType damageTypeForTurn;

    public StackKillAction(AbstractMonster monster, int damage, int susAmount, DamageInfo.DamageType damageTypeForTurn) {
        this.m = monster;
        this.damage = damage;
        this.magicNumber = susAmount;
        this.damageTypeForTurn = damageTypeForTurn;
    }

    public void update() {
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            if (!m2.isDeadOrEscaped()) {
                this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
        this.addToBot(new ApplyPowerAction(this.m, AbstractDungeon.player, new SusPower(this.m, AbstractDungeon.player, this.magicNumber), this.magicNumber));

        isDone = true;
    }
}
