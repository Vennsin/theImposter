package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.powers.AbstractEasyPower;
import theImposter.powers.VoteEnemyPower;

public class Babushka extends AbstractEasyCard {
    public final static String ID = makeID("Babushka");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Babushka() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 16;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ApplyPowerAction(m, p, new VoteEnemyPower(m, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(6);
    }
}