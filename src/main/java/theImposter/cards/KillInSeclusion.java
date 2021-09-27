package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class KillInSeclusion extends AbstractEasyCard {
    public final static String ID = makeID("KillInSeclusion");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillInSeclusion() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 16;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (p.hasPower("impostermod:Vote(s)")) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, p.getPower("impostermod:Vote(s)")));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}