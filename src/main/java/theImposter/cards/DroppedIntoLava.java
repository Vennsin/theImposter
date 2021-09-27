package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.powers.AbstractEasyPower;
import theImposter.powers.VotePower;
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
        this.addToBot(new ApplyPowerAction(m, p, new VotePower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.secondMagic), this.secondMagic));
    }

    public void upp() {
        upgradeDamage(3);
    }
}