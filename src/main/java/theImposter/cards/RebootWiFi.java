package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static theImposter.ImposterMod.makeID;

public class RebootWiFi extends AbstractEasyCard {
    public final static String ID = makeID("RebootWiFi");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public RebootWiFi() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 2;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}