package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.powers.VotePower;

import java.util.Iterator;

public class KillOnCams extends AbstractEasyCard {
    public final static String ID = makeID("KillOnCams");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillOnCams() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new ApplyPowerAction(p, p, new VotePower(m, p, this.magicNumber), this.magicNumber));
    }

    public void triggerOnGlowCheck() {
        int totalVotes = 0;
        if (AbstractDungeon.player.hasPower("impostermod:Vote(s)"))
        {
            totalVotes += AbstractDungeon.player.getPower("impostermod:Vote(s)").amount;
        }

        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (mo.hasPower("impostermod:Vote(s)")) {
                totalVotes += mo.getPower("impostermod:Vote(s)").amount;
            }
        }

        if (totalVotes + this.magicNumber >= 10) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}