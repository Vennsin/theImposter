package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.TheImposter;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VotePlayerPower;

public class DoubleKill extends AbstractEasyCard {
    public final static String ID = makeID("DoubleKill");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public DoubleKill() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 10;
        this.magicNumber = this.baseMagicNumber = 3;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if(!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
        {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new VoteBuffPower(p, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseVoteBuffPower(p, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
    }
}