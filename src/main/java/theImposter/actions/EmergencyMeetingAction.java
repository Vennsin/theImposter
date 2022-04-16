package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;
import theImposter.powers.VoteEnemyPower;

public class EmergencyMeetingAction extends AbstractGameAction {
    private boolean upgraded;

    public EmergencyMeetingAction(boolean upgraded) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.upgraded = upgraded;
    }

    public void update() {
        if (this.upgraded)
        {
//            Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//
//            while(var3.hasNext()) {
//                AbstractMonster mo = (AbstractMonster)var3.next();
//                this.addToBot(new ApplyPowerAction(mo, this.source, new SusPower(mo, this.source, mo.getPower(SusPower.POWER_ID).amount), mo.getPower(SusPower.POWER_ID).amount));
//                this.addToBot(new ApplyPowerAction(mo, this.source, new VoteEnemyPower(mo, this.source, mo.getPower(VoteEnemyPower.POWER_ID).amount), mo.getPower(VoteEnemyPower.POWER_ID).amount));
//            }
            for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (mo.hasPower(SusPower.POWER_ID))
                {
                    this.addToBot(new ApplyPowerAction(mo, this.source, new SusPower(mo, this.source, mo.getPower(SusPower.POWER_ID).amount), mo.getPower(SusPower.POWER_ID).amount));
                }
                if (mo.hasPower(VoteEnemyPower.POWER_ID))
                {
                    this.addToBot(new ApplyPowerAction(mo, this.source, new VoteEnemyPower(mo, this.source, mo.getPower(VoteEnemyPower.POWER_ID).amount), mo.getPower(VoteEnemyPower.POWER_ID).amount));
                }
            }
        }

        this.addToBot(new TriggerVotesAction());

        this.isDone = true;
    }
}
