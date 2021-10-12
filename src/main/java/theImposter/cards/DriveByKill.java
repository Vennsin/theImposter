package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.TheImposter;
import theImposter.powers.SusPower;

import java.util.Iterator;

public class DriveByKill extends AbstractEasyCard {
    public final static String ID = makeID("DriveByKill");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public DriveByKill() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

//        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        while(var3.hasNext()) {
//            AbstractMonster mo = (AbstractMonster)var3.next();
//            this.addToBot(new ApplyPowerAction(mo, p, new SusPower(mo, p, this.magicNumber), this.magicNumber));
//        }
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new SusPower(mo, p, this.magicNumber), this.magicNumber, true));
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}