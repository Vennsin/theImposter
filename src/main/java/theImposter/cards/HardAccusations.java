package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.HardAccusationsPower;

import static theImposter.ImposterMod.makeID;

public class HardAccusations extends AbstractEasyCard {
    public final static String ID = makeID("HardAccusations");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HardAccusations() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HardAccusationsPower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}