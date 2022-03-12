package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.VotePlayerPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class SwipeCard extends AbstractEasyCard {
    public final static String ID = makeID("SwipeCard");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SwipeCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(VotePlayerPower.POWER_ID)) {
            if (this.magicNumber >= p.getPower(VotePlayerPower.POWER_ID).amount)
            {
                this.addToBot(new RemoveSpecificPowerAction(p, p, p.getPower(VotePlayerPower.POWER_ID)));
            }
            else
            {
                this.addToBot(new ReducePowerAction(p, p, VotePlayerPower.POWER_ID, this.magicNumber));
            }
        }

        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var1.hasNext()) {
                AbstractMonster mo = (AbstractMonster)var1.next();
                if (!mo.isDead && !mo.isDying) {
                    if (mo.hasPower(VoteEnemyPower.POWER_ID)) {
                        if (this.magicNumber >= mo.getPower(VoteEnemyPower.POWER_ID).amount) {
                            this.addToBot(new RemoveSpecificPowerAction(mo, p, mo.getPower(VoteEnemyPower.POWER_ID)));
                        }
                        else
                        {
                            this.addToBot(new ReducePowerAction(mo, p, VoteEnemyPower.POWER_ID, this.magicNumber));
                        }
                    }
                }
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}