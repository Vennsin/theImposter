package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.WeakPower;
import theImposter.powers.*;

import java.util.Random;

public class DroppedIntoLava extends AbstractEasyCard {
    public final static String ID = makeID("DroppedIntoLavaCard");
//    makeID("DroppedIntoLava") causes a crash for some reason
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public DroppedIntoLava() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Random rand = new Random();

        int n;
        if (upgraded) {
            n = rand.nextInt(2) + 1;
        }
        else {
            n = rand.nextInt(3);
        }
        this.magicNumber += n;

        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if (this.magicNumber > 0) {
            this.addToBot(new ApplyPowerAction(m, p, new VoteEnemyPower(m, p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(m, p, new VoteBuffPower(m, p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(m, p, new LoseVoteBuffPower(m, p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
        uDesc();
    }
}