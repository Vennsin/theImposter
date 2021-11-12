package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class InsertKeys extends AbstractEasyCard {
    public final static String ID = makeID("InsertKeys");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public InsertKeys() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.isInnate = true;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(VotePlayerPower.POWER_ID)) {
            if (p.getPower(VotePlayerPower.POWER_ID).amount <= this.magicNumber) {
                this.addToBot(new RemoveSpecificPowerAction(p, p, AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID)));
            } else {
                this.addToBot(new ReducePowerAction(p, p, VotePlayerPower.POWER_ID, this.magicNumber));
            }
        }
    }

    public void upp() {
        this.selfRetain = true;
        uDesc();
    }
}