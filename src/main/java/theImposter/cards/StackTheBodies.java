package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import static theImposter.ImposterMod.makeID;

import java.util.Iterator;

public class StackTheBodies extends AbstractEasyCard {
    public final static String ID = makeID("StackTheBodies");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public StackTheBodies() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (!m.isDeadOrEscaped() && mo.hasPower(SusPower.POWER_ID)) {
                dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
            }
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}