package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import static theImposter.ImposterMod.makeID;

public class FixWiring extends AbstractEasyCard {
    public final static String ID = makeID("FixWiring");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FixWiring() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
        if (upgraded)
        {
            this.addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
        }
    }

    public void upp() {
        uDesc();
    }
}