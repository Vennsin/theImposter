package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;
import theImposter.actions.BackstabAction;

public class Backstab extends AbstractEasyCard {
    public final static String ID = makeID("Backstab");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Backstab() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 7;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (m.hasPower("impostermod:Sus")) {
//            this.addToBot(new BackstabAction(m, new DamageInfo(p, m.getPower("impostermod:Sus").amount * 4, this.damageTypeForTurn)));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage + m.getPower("impostermod:Sus").amount * this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}