package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;

import static theImposter.ImposterMod.makeID;

public class CleanO2Filter extends AbstractEasyCard {
    public final static String ID = makeID("CleanO2Filter");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CleanO2Filter() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 1;
        this.isEthereal = true;
        this.exhaust = true;
        tags.add(TheImposter.Enums.VENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        blck();
        blck();
        blck();
        blck();
        blck();
    }

    public void upp() {
        this.isEthereal = false;
        uDesc();
    }
}