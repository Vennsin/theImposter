package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.SusPower;
import theImposter.powers.VoteBuffPower;
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
        this.addToBot(new ApplyPowerAction(m, p, new VoteBuffPower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new LoseVoteBuffPower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.secondMagic), this.secondMagic));

        if (p.hasPower(VotePlayerPower.POWER_ID)) {
            int playerVotesRemoved = 1;
            if (upgraded) {
                playerVotesRemoved++;
            }

            if (p.getPower(VotePlayerPower.POWER_ID).amount <= playerVotesRemoved) {
                this.addToBot(new RemoveSpecificPowerAction(p, p, AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID)));
            }
            else {
//                this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, -playerVotesRemoved), -playerVotesRemoved));
                this.addToBot(new ReducePowerAction(p, p, VotePlayerPower.POWER_ID, playerVotesRemoved));
            }
        }

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
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }
}