package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import java.util.Iterator;

public class SheriffHatAction extends AbstractGameAction {
    private AbstractPlayer p;

    public SheriffHatAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

                while(var1.hasNext()) {
                    AbstractMonster m = (AbstractMonster)var1.next();
                    if (!m.isDead && !m.isDying && m.hasPower(SusPower.POWER_ID)) {
                        this.addToBot(new ApplyPowerAction(m, m, new SusPower(m, AbstractDungeon.player, 1), 1));
                    }
                }
            }
            this.isDone = true;
    }
}
