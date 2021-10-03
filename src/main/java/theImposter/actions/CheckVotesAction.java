//package theImposter.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import theImposter.ImposterMod;
//
//import java.util.Iterator;
//
//public class CheckVotesAction extends AbstractGameAction {
//
//    public CheckVotesAction() {
//
//    }
//
//    public void update() {
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
//
//        if (totalVotes >= 10)
//        {
//            this.addToBot(new TriggerVotesAction());
//        }
//
//        isDone = true;
//    }
//}
