package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.TheImposter;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class KillInSeclusion extends AbstractEasyCard {
    public final static String ID = makeID("KillInSeclusion");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillInSeclusion() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 18;
        this.exhaust = true;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (p.hasPower(VotePlayerPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, p.getPower(VotePlayerPower.POWER_ID)));
        }
    }

    public void upp() {
        upgradeDamage(7);
    }
}