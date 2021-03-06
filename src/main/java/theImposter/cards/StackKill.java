package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.actions.EasyModalChoiceAction;
import theImposter.actions.StackKillAction;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.atb;

import java.util.ArrayList;

public class StackKill extends AbstractEasyCard {
    public final static String ID = makeID("StackKill");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public StackKill() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 1;

        baseSecondDamage = 1;
        this.secondMagic = this.baseSecondMagic = 8;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard("", "Deal " + damage + " damage per enemy and apply " + magicNumber + " Sus.", () -> atb(new StackKillAction(m, this.damage, this.magicNumber, this.damageTypeForTurn))));
        easyCardList.add(new EasyModalChoiceCard("", "Deal " + secondDamage + " damage per enemy and apply " + secondMagic + " Sus.", () -> atb(new StackKillAction(m, this.secondDamage, this.secondMagic, this.damageTypeForTurn))));
        atb(new EasyModalChoiceAction(easyCardList));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(3);

        upgradeSecondDamage(3);
        upgradeSecondMagic(3);
    }
}