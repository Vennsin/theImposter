package theImposter.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.powers.SusPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class SheriffHat extends AbstractEasyRelic {
    public static final String ID = makeID("SheriffHat");

    public SheriffHat() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var1.hasNext()) {
                AbstractMonster m = (AbstractMonster)var1.next();
                if (!m.isDead && !m.isDying && m.hasPower(SusPower.POWER_ID)) {
                    this.addToBot(new ApplyPowerAction(m, m, new SusPower(m, AbstractDungeon.player, 1), 1));
                }
            }
        }

    }
}
