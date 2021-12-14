package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.FixWeatherNodeAction;

import static theImposter.ImposterMod.makeID;

public class FixWeatherNode extends AbstractEasyCard {
    public final static String ID = makeID("FixWeatherNode");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FixWeatherNode() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FixWeatherNodeAction(this.block));

    }

    public void upp() {
        upgradeBlock(1);
    }
}