package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.powers.VotePower;

public class KillOnCams extends AbstractEasyCard {
    public final static String ID = makeID("KillOnCams");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillOnCams() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new ApplyPowerAction(p, p, new VotePower(m, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}