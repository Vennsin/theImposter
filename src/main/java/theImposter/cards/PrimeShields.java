package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theImposter.actions.PrimeShieldsAction;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.ShieldsPrimedPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VotePlayerPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class PrimeShields extends AbstractEasyCard {
    public final static String ID = makeID("PrimeShields");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public PrimeShields() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondMagic = this.baseSecondMagic = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.secondMagic), this.secondMagic));
        this.addToBot(new ApplyPowerAction(p, p, new VoteBuffPower(p, p, this.secondMagic), this.secondMagic));
        this.addToBot(new ApplyPowerAction(p, p, new LoseVoteBuffPower(p, p, this.secondMagic), this.secondMagic));

        this.addToBot(new PrimeShieldsAction());

//        boolean powerExists = false;
//        Iterator var4 = p.powers.iterator();
//
//        while(var4.hasNext()) {
//            AbstractPower pow = (AbstractPower)var4.next();
//            if (pow.ID.equals(ShieldsPrimedPower.POWER_ID)) {
//                powerExists = true;
//                break;
//            }
//        }
//
//        if (!powerExists) {
//            this.addToBot(new ApplyPowerAction(p, p, new ShieldsPrimedPower(p)));
//        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}