package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import java.util.Iterator;

public class KillAtReactorAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int damage;
    private AbstractPlayer p;
    private AbstractMonster m;
    private DamageInfo.DamageType damageTypeForTurn;
    private boolean upgraded = false;
    private int energyOnUse;

    public KillAtReactorAction(AbstractPlayer p, AbstractMonster m, int damage, DamageInfo.DamageType damageTypeForTurn, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.m = m;
        this.damage = damage;
        this.upgraded = upgraded;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        this.addToBot(new DamageAction(this.m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), AttackEffect.FIRE));

        if (effect > 0) {
            this.addToBot(new DrawCardAction(p, effect));

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        int energyGain = 0;
        Iterator var3 = this.p.hand.group.iterator();

        while(var3.hasNext()) {
            AbstractCard c = (AbstractCard)var3.next();
            if (c.type == AbstractCard.CardType.POWER || c.type == AbstractCard.CardType.STATUS) {
                energyGain++;
            }
        }

        if (this.upgraded)
        {
            energyGain++;
        }

        this.addToBot(new GainEnergyAction(energyGain));

        this.isDone = true;
    }
}
