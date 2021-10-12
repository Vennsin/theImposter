package theImposter.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theImposter.TheImposter;

import static theImposter.ImposterMod.makeID;

public class ClaustrophilicHamster extends AbstractEasyRelic {
    public static final String ID = makeID("ClaustrophilicHamster");

    public ClaustrophilicHamster() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void atTurnStart() {
        this.grayscale = false;
    }

    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.hasTag(TheImposter.Enums.VENT)) {
            this.flash();
            if (!this.grayscale) {
                this.grayscale = true;
                this.addToBot(new GainEnergyAction(1));
            }
        }
    }
}
