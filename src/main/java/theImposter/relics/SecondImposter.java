package theImposter.relics;

import theImposter.TheImposter;

import static theImposter.ImposterMod.makeID;

public class SecondImposter extends AbstractEasyRelic {
    public static final String ID = makeID("SecondImposter");

    public SecondImposter() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }
}
