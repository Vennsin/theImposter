package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.ResetBreakersAction;

import static theImposter.ImposterMod.makeID;

public class ResetBreakers extends AbstractEasyCard {
    public final static String ID = makeID("ResetBreakers");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ResetBreakers() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ResetBreakersAction(this.magicNumber, m));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}