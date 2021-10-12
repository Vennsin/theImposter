package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.VotePlayerPower;

import java.util.Iterator;
import java.util.Vector;

public class VentAction extends AbstractGameAction {

    public VentAction() {

    }

    public void update() {
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

        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, numUniques));
        this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(numUniques, true), DamageType.THORNS, AttackEffect.POISON));
        this.addToBot(new RemoveDebuffsAction(AbstractDungeon.player));

        isDone = true;
    }
}
