package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class UploadData extends AbstractEasyCard {
    public final static String ID = makeID("UploadData");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public UploadData() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 2;
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondMagic = baseSecondMagic = 2;
        this.retain = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new DiscardAction(p, p, this.magicNumber, false));
        this.addToBot(new DrawCardAction(this.secondMagic));
    }

    public void upp() {
        upgradeBlock(2);
    }
}