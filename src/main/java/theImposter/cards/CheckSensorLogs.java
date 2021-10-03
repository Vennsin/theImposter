package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class CheckSensorLogs extends AbstractEasyCard {
    public final static String ID = makeID("CheckSensorLogs");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CheckSensorLogs() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;
        this.secondMagic = this.baseSecondMagic = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new VoteEnemyPower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.secondMagic), this.secondMagic));

        int playerVotesRemoved = 1;
            if (upgraded) {
                playerVotesRemoved++;
            }
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, -playerVotesRemoved), -playerVotesRemoved));

//        if (p.hasPower(VotePlayerPower.POWER_ID)) {
//            int playerVotesRemoved = 1;
//            if (upgraded) {
//                playerVotesRemoved++;
//            }
//
//            if (playerVotesRemoved >= p.getPower(VotePlayerPower.POWER_ID).amount)
//            {
//                this.addToBot(new RemoveSpecificPowerAction(p, p, p.getPower(VotePlayerPower.POWER_ID)));
//            }
//            else
//            {
//                this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, playerVotesRemoved), playerVotesRemoved * (-1)));
//            }
//        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}