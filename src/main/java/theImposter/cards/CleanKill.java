package theImposter.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.atb;

import theImposter.TheImposter;
import theImposter.actions.CleanKillAction;
import theImposter.actions.EasyModalChoiceAction;

import java.util.ArrayList;

public class CleanKill extends AbstractEasyCard {
    public final static String ID = makeID("CleanKill");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public CleanKill() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 3;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
//        this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.magicNumber), this.magicNumber));

        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard("", "Deal " + damage + " damage and apply 2 Sus.", () -> atb(new CleanKillAction(m, this.damage, 1, this.damageTypeForTurn))));
        easyCardList.add(new EasyModalChoiceCard("", "Deal " + damage + " damage and remove 1 of your Votes.", () -> atb(new CleanKillAction(m, this.damage, 2, this.damageTypeForTurn))));
        atb(new EasyModalChoiceAction(easyCardList));
    }

    public void upp() {
        upgradeDamage(3);
    }
}