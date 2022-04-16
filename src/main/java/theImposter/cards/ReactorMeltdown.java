package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.EasyModalChoiceAction;
//import theImposter.actions.ReactorMeltdownAction;
import theImposter.powers.ReactorMeltdownBuffPower;
import theImposter.powers.ReactorMeltdownDamagePower;
//import theImposter.powers.ReactorMeltingDownPower;

import java.util.ArrayList;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.atb;

public class ReactorMeltdown extends AbstractEasyCard {
    public final static String ID = makeID("ReactorMeltdown");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ReactorMeltdown() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard("", "Your Strength and Dexterity gains from Votes are increased by " + this.magicNumber * 50 + "%.", () -> atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ReactorMeltdownBuffPower(AbstractDungeon.player, this.magicNumber), this.magicNumber))));
        easyCardList.add(new EasyModalChoiceCard("", "Damage dealt to enemies when triggering votes is increased by " + this.magicNumber * 50 + "%.", () -> atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ReactorMeltdownDamagePower(AbstractDungeon.player, this.magicNumber), this.magicNumber))));
        atb(new EasyModalChoiceAction(easyCardList));

//        if (!p.hasPower(ReactorMeltingDownPower.POWER_ID)) {
//            this.addToBot(new ApplyPowerAction(p, p, new ReactorMeltingDownPower(p)));
//        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}