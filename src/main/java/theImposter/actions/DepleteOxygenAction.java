package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theImposter.powers.OxygenDepletedPower;

public class DepleteOxygenAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private boolean upgraded = false;
    private AbstractPlayer p;
    private int energyOnUse = -1;

    public DepleteOxygenAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.upgraded = upgraded;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount + 1;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse + 1;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (this.upgraded) {
            effect += 2;
        }

        if (effect > 0) {
            this.addToBot(new ApplyPowerAction(this.p, this.p, new OxygenDepletedPower(this.p, effect), effect));
            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
