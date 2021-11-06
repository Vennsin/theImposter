package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VotePlayerPower;

public class KillOnCams extends AbstractEasyCard {
    public final static String ID = makeID("KillOnCams");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillOnCams() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondMagic = baseSecondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new VoteBuffPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseVoteBuffPower(p, p, this.magicNumber), this.magicNumber));

        if (this.upgraded)
        {
            this.addToBot(new DrawCardAction(p, this.secondMagic));
        }
    }

//    public void triggerOnGlowCheck() {
//        if (WouldTriggerVotes(this.magicNumber)) {
//            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
//        } else {
//            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
//        }
//    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}