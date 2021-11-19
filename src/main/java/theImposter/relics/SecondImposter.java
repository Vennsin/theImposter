package theImposter.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnLoseHpRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.relics.LizardTail;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import theImposter.TheImposter;
import theImposter.powers.AnotherImposterPower;

import static theImposter.ImposterMod.makeID;

public class SecondImposter extends AbstractEasyRelic implements BetterOnLoseHpRelic {
    public static final String ID = makeID("SecondImposter");

    public SecondImposter() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
        this.counter = 1;
    }

    public void setCounter(int setCounter) {
        if (setCounter <= 0) {
            this.usedUp();
            this.counter = -2;
            this.description = this.DESCRIPTIONS[1];
        }
    }

//    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
//        AbstractPlayer p = AbstractDungeon.player;
//
//        if (this.counter == -2) {
//            return true;
//        }
//        else {
//            this.flash();
//            p.maxHealth /= 2;
//
//            if (p.hasRelic("Mark of the Bloom")) {
//                p.currentHealth = AbstractDungeon.player.maxHealth;
//            } else {
//                p.heal(AbstractDungeon.player.maxHealth, true);
//            }
//
//            this.addToBot(new RemoveAllPowersAction(AbstractDungeon.player, false));
//            this.addToBot(new ApplyPowerAction(p, p, new AnotherImposterPower(p)));
//
//            this.setCounter(-2);
//            return false;
//        }
//    }

//    this uses betterOnLoseHp rather than onPlayerDeath due to interactions with Spire With Friends
    @Override
    public int betterOnLoseHp(DamageInfo damageInfo, int damageAmount) {
        AbstractPlayer p = AbstractDungeon.player;

        boolean hasActiveLizardTail = p.hasRelic(LizardTail.ID) && !p.getRelic(LizardTail.ID).usedUp;
        boolean hasFairyPotion = p.hasPotion(FairyPotion.POTION_ID);
        boolean hasMarkOfBloom = p.hasRelic(MarkOfTheBloom.ID);

        if (hasMarkOfBloom || (!hasActiveLizardTail && !hasFairyPotion && damageAmount >= p.currentHealth)) {
            if (this.usedUp) {
                return damageAmount;
            } else {
                this.flash();
                p.maxHealth /= 2;

                if (hasMarkOfBloom) {
                    p.currentHealth = AbstractDungeon.player.maxHealth;
                } else {
                    p.heal(AbstractDungeon.player.maxHealth, true);
                }

                this.addToTop(new ApplyPowerAction(p, p, new AnotherImposterPower(p)));
                this.addToTop(new RemoveAllPowersAction(AbstractDungeon.player, false));
//                this.addToTop(new ApplyPowerAction(p, p, new AnotherImposterPower(p)));

                this.setCounter(-2);
                return 0;
            }
        }
        return damageAmount;
    }
}

//package theImposter.relics;
//
//        import com.evacipated.cardcrawl.mod.stslib.relics.OnPlayerDeathRelic;
//        import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//        import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
//        import com.megacrit.cardcrawl.cards.DamageInfo;
//        import com.megacrit.cardcrawl.characters.AbstractPlayer;
//        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//        import theImposter.TheImposter;
//        import theImposter.powers.AnotherImposterPower;
//
//        import static theImposter.ImposterMod.makeID;
//
//public class SecondImposter extends AbstractEasyRelic implements OnPlayerDeathRelic {
//    public static final String ID = makeID("SecondImposter");
//
//    public SecondImposter() {
//        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
//        this.counter = 1;
//    }
//
//    public void setCounter(int setCounter) {
//        if (setCounter == -2) {
//            this.usedUp();
//            this.counter = -2;
//            this.description = this.DESCRIPTIONS[1];
//        }
//    }
//
//    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
//        AbstractPlayer p = AbstractDungeon.player;
//
//        if (this.counter == -2) {
//            return true;
//        }
//        else {
//            this.flash();
//            p.maxHealth /= 2;
//
//            if (p.hasRelic("Mark of the Bloom")) {
//                p.currentHealth = AbstractDungeon.player.maxHealth;
//            } else {
//                p.heal(AbstractDungeon.player.maxHealth, true);
//            }
//
//            this.addToBot(new RemoveAllPowersAction(AbstractDungeon.player, false));
//            this.addToBot(new ApplyPowerAction(p, p, new AnotherImposterPower(p)));
//
//            this.setCounter(-2);
//            return false;
//        }
//    }
//}
