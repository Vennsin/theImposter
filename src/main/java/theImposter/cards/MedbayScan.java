package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.VentAction;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class MedbayScan extends AbstractEasyCard {
    public final static String ID = makeID("MedbayScan");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public MedbayScan() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 4;
        this.secondMagic = this.baseSecondMagic = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HealAction(p, p, this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.secondMagic), this.secondMagic));
        if (upgraded)
        {
            this.addToBot(new VentAction());
        }
    }

    public void upp() {
        uDesc();
    }
}