package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.DepleteOxygenAction;

import static theImposter.ImposterMod.makeID;

public class DepleteOxygen extends AbstractEasyCard {
    public final static String ID = makeID("DepleteOxygen");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DepleteOxygen() {
        super(ID, -1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DepleteOxygenAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upp() {
        upgradeMagicNumber(2);
        uDesc();
    }
}