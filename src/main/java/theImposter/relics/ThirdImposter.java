package theImposter.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnLoseHpRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.LizardTail;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import theImposter.TheImposter;
import theImposter.powers.AnotherImposterPower;

import static theImposter.ImposterMod.makeID;

public class ThirdImposter extends AbstractEasyRelic implements BetterOnLoseHpRelic {
    public static final String ID = makeID("ThirdImposter");

    public ThirdImposter() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
        this.counter = 1;
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(SecondImposter.ID);
    }

    public void obtain() {
        AbstractRelic rel;
        boolean addCharge = false;

        if (AbstractDungeon.player.hasRelic(SecondImposter.ID)) {
            for(int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (((AbstractRelic)AbstractDungeon.player.relics.get(i)).relicId.equals(SecondImposter.ID)) {
                    rel = (AbstractRelic)AbstractDungeon.player.relics.get(i);
                    if (!rel.usedUp)
                    {
                        addCharge = true;
                    }
                    this.instantObtain(AbstractDungeon.player, i, true);
                    if (addCharge)
                    {
                        this.counter++;
                    }
                    break;
                }
            }
        } else {
            super.obtain();
        }

    }

    public void setCounter(int setCounter) {
        if (setCounter <= 0) {
            this.usedUp();
            this.counter = -2;
            this.description = this.DESCRIPTIONS[2];
        }
        else
        {
            this.description = this.DESCRIPTIONS[1];
        }
    }

//    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
//        AbstractPlayer p = AbstractDungeon.player;
//
//        if (this.counter <= 0) {
//            return true;
//        }
//        else {
//            this.flash();
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
//            --this.counter;
//            if (this.counter == 0) {
//                this.setCounter(0);
//            }
////            this.setCounter(this.counter);
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
                if (hasMarkOfBloom) {
                    p.currentHealth = AbstractDungeon.player.maxHealth;
                } else {
                    p.heal(AbstractDungeon.player.maxHealth, true);
                }

                this.addToTop(new ApplyPowerAction(p, p, new AnotherImposterPower(p)));
                this.addToTop(new RemoveAllPowersAction(AbstractDungeon.player, false));
//                this.addToTop(new ApplyPowerAction(p, p, new AnotherImposterPower(p)));

                --this.counter;
                if (this.counter == 0) {
                    this.setCounter(0);
                }
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
//        import com.megacrit.cardcrawl.relics.AbstractRelic;
//        import theImposter.TheImposter;
//        import theImposter.powers.AnotherImposterPower;
//
//        import static theImposter.ImposterMod.makeID;
//
//public class ThirdImposter extends AbstractEasyRelic implements OnPlayerDeathRelic {
//    public static final String ID = makeID("ThirdImposter");
//
//    public ThirdImposter() {
//        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
//        this.counter = 1;
//    }
//
//    public boolean canSpawn() {
//        return AbstractDungeon.player.hasRelic(SecondImposter.ID);
//    }
//
//    public void obtain() {
//        AbstractRelic rel;
//        boolean addCharge = false;
//
//        if (AbstractDungeon.player.hasRelic(SecondImposter.ID)) {
//            for(int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
//                if (((AbstractRelic)AbstractDungeon.player.relics.get(i)).relicId.equals(SecondImposter.ID)) {
//                    rel = (AbstractRelic)AbstractDungeon.player.relics.get(i);
//                    if (!rel.usedUp)
//                    {
//                        addCharge = true;
//                    }
//                    this.instantObtain(AbstractDungeon.player, i, true);
//                    if (addCharge)
//                    {
//                        this.counter++;
//                    }
//                    break;
//                }
//            }
//        } else {
//            super.obtain();
//        }
//
//    }
//
//    public void setCounter(int setCounter) {
//        if (setCounter <= 0) {
//            this.usedUp();
//            this.counter = -2;
//        }
//    }
//
//    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
//        AbstractPlayer p = AbstractDungeon.player;
//
//        if (this.counter <= 0) {
//            return true;
//        }
//        else {
//            this.flash();
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
//            --this.counter;
//            if (this.counter == 0) {
//                this.setCounter(0);
//            }
////            this.setCounter(this.counter);
//            return false;
//        }
//    }
//}
