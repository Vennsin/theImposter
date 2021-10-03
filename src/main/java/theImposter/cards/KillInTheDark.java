package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;
import theImposter.powers.SusPower;
import theImposter.powers.VoteEnemyPower;

public class KillInTheDark extends AbstractEasyCard {
    public final static String ID = makeID("KillInTheDark");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillInTheDark() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 5;
        this.isMultiDamage = true;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        atb(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            this.addToBot(new ApplyPowerAction(mo, p, new SusPower(mo, p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(mo, p, new VoteEnemyPower(mo, p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber));
            this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber));
        }
    }

    public void triggerOnGlowCheck() {
        if (WouldTriggerVotes(this.magicNumber * AbstractDungeon.getCurrRoom().monsters.monsters.size())) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(5);
    }
}