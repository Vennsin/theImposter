package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.WaterPlantsAction;

import static theImposter.ImposterMod.makeID;

public class WaterPlants extends AbstractEasyCard {
    public final static String ID = makeID("WaterPlants");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public WaterPlants() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new WaterPlantsAction(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}