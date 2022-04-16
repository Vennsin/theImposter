package theImposter.relics;

import theImposter.TheImposter;
import theImposter.actions.SheriffHatAction;

import static theImposter.ImposterMod.makeID;

public class SheriffHat extends AbstractEasyRelic {
    public static final String ID = makeID("SheriffHat");

    public SheriffHat() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void atTurnStartPostDraw() {
        this.flash();
        this.addToBot(new SheriffHatAction());
    }
}
