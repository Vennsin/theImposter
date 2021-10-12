package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.TheImposter;
import theImposter.actions.KillOnTheVentAction;
import theImposter.actions.VentAction;


public class KillOnTheVent extends AbstractEasyCard {
    public final static String ID = makeID("KillOnTheVent");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillOnTheVent() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new KillOnTheVentAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public void upp() {
        upgradeDamage(3);
    }
}