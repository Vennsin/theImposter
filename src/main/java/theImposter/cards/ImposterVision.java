package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.ImposterVisionPower;

import static theImposter.ImposterMod.makeID;

public class ImposterVision extends AbstractEasyCard {
    public final static String ID = makeID("ImposterVision");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ImposterVision() {
        super(ID, 3, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.player.hasPower(ImposterVisionPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new ImposterVisionPower(p)));
        }
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}