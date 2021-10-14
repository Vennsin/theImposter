package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.actions.VentAction;
import theImposter.powers.CleanVentsPlusPower;
import theImposter.powers.CleanVentsPower;
import theImposter.powers.VotePlayerPower;


import static theImposter.ImposterMod.makeID;

public class CleanVents extends AbstractEasyCard {
    public final static String ID = makeID("CleanVents");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CleanVents() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        tags.add(TheImposter.Enums.VENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (upgraded) {
            if (p.hasPower(CleanVentsPower.POWER_ID)) {
                this.addToBot(new RemoveSpecificPowerAction(p, p, p.getPower(CleanVentsPower.POWER_ID)));
            }

            if (!p.hasPower(CleanVentsPlusPower.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(p, p, new CleanVentsPlusPower(p, this.magicNumber), this.magicNumber));
            }
        }
        else{
            if (!p.hasPower(CleanVentsPlusPower.POWER_ID) && !p.hasPower(CleanVentsPower.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(p, p, new CleanVentsPower(p, this.magicNumber), this.magicNumber));
            }
        }

        this.addToBot(new VentAction());
    }

    public void upp() {
        uDesc();
    }
}