package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.JamCommunications13Power;
import theImposter.powers.JamCommunications7Power;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.VotePlayerPower;

import java.util.Iterator;

public class CheckVotesAction extends AbstractGameAction {

    public CheckVotesAction() {

    }

    public void update() {
//        int totalVotes = 0;
//        if (AbstractDungeon.player.hasPower("impostermod:VotesPlayer"))
//        {
//            totalVotes += AbstractDungeon.player.getPower("impostermod:VotesPlayer").amount;
//        }
//
//        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        while(monsterIterator.hasNext()) {
//            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
//            if (mo.hasPower("impostermod:VotesEnemy")) {
//                totalVotes += mo.getPower("impostermod:VotesEnemy").amount;
//            }
//        }

        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && GetTotalVotes() >= GetVoteTriggerAmount())
        {
//            this.flashWithoutSound();
            this.addToBot(new TriggerVotesAction());
        }

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

    public int GetVoteTriggerAmount()
    {
        if (AbstractDungeon.player.hasPower(JamCommunications7Power.POWER_ID))
        {
            return 7;
        }
        else if (AbstractDungeon.player.hasPower(JamCommunications13Power.POWER_ID))
        {
            return 13;
        }

        return 10;
    }
}
