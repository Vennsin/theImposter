package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.WaterwaysOpenPower;


import static theImposter.ImposterMod.makeID;

public class OpenWaterways extends AbstractEasyCard {
    public final static String ID = makeID("OpenWaterways");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public OpenWaterways() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new WaterwaysOpenPower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}