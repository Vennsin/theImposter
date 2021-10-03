package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ResetBreakersAction extends AbstractGameAction {
        private int powerStacks;
        private AbstractMonster targetMonster;

        public ResetBreakersAction(int powerStacks, AbstractMonster m) {
                this.duration = 0.0F;
                this.actionType = ActionType.WAIT;
                this.powerStacks = powerStacks;
                this.targetMonster = m;
        }

        public void update() {
                if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
                        this.addToBot(new ApplyPowerAction(this.targetMonster, AbstractDungeon.player, new WeakPower(this.targetMonster, this.powerStacks, false), this.powerStacks, true));
                } else {
                        this.addToBot(new ApplyPowerAction(this.targetMonster, AbstractDungeon.player, new VulnerablePower(this.targetMonster, this.powerStacks, false), this.powerStacks, true));
                }

                this.isDone = true;
        }
}
