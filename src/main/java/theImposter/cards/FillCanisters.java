package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class FillCanisters extends AbstractEasyCard {
    public final static String ID = makeID("FillCanisters");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FillCanisters() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 3;
        this.cardsToPreview = new FuelCanister();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
    }

    public void upp() {
        upgradeBlock(2);
        this.cardsToPreview.upgrade();
        uDesc();
    }
}