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
        baseDamage = 3;
        this.baseBlock = 2;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SortSamplesAction(p, m, this.damage, this.block, this.magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(1);
    }
}