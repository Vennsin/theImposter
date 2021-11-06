package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
//import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theImposter.powers.*;

import java.util.Vector;

public class VentAction extends AbstractGameAction {

    public VentAction() {

    }

    public void update() {
//        int frailStacks = 0, weakStacks = 0,vulnerableStacks = 0;
        if (AbstractDungeon.player.hasPower(FrailPower.POWER_ID))
        {
//            frailStacks = AbstractDungeon.player.getPower(FrailPower.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, FrailPower.POWER_ID));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HardyPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
        }
        if (AbstractDungeon.player.hasPower(WeakPower.POWER_ID))
        {
//            weakStacks = AbstractDungeon.player.getPower(WeakPower.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, WeakPower.POWER_ID));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FitPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
        }
        if (AbstractDungeon.player.hasPower(VulnerablePower.POWER_ID))
        {
//            vulnerableStacks = AbstractDungeon.player.getPower(VulnerablePower.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, VulnerablePower.POWER_ID));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GuardedPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
        }

        int numUniques = 0;
        Vector<String> uniquePowers = new Vector<String>();

        for (AbstractPower p : AbstractDungeon.player.powers)
        {
            uniquePowers.add(p.ID);
        }

        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(!mo.isDeadOrEscaped()) {
                for (AbstractPower p : mo.powers) {
                    if (!uniquePowers.contains(p.ID))
                    {
                        uniquePowers.add(p.ID);
                    }
                }
            }
        }

        if (uniquePowers.contains(VotePlayerPower.POWER_ID) && uniquePowers.contains(VoteEnemyPower.POWER_ID))
        {
            numUniques = uniquePowers.size() - 1;
        }
        else
        {
            numUniques = uniquePowers.size();
        }

//        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, numUniques));
        this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(numUniques, true), DamageType.THORNS, AttackEffect.POISON));
//        this.addToBot(new RemoveDebuffsAction(AbstractDungeon.player));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, LoseVoteBuffPower.POWER_ID));

        isDone = true;
    }
}
