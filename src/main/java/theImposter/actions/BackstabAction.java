//

//package com.megacrit.cardcrawl.actions.unique;
package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class BackstabAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.01F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private AbstractMonster m;

    public BackstabAction(AbstractMonster target, DamageInfo info) {
        this.info = info;
        this.setValues(target, info);
        this.m = target;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.SLASH_VERTICAL;
        this.duration = 0.01F;
    }

    public void update() {
        if (this.target == null) {
            this.isDone = true;
        } else {
            if (this.m.hasPower("impostermod:Sus")) {
                if (this.duration == 0.01F && this.target != null && this.target.currentHealth > 0) {
                    if (this.info.type != DamageType.THORNS && this.info.owner.isDying) {
                        this.isDone = true;
                        return;
                    }

                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
                }

                this.tickDuration();
                if (this.isDone && this.target != null && this.target.currentHealth > 0) {
                    this.target.damage(this.info);
                    if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                        AbstractDungeon.actionManager.clearPostCombatActions();
                    }

                    this.addToTop(new WaitAction(0.1F));
                }
            } else {
                this.isDone = true;
            }
        }
    }
}
