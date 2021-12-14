package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.SortRecordsAction;

import static theImposter.ImposterMod.makeID;

public class SortRecords extends AbstractEasyCard {
    public final static String ID = makeID("SortRecords");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SortRecords() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isEthereal = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SortRecordsAction());
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}