package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.powers.VotePlayerPower;

public class OutTheAirlock extends AbstractEasyCard {
    public final static String ID = makeID("OutTheAirlock");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public OutTheAirlock() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 33;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.POISON);
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(m, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(11);
    }
}