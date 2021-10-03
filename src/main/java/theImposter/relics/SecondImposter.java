package theImposter.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnPlayerDeathRelic;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theImposter.TheImposter;

import static theImposter.ImposterMod.makeID;

public class SecondImposter extends AbstractEasyRelic implements OnPlayerDeathRelic {
    public static final String ID = makeID("SecondImposter");

    public SecondImposter() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
        this.counter = 0;
    }

    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
        }
    }

    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
        AbstractPlayer p = AbstractDungeon.player;

        if (this.counter == -2) {
            return true;
        }
        else {
            this.flash();
            p.maxHealth /= 2;

            if (p.hasRelic("Mark of the Bloom")) {
                p.currentHealth = AbstractDungeon.player.maxHealth;
            } else {
                p.heal(AbstractDungeon.player.maxHealth, true);
            }

            this.addToBot(new RemoveAllPowersAction(AbstractDungeon.player, false));

            this.setCounter(-2);
            return false;
        }

//        if (this.counter == -2) {
//            return true;
//        } else {
//            if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
//                this.flash();
//                p.maxHealth /= 2;
//
//                if (p.hasRelic("Mark of the Bloom")) {
//                    p.currentHealth = AbstractDungeon.player.maxHealth;
//                } else {
//                    p.heal(AbstractDungeon.player.maxHealth, true);
//                }
//
//                this.setCounter(-2);
//                return false;
//            }
//            else {
//                if (p.currentBlock > 0) {
//                    if (damageInfo.output - p.currentBlock > p.currentHealth) {
//                        this.flash();
//                        p.maxHealth /= 2;
//                        if (!p.hasPower("TrueAncestors_Blood_Power") && !p.hasRelic("Mark of the Bloom")) {
//                            p.heal(p.maxHealth);
//                            ++this.counter;
//                            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2)));
//                            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2)));
//                            return false;
//                        }
//
//                        return true;
//                    }
//                } else if (damageInfo.output > p.currentHealth) {
//                    this.flash();
//                    p.maxHealth /= 2;
//                    if (!p.hasPower("TrueAncestors_Blood_Power") && !p.hasRelic("Mark of the Bloom")) {
//                        p.heal(p.maxHealth);
//                        ++this.counter;
//                        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2)));
//                        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2)));
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
    }
}
