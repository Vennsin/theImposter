package theImposter.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnPlayerDeathRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theImposter.TheImposter;
import theImposter.powers.AnotherImposterPower;

import static theImposter.ImposterMod.makeID;

public class SecondImposter extends AbstractEasyRelic implements OnPlayerDeathRelic {
    public static final String ID = makeID("SecondImposter");

    public SecondImposter() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
        this.counter = 1;
    }

    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
            this.description = this.DESCRIPTIONS[1];
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
//            this.addToBot(new SkipEnemiesTurnAction());
            this.addToBot(new ApplyPowerAction(p, p, new AnotherImposterPower(p)));

            this.setCounter(-2);
            return false;
        }
    }
}
