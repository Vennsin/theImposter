package theImposter.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.powers.SusPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class ToyUFO extends AbstractEasyRelic {
    public static final String ID = makeID("ToyUFO");

    public ToyUFO() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheImposter.Enums.IMPOSTER_COLOR);
    }

    public void atBattleStart() {
        this.flash();
        Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var1.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var1.next();
            this.addToBot(new RelicAboveCreatureAction(mo, this));
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new SusPower(mo, AbstractDungeon.player, 3), 3));
        }

    }
}
