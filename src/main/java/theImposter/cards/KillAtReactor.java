package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theImposter.TheImposter;
import theImposter.actions.EasyXCostAction;
import theImposter.actions.KillAtReactorAction;
import theImposter.cards.AbstractEasyCard;

import static theImposter.util.Wiz.*;
import static theImposter.ImposterMod.makeID;

public class KillAtReactor extends AbstractEasyCard {
    public final static String ID = makeID("KillAtReactor");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public KillAtReactor() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 7;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new KillAtReactorAction(p, m, this.damage, this.damageTypeForTurn, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upp() {
        uDesc();
    }
}