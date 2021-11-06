package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.EmptyChuteAction;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class EmptyChute extends AbstractEasyCard {
    public final static String ID = makeID("EmptyChute");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public EmptyChute() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.magicNumber = this.baseMagicNumber = 2;
        this.secondMagic = this.baseSecondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EmptyChuteAction(p, this.magicNumber, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.secondMagic), this.secondMagic));
        this.addToBot(new ApplyPowerAction(p, p, new VoteBuffPower(p, p, this.secondMagic), this.secondMagic));
        this.addToBot(new ApplyPowerAction(p, p, new LoseVoteBuffPower(p, p, this.secondMagic), this.secondMagic));
    }

    public void upp() {
        upgradeBlock(2);
    }
}