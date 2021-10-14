package theImposter.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theImposter.TheImposter;

import static theImposter.ImposterMod.makeID;

public class DUMStickyNote extends AbstractEasyRelic {
    public static final String ID = makeID("DUMStickyNote");
    private static final int TURN_ACTIVATION = 2;

    public DUMStickyNote() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

//    public void atTurnStart() {
//        if (!this.grayscale) {
//            ++this.counter;
//        }
//        if (this.counter == TURN_ACTIVATION) {
//            this.counter = -1;
//            this.grayscale = true;
//        }
////        if (this.counter == TURN_ACTIVATION) {
////            this.flash();
////
////            for (AbstractCard c : AbstractDungeon.player.hand.group) {
////                if (c.cost >= 0) {
////                    int newCost = AbstractDungeon.cardRandomRng.random(2);
////                    if (c.cost != newCost) {
////                        c.cost = newCost;
////                        c.costForTurn = c.cost;
////                        c.isCostModified = true;
////                    }
////
////                    c.freeToPlayOnce = false;
////                }
////            }
////
////            this.counter = -1;
////            this.grayscale = true;
////        }
//    }

    public void atTurnStartPostDraw() {
        if (!this.grayscale) {
            ++this.counter;
        }
        if (this.counter == TURN_ACTIVATION) {
            this.counter = -1;
            this.grayscale = true;
        }
    }

    public void onCardDraw(AbstractCard card) {
        if (!this.grayscale) {
            if (card.cost >= 0) {
                int newCost = AbstractDungeon.cardRandomRng.random(2);
                if (card.cost != newCost) {
                    card.cost = newCost;
                    card.costForTurn = card.cost;
                    card.isCostModified = true;
                }

                card.freeToPlayOnce = false;
            }
        }
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }

//    public void atBattleStart() {
//        for (AbstractCard c : AbstractDungeon.player.hand.group) {
//            if (c.cost >= 0) {
//                int newCost = AbstractDungeon.cardRandomRng.random(2);
//                if (c.cost != newCost) {
//                    c.cost = newCost;
//                    c.costForTurn = c.cost;
//                    c.isCostModified = true;
//                }
//
//                c.freeToPlayOnce = false;
//            }
//        }
//    }
}
