package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import static theImposter.ImposterMod.makeID;

public class DressMannequin extends AbstractEasyCard {
    public final static String ID = makeID("DressMannequin");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DressMannequin() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, 2));
        if (m != null && m.getIntentBaseDmg() >= 0) {
            this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.magicNumber), this.magicNumber));
        }
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}