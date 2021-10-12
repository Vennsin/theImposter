package theImposter.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theImposter.TheImposter;

import static theImposter.ImposterMod.makeID;

public class LongTongue extends AbstractEasyRelic {
    public static final String ID = makeID("LongTongue");

    public LongTongue() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public float atDamageModify(float damage, AbstractCard c) {
        return c.hasTag(TheImposter.Enums.KILL) ? damage + 4.0F : damage;
    }
}
