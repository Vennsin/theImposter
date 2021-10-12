package theImposter.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theImposter.TheImposter;

import static theImposter.ImposterMod.makeID;

public class DUMStickyNote extends AbstractEasyRelic {
    public static final String ID = makeID("DUMStickyNote");

    public DUMStickyNote() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void atBattleStart() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cost >= 0) {
                int newCost = AbstractDungeon.cardRandomRng.random(2);
                if (c.cost != newCost) {
                    c.cost = newCost;
                    c.costForTurn = c.cost;
                    c.isCostModified = true;
                }

                c.freeToPlayOnce = false;
            }
        }
    }
}
