//package theImposter.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import theImposter.powers.ReactorMeltdownBuffPower;
//import theImposter.powers.ReactorMeltdownDamagePower;
//
//public class ReactorMeltdownAction extends AbstractGameAction {
//    private int optionChosen;
//    private int amt;
//
//    public ReactorMeltdownAction(int optionChosen, int amt) {
//        this.optionChosen = optionChosen;
//        this.amt = amt;
//    }
//
//    public void update() {
//        if (optionChosen == 1) {
////            Str/Dex from Votes are doubled
//            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ReactorMeltdownBuffPower(AbstractDungeon.player, amt), amt));
//        } else {
////            Vote dmg is doubled
//            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ReactorMeltdownDamagePower(AbstractDungeon.player, amt), amt));
//        }
//
//        isDone = true;
//    }
//}
