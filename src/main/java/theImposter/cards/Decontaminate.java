package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class Decontaminate extends AbstractEasyCard {
    public final static String ID = makeID("Decontaminate");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Decontaminate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, -this.magicNumber), -this.magicNumber));
//        if (p.hasPower(VotePlayerPower.POWER_ID)) {
//            int playerVotesRemoved = this.magicNumber;
//
//            if (playerVotesRemoved >= p.getPower(VotePlayerPower.POWER_ID).amount)
//            {
//                this.addToBot(new RemoveSpecificPowerAction(p, p, p.getPower(VotePlayerPower.POWER_ID)));
//            }
//            else
//            {
//                this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, -playerVotesRemoved), -playerVotesRemoved));
//            }
//        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}