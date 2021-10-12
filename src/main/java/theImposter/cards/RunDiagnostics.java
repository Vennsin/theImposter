package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theImposter.TheImposter;
import theImposter.actions.VentAction;

import static theImposter.ImposterMod.makeID;

public class RunDiagnostics extends AbstractEasyCard {
    public final static String ID = makeID("RunDiagnostics");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public RunDiagnostics() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
        if (upgraded)
        {
            this.addToBot(new VentAction());
        }
    }

    public void upp() {
        uDesc();
        tags.add(TheImposter.Enums.VENT);
    }
}