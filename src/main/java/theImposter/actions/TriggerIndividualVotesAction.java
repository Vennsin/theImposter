package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theImposter.ImposterMod;
import theImposter.powers.ReactorMeltdownDamagePower;
import theImposter.powers.VoteEnemyPower;
import theImposter.powers.VotePlayerPower;

public class TriggerIndividualVotesAction extends AbstractGameAction {
    private AbstractCreature c;
    private boolean isPlayer;

    public TriggerIndividualVotesAction(AbstractCreature creature, boolean isPlayer) {
        this.c = creature;
        this.isPlayer = isPlayer;
    }

    public int GetVoteTriggerBaseDamage()
    {
        if (AbstractDungeon.player.hasPower(ReactorMeltdownDamagePower.POWER_ID))
        {
            return (int)(10 + (10 * ((float)(AbstractDungeon.player.getPower(ReactorMeltdownDamagePower.POWER_ID).amount * 0.5))));
        }
        return 10;
    }

    public void update() {
        CardCrawlGame.sound.playA(ImposterMod.makeID("EMERGENCYMEETING"), 0.0F);

        if (isPlayer) {
            if (AbstractDungeon.player.hasPower(VotePlayerPower.POWER_ID)) {
                this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo((AbstractCreature) null, AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID).amount * GetVoteTriggerBaseDamage(), DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.FIRE));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID)));
            }
        }
        else {
            if (c.hasPower(VoteEnemyPower.POWER_ID)) {
                this.addToBot(new DamageAction(c, new DamageInfo((AbstractCreature) null, c.getPower(VoteEnemyPower.POWER_ID).amount * GetVoteTriggerBaseDamage(), DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.FIRE));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(c, AbstractDungeon.player, c.getPower(VoteEnemyPower.POWER_ID)));
            }
        }

        isDone = true;
    }
}
