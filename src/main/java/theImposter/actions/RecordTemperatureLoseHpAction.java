package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

public class RecordTemperatureLoseHpAction extends AbstractGameAction {
    private AbstractPlayer player;

    public RecordTemperatureLoseHpAction() {
//        this.actionType = ActionType.CARD_MANIPULATION;
//        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
    }

    public void update() {
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(SusPower.POWER_ID)) {
                addToBot(new LoseHPAction(mo, player, mo.getPower(SusPower.POWER_ID).amount));
            }
        }
        this.isDone = true;
    }

}
