package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.ResetBreakersAction;
import theImposter.actions.StartReactorAction;

import static theImposter.ImposterMod.makeID;

public class StartReactor extends AbstractEasyCard {
    public final static String ID = makeID("StartReactor");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public StartReactor() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new StartReactorAction(this.magicNumber, m));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}