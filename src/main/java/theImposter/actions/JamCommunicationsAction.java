package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theImposter.powers.JamCommunications13Power;
import theImposter.powers.JamCommunications7Power;

public class JamCommunicationsAction extends AbstractGameAction {
    private int optionChosen;

    public JamCommunicationsAction(int optionChosen) {
        this.optionChosen = optionChosen;
    }

    public void update() {
        if (optionChosen == 7) {
            if (AbstractDungeon.player.hasPower(JamCommunications13Power.POWER_ID)) {
                this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(JamCommunications13Power.POWER_ID)));
            }

            if (!AbstractDungeon.player.hasPower(JamCommunications7Power.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new JamCommunications7Power(AbstractDungeon.player)));

//                this.addToBot(new CheckVotesAction());
            }
        } else {
            if (AbstractDungeon.player.hasPower(JamCommunications7Power.POWER_ID)) {
                this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(JamCommunications7Power.POWER_ID)));
            }

            if (!AbstractDungeon.player.hasPower(JamCommunications13Power.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new JamCommunications13Power(AbstractDungeon.player)));
            }
        }

        isDone = true;
    }
}
