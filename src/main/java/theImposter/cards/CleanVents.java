package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.VentAction;
import theImposter.powers.CleanVentsPower;


import static theImposter.ImposterMod.makeID;

public class CleanVents extends AbstractEasyCard {
    public final static String ID = makeID("CleanVents");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CleanVents() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(CleanVentsPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new CleanVentsPower(p, this.magicNumber), this.magicNumber));
        }

        this.addToBot(new VentAction());
        if (this.upgraded)
        {
            this.addToBot(new VentAction());
        }
    }

    public void upp() {
        uDesc();
    }
}