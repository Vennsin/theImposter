package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.SusPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class HuntedDown extends AbstractEasyCard {
    public final static String ID = makeID("HuntedDown");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HuntedDown() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (m.hasPower(SusPower.POWER_ID)) {
            for (int i = 0; i < m.getPower(SusPower.POWER_ID).amount; i++) {
                dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            }
        }
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new VoteBuffPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseVoteBuffPower(p, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
    }
}