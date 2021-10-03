package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.powers.VotePlayerPower;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.SusPower;

public class DroppedIntoLava extends AbstractEasyCard {
    public final static String ID = makeID("DroppedIntoLavaCard");
//    makeID("DroppedIntoLava") causes a crash for some reason
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public DroppedIntoLava() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.baseSecondMagic = 4;
        this.secondMagic = this.baseSecondMagic;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        this.addToBot(new ApplyPowerAction(m, p, new VoteEnemyPower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.secondMagic), this.secondMagic));
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, 4), 4));
    }

    public void upp() {
        upgradeDamage(5);
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}