package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class EmptyGarbage extends AbstractEasyCard {
    public final static String ID = makeID("EmptyGarbage");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public EmptyGarbage() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 22;
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondMagic = this.baseSecondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
        this.addToBot(new DiscardAction(p, p, this.magicNumber, false));
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.secondMagic), this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}