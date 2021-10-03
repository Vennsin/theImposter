package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class CalibrateDistributor extends AbstractEasyCard {
    public final static String ID = makeID("CalibrateDistributor");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CalibrateDistributor() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new CalibrateAgain();
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), 1, true, true));
    }

    public void upp() {
        this.cardsToPreview.upgrade();
        uDesc();
    }
}