package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;
import theImposter.powers.VotePlayerPower;

import java.util.Iterator;

public class CleanKillAction extends AbstractGameAction {
    private AbstractMonster m;
    private int damage;
    private int optionChosen;
    private DamageInfo.DamageType damageTypeForTurn;

//    option1 = apply 2 Sus, option2 = remove 1 of your Votes
    public CleanKillAction(AbstractMonster monster, int damage, int optionChosen, DamageInfo.DamageType damageTypeForTurn) {
        this.m = monster;
        this.damage = damage;
        this.optionChosen = optionChosen;
        this.damageTypeForTurn = damageTypeForTurn;
    }

    public void update() {
        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        if (this.optionChosen == 1) {
            this.addToBot(new ApplyPowerAction(this.m, AbstractDungeon.player, new SusPower(this.m, AbstractDungeon.player, 2), 2));
        }
        else {
            if (AbstractDungeon.player.hasPower(VotePlayerPower.POWER_ID)) {
                this.addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, VotePlayerPower.POWER_ID, 1));
            }
        }

        isDone = true;
    }
}
