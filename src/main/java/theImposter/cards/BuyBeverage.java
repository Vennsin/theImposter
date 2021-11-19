package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import theImposter.actions.EasyModalChoiceAction;

import java.util.ArrayList;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.*;

public class BuyBeverage extends AbstractEasyCard {
    public final static String ID = makeID("BuyBeverage");
    // intellij stuff skill, self, uncommon, , , , , ,

    public BuyBeverage() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 20;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            p.loseGold(this.magicNumber);
        }

        AbstractPotion pot1 = AbstractDungeon.returnRandomPotion(true);
        AbstractPotion pot2 = AbstractDungeon.returnRandomPotion(true);
        AbstractPotion pot3 = AbstractDungeon.returnRandomPotion(true);

        ArrayList<AbstractCard> potionList = new ArrayList<>();
        potionList.add(new EasyModalChoiceCard("", "Obtain  " + pot1.name + ".", () -> atb(new ObtainPotionAction(pot1))));
        potionList.add(new EasyModalChoiceCard("", "Obtain " + pot2.name + ".", () -> atb(new ObtainPotionAction(pot2))));
        potionList.add(new EasyModalChoiceCard("", "Obtain  " + pot3.name + ".", () -> atb(new ObtainPotionAction(pot3))));
        atb(new EasyModalChoiceAction(potionList));
    }

    public void upp() {
        uDesc();
    }
}