package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class FuelCanister extends AbstractEasyCard {
    public final static String ID = makeID("FuelCanister");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FuelCanister() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.retain = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}