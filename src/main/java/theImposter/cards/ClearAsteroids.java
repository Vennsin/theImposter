package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.DrawCallbackShuffleAction;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class ClearAsteroids extends AbstractEasyCard {
    public final static String ID = makeID("ClearAsteroids");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ClearAsteroids() {
        super(ID, 2, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseBlock = 10;
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondMagic = this.baseSecondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DrawCallbackShuffleAction(magicNumber, c -> c.type == CardType.ATTACK));
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.secondMagic), this.secondMagic));
        this.addToBot(new ApplyPowerAction(p, p, new VoteBuffPower(p, p, this.secondMagic), this.secondMagic));
        this.addToBot(new ApplyPowerAction(p, p, new LoseVoteBuffPower(p, p, this.secondMagic), this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}