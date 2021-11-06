package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.UnlockSafeAction;

import static theImposter.ImposterMod.makeID;

public class UnlockSafe extends AbstractEasyCard {
    public final static String ID = makeID("UnlockSafe");
    // intellij stuff skill, self, uncommon, , , , , ,

    public UnlockSafe() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new UnlockSafeAction(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}