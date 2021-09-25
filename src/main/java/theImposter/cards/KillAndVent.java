package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.powers.VentPower;

public class KillAndVent extends AbstractEasyCard {
    public final static String ID = makeID("KillAndVent");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillAndVent() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 999;
        baseBlock = 999;
        this.baseMagicNumber = 7;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new ApplyPowerAction(m, p, new VentPower(AbstractPower.PowerType.DEBUFF, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}