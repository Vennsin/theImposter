package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.EasyModalChoiceAction;
import theImposter.powers.HardAccusationsEnemyPower;
import theImposter.powers.HardAccusationsPlayerPower;

import java.util.ArrayList;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.atb;

public class HardAccusations extends AbstractEasyCard {
    public final static String ID = makeID("HardAccusations");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HardAccusations() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new ApplyPowerAction(p, p, new HardAccusationsPower(p, this.magicNumber), this.magicNumber));
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard("", "Every time you play 4 cards in one turn, gain 2 Votes.", () -> atb(new ApplyPowerAction(p, p, new HardAccusationsPlayerPower(p, this.magicNumber), this.magicNumber))));
        easyCardList.add(new EasyModalChoiceCard("", "Every time you play 4 cards in one turn, apply 2 Votes to ALL enemies.", () -> atb(new ApplyPowerAction(p, p, new HardAccusationsEnemyPower(p, this.magicNumber), this.magicNumber))));
        atb(new EasyModalChoiceAction(easyCardList));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}