package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.CardSwipeAction;

import static theImposter.ImposterMod.makeID;

public class CardSwipe extends AbstractEasyCard {
    public final static String ID = makeID("CardSwipe");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CardSwipe() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new DiscardAction(p, p, 1, false));
        this.addToBot(new CardSwipeAction(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}