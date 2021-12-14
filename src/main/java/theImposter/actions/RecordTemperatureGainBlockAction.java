package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import static theImposter.util.Wiz.atb;

public class RecordTemperatureGainBlockAction extends AbstractGameAction {
    private AbstractPlayer player;

    public RecordTemperatureGainBlockAction() {
//        this.actionType = ActionType.CARD_MANIPULATION;
//        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
    }

    public void update() {
        int blck = 0;
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(SusPower.POWER_ID)) {
                blck += mo.getPower(SusPower.POWER_ID).amount;
            }
        }
        atb(new GainBlockAction(player, AbstractDungeon.player, blck));

        this.isDone = true;
    }

}
