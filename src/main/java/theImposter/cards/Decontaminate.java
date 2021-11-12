package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.actions.VentAction;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class Decontaminate extends AbstractEasyCard {
    public final static String ID = makeID("Decontaminate");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Decontaminate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
        tags.add(TheImposter.Enums.VENT);
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

        this.addToBot(new VentAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}