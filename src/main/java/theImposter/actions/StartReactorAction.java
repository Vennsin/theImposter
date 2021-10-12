//package theImposter.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;;
//import com.megacrit.cardcrawl.actions.common.DiscardAction;
//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
//import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.UIStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.*;
//
//public class StartReactorAction extends AbstractGameAction {
//    private int powerStacks;
//    private AbstractMonster targetMonster;
//
//    public StartReactorAction(int block) {
//        this.duration = 0.0F;
//        this.actionType = ActionType.WAIT;
//        this.powerStacks = powerStacks;
//        this.targetMonster = m;
//    }
//
//    public void update() {
//        this.addToBot(new DrawCardAction(1));
//        this.addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));
//
//        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 1));
//
//        this.isDone = true;
//    }
//}
