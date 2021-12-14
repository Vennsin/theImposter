package theImposter.relics;

import com.megacrit.cardcrawl.actions.GameActionManager;
import theImposter.TheImposter;
import theImposter.actions.DUMStickyNoteAction;

import static theImposter.ImposterMod.makeID;

public class DUMStickyNote extends AbstractEasyRelic {
    public static final String ID = makeID("DUMStickyNote");

    public DUMStickyNote() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void atTurnStartPostDraw() {
        if (GameActionManager.turn < 2) {
            this.addToBot(new DUMStickyNoteAction());
        }
    }
}
