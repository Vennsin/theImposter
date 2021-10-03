package theImposter.cards;

import com.megacrit.cardcrawl.actions.unique.GainEnergyIfDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class AlignEngineOutput extends AbstractEasyCard {
    public final static String ID = makeID("AlignEngineOutput");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public AlignEngineOutput() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyIfDiscardAction(this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}