package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.TheImposter;
import theImposter.actions.VentAction;

public class KillAndVent extends AbstractEasyCard {
    public final static String ID = makeID("KillAndVent");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillAndVent() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseBlock = 7;
        tags.add(TheImposter.Enums.KILL);
        tags.add(TheImposter.Enums.VENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new VentAction());
    }


    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }
}