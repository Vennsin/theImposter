//

//package com.megacrit.cardcrawl.actions.unique;
package theImposter.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theImposter.ImposterMod;

import java.util.Iterator;

public class VoteAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.01F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private AbstractMonster m;

    public VoteAction() {
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.LIGHTNING;
        this.duration = 0.01F;
        this.damageType = DamageType.THORNS;
    }

    public void update() {
        int totalVotes = 0;
        if (AbstractDungeon.player.hasPower("impostermod:Vote"))
        {
            totalVotes += AbstractDungeon.player.getPower("impostermod:Vote").amount;
        }

        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (mo.hasPower("impostermod:Vote")) {
                totalVotes += mo.getPower("impostermod:Vote").amount;
            }
        }

        if (totalVotes >= 10)
        {
            CardCrawlGame.sound.playA(ImposterMod.makeID("EMERGENCYMEETING"), 0.0F);

            if (AbstractDungeon.player.hasPower("impostermod:Vote")) {
                this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo((AbstractCreature)null, AbstractDungeon.player.getPower("impostermod:Vote").amount * 1, this.damageType),
                        this.attackEffect));
                AbstractDungeon.player.getPower("impostermod:Vote").amount = 0;
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower("impostermod:Vote")));
            }

            monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(monsterIterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster)monsterIterator.next();
                if (mo.hasPower("impostermod:Vote")) {
//                    totalVotes += mo.getPower("impostermod:Vote").amount;
                    this.addToBot(new DamageAction(mo, new DamageInfo((AbstractCreature)null, mo.getPower("impostermod:Vote").amount * 1, this.damageType),
                            this.attackEffect));
                    mo.getPower("impostermod:Vote").amount = 0;
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, AbstractDungeon.player, mo.getPower("impostermod:Vote")));
                }
            }
        }
    }
}
