package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.FixShowerAction;

import static theImposter.ImposterMod.makeID;

public class FixShower extends AbstractEasyCard {
    public final static String ID = makeID("FixShower");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FixShower() {
        super(ID, 4, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FixShowerAction());
    }

    public void upp() {
        upgradeBaseCost(3);
    }
}