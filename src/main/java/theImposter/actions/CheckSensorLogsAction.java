package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.VotePlayerPower;

import java.util.Iterator;

public class CheckSensorLogsAction extends AbstractGameAction {
    private boolean upgraded = false;
    private AbstractMonster m;

    public CheckSensorLogsAction(AbstractMonster m, boolean upgraded) {
        this.m = m;
        this.upgraded = upgraded;
    }

    public void update() {
        int numVotes = 0;
        if (this.upgraded)
        {
            numVotes = GetTotalVotes();
        }
        else
        {
            if (m.hasPower(VoteEnemyPower.POWER_ID)) {
                numVotes = m.getPower(VoteEnemyPower.POWER_ID).amount;
            }
        }

        this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, numVotes));

        isDone = true;
    }

    public int GetTotalVotes() {
        int totalVotes = 0;
        if (AbstractDungeon.player.hasPower(VotePlayerPower.POWER_ID))
        {
            totalVotes += AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID).amount;
        }

        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (mo.hasPower(VoteEnemyPower.POWER_ID)) {
                totalVotes += mo.getPower(VoteEnemyPower.POWER_ID).amount;
            }
        }

        return totalVotes;
    }
}
