package theImposter.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
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

    public void onUseCard(AbstractCard card, UseCardAction action) {
//        consider just hardcoding this into VentAction
        if (card.hasTag(TheImposter.Enums.VENT)) {
            this.flash();
            if (!this.grayscale) {
                this.grayscale = true;
                this.addToBot(new GainEnergyAction(1));
            }
        }
    }
}
