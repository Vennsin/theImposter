package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.actions.VentAction;
import theImposter.powers.CleanVentsPower;


import static theImposter.ImposterMod.makeID;

public class CleanVents extends AbstractEasyCard {
    public final static String ID = makeID("CleanVents");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CleanVents() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        tags.add(TheImposter.Enums.VENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CleanVentsPower(p, this.magicNumber, this.upgraded), this.magicNumber));
        this.addToBot(new VentAction());
    }

    public void upp() {
        uDesc();
    }
}