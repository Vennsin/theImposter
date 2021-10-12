package theImposter.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.powers.VoteEnemyPower;

import static theImposter.ImposterMod.makeID;

public class BrainSlug extends AbstractEasyRelic {
    public static final String ID = makeID("BrainSlug");

    public BrainSlug() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (m.hasPower(VoteEnemyPower.POWER_ID)) {
            int amount = m.getPower(VoteEnemyPower.POWER_ID).amount;
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(m, this));
                this.addToBot(new ApplyPowerToRandomEnemyAction(AbstractDungeon.player, new VoteEnemyPower((AbstractCreature)null, AbstractDungeon.player, amount), amount, false));
            }
        }

    }
}
