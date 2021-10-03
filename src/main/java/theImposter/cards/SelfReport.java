package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.actions.TriggerVotesAction;
import theImposter.powers.VotePlayerPower;

public class SelfReport extends AbstractEasyCard {
    public final static String ID = makeID("SelfReport");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public SelfReport() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 12;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        this.addToBot(new TriggerVotesAction());
    }

    public void upp() {
        this.exhaust = false;
        uDesc();
    }
}