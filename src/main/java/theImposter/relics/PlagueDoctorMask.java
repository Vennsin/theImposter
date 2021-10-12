package theImposter.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theImposter.TheImposter;
import theImposter.actions.VentAction;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class PlagueDoctorMask extends AbstractEasyRelic {
    public static final String ID = makeID("PlagueDoctorMask");
    private static final int TURN_ACTIVATION = 2;

    public PlagueDoctorMask() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void atBattleStart() {
        this.counter = 0;

        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VotePlayerPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
    }

    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }

        if (this.counter == 2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new VentAction());
            this.counter = -1;
            this.grayscale = true;
        }

    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }
}
