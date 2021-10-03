package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.ImposterMod;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.VotePlayerPower;

import java.util.Iterator;

public class TriggerVotesAction extends AbstractGameAction {

    public TriggerVotesAction() {

    }

    public void update() {
        CardCrawlGame.sound.playA(ImposterMod.makeID("EMERGENCYMEETING"), 0.0F);

        if (AbstractDungeon.player.hasPower(VotePlayerPower.POWER_ID)) {
            this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo((AbstractCreature)null, AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID).amount * 10, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.FIRE));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID)));
        }

        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (mo.hasPower(VoteEnemyPower.POWER_ID)) {
                this.addToBot(new DamageAction(mo, new DamageInfo((AbstractCreature)null, mo.getPower(VoteEnemyPower.POWER_ID).amount * 10, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.FIRE));
//                this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(numUniques * 2, true), DamageInfo.DamageType.THORNS, AttackEffect.POISON));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, AbstractDungeon.player, mo.getPower(VoteEnemyPower.POWER_ID)));
            }
        }

        isDone = true;
    }
}
