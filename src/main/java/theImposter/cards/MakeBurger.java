package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;

import static theImposter.ImposterMod.makeID;

public class MakeBurger extends AbstractEasyCard {
    public final static String ID = makeID("MakeBurger");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public MakeBurger() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}