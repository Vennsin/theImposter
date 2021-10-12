package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.TheImposter;
import theImposter.powers.SusPower;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.VotePlayerPower;

public class KillDuringChaos extends AbstractEasyCard {
    public final static String ID = makeID("KillDuringChaos");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillDuringChaos() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 10;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.magicNumber), this.magicNumber));

        if (m.hasPower(VoteEnemyPower.POWER_ID))
        {
            this.addToBot(new GainEnergyAction(this.magicNumber));
        }

        if (m.hasPower(SusPower.POWER_ID))
        {
            this.addToBot(new DrawCardAction(p, this.magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(4);
    }
}