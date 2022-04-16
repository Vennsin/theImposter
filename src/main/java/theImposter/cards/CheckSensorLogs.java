package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.CheckSensorLogsAction;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VoteEnemyPower;

import static theImposter.ImposterMod.makeID;

public class CheckSensorLogs extends AbstractEasyCard {
    public final static String ID = makeID("CheckSensorLogs");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CheckSensorLogs() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new VoteEnemyPower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new VoteBuffPower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new LoseVoteBuffPower(m, p, this.magicNumber), this.magicNumber));

        this.addToBot(new CheckSensorLogsAction(m, this.upgraded));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}