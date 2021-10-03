package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;
import theImposter.powers.VoteEnemyPower;

import java.util.Iterator;

public class DriveByKill extends AbstractEasyCard {
    public final static String ID = makeID("DriveByKill");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public DriveByKill() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        Iterator mo = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(mo.hasNext()) {
            this.addToBot(new ApplyPowerAction(m, p, new VoteEnemyPower(m, p, this.magicNumber), this.magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}