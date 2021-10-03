package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class StartReactorAction extends AbstractGameAction {
    private int powerStacks;
    private AbstractMonster targetMonster;

    public StartReactorAction(int powerStacks, AbstractMonster m) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.powerStacks = powerStacks;
        this.targetMonster = m;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.powerStacks), this.powerStacks));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, this.powerStacks), this.powerStacks));
        } else {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.powerStacks), this.powerStacks));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseDexterityPower(AbstractDungeon.player, this.powerStacks), this.powerStacks));
        }

        this.isDone = true;
    }
}
