package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.DivertPowerAction;

import static theImposter.ImposterMod.makeID;

public class DivertPower extends AbstractEasyCard {
    public final static String ID = makeID("DivertPower");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DivertPower() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DivertPowerAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}