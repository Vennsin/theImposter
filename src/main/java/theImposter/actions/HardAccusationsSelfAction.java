//package theImposter.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import theImposter.powers.*;
//
//public class HardAccusationsSelfAction extends AbstractGameAction {
//
//    public HardAccusationsSelfAction() {
//
//    }
//
//    public void update() {
//        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VotePlayerPower(AbstractDungeon.player, AbstractDungeon.player, 2), 2));
//        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VoteBuffPower(AbstractDungeon.player, AbstractDungeon.player, 2), 2));
//        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseVoteBuffPower(AbstractDungeon.player, AbstractDungeon.player, 2), 2));
//
//        isDone = true;
//    }
//}
