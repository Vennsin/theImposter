package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import theImposter.TheImposter;
import theImposter.actions.KillAtO2Action;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VotePlayerPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class KillAtO2 extends AbstractEasyCard {
    public final static String ID = makeID("KillAtO2");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillAtO2() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.exhaust = true;
        baseDamage = 8;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.baseSecondMagic = 0;
        this.secondMagic = this.baseSecondMagic;
        this.exhaust = true;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        see Blizzard about displaying Power count
        this.addToBot(new KillAtO2Action());

        int powersPlayed = 0;
        Iterator var4 = AbstractDungeon.actionManager.cardsPlayedThisCombat.iterator();

        while(var4.hasNext()) {
            AbstractCard o = (AbstractCard)var4.next();
            if (o.type == CardType.POWER) {
                ++powersPlayed;
            }
        }

        for (int i = 0 ; i < powersPlayed ; i++)
        {
            if (!m.isDeadOrEscaped()) {
                dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
            }
        }
//        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage + (powersPlayed * this.secondMagic), this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new VoteBuffPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseVoteBuffPower(p, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
    }
}