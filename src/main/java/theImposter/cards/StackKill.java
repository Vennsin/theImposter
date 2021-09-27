package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;
import theImposter.powers.VotePower;

import static theImposter.ImposterMod.makeID;

import java.util.Iterator;

public class StackKill extends AbstractEasyCard {
    public final static String ID = makeID("StackKill");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public StackKill() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
        this.baseMagicNumber = 15;
        this.magicNumber = this.baseMagicNumber;
        this.baseSecondMagic = 3;
        this.secondMagic = this.baseSecondMagic;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            this.magicNumber--;
        }

        if (this.magicNumber < 0)
        {
            this.magicNumber = 0;
        }

        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new VotePower(m, p, this.secondMagic), this.secondMagic));
    }

    public void upp() {
        upgradeDamage(3);
    }
}