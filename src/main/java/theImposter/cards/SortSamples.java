package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.SortSamplesAction;

import static theImposter.ImposterMod.makeID;

public class SortSamples extends AbstractEasyCard {
    public final static String ID = makeID("SortSamples");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SortSamples() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 0;
        this.baseBlock = 0;
        this.magicNumber = this.baseMagicNumber = 3;
        this.secondMagic = this.baseSecondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new SortSamplesAction(p, m, this.magicNumber, this.secondMagic, 1, this.damage));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
    }
}