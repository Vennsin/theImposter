package theImposter.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theImposter.TheImposter;
import theImposter.powers.VoteEnemyPower;

import static theImposter.ImposterMod.makeID;

public class TopHat extends AbstractEasyRelic {
    public static final String ID = makeID("TopHat");

    public TopHat() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void atBattleStart() {
        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (m != null) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(m, this));
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VoteEnemyPower(m, AbstractDungeon.player, 2), 2));
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, -2), -2));
        }
    }
}
