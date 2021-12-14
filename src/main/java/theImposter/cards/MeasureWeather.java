package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import static theImposter.ImposterMod.makeID;

public class MeasureWeather extends AbstractEasyCard {
    public final static String ID = makeID("MeasureWeather");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public MeasureWeather() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber + AbstractDungeon.actNum - 1), this.magicNumber + AbstractDungeon.actNum - 1));
        this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber + AbstractDungeon.actNum - 1), this.magicNumber + AbstractDungeon.actNum - 1));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber + AbstractDungeon.actNum - 1), this.magicNumber + AbstractDungeon.actNum - 1));
        this.addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.magicNumber + AbstractDungeon.actNum - 1), this.magicNumber + AbstractDungeon.actNum - 1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}