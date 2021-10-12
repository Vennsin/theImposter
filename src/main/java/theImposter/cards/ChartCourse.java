package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import static theImposter.ImposterMod.makeID;

public class ChartCourse extends AbstractEasyCard {
    public final static String ID = makeID("ChartCourse");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ChartCourse() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseBlock = 4;
        this.magicNumber = this.baseMagicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RemoveAllBlockAction(m, p));
        blck();
        if (this.magicNumber > 0) {
            this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.magicNumber), this.magicNumber));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}