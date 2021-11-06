package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.actions.CheckSecurityFollowUpAction;

import static theImposter.ImposterMod.makeID;

public class CheckSecurity extends AbstractEasyCard {
    public final static String ID = makeID("CheckSecurity");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CheckSecurity() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 3;
        this.magicNumber = this.baseMagicNumber = 1;
        tags.add(TheImposter.Enums.VENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new DrawCardAction(this.magicNumber, new CheckSecurityFollowUpAction()));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}