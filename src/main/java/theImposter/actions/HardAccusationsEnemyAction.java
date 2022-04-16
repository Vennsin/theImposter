//package theImposter.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import theImposter.powers.*;
//
//import java.util.Iterator;
//
//public class HardAccusationsEnemyAction extends AbstractGameAction {
//
//    public HardAccusationsEnemyAction() {
//
//    }
//
//    public void update() {
//        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
//            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new VoteEnemyPower(mo, AbstractDungeon.player, 2), 2));
//            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new VoteBuffPower(mo, AbstractDungeon.player, 2), 2));
//            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new LoseVoteBuffPower(mo, AbstractDungeon.player, 2), 2));
//        }
//
//        isDone = true;
//    }
//}
