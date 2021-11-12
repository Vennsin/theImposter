package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.actions.KillAtReactorAction;

import static theImposter.ImposterMod.makeID;

public class KillAtReactor extends AbstractEasyCard {
    public final static String ID = makeID("KillAtReactor");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public KillAtReactor() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 10;
        this.magicNumber = this.baseMagicNumber = 6;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);

        this.addToBot(new KillAtReactorAction(p, m, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}