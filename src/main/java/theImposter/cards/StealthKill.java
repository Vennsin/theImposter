package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.TheImposter;
import theImposter.powers.LoseVoteBuffPower;
import theImposter.powers.VoteBuffPower;
import theImposter.powers.VoteEnemyPower;

public class StealthKill extends AbstractEasyCard {
    public final static String ID = makeID("StealthKill");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public StealthKill() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 1;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
//            this.addToBot(new ApplyPowerToRandomEnemyAction(p, new VoteEnemyPower((AbstractCreature) null, p, this.magicNumber), this.magicNumber, false, AbstractGameAction.AttackEffect.NONE));

            AbstractMonster randomMonster = AbstractDungeon.getRandomMonster();
            this.addToBot(new ApplyPowerAction(randomMonster, p, new VoteEnemyPower(randomMonster, p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(randomMonster, p, new VoteBuffPower(randomMonster, p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(randomMonster, p, new LoseVoteBuffPower(randomMonster, p, this.magicNumber), this.magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}