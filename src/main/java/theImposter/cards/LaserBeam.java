package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import theImposter.powers.SusPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.atb;

public class LaserBeam extends AbstractEasyCard {
    public final static String ID = makeID("LaserBeam");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public LaserBeam() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 1;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        int energyGain = 0;

        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (mo.hasPower(SusPower.POWER_ID)) {
                energyGain++;
            }
            this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.magicNumber), this.magicNumber));
        }

//        this.addToBot(new SFXAction("ATTACK_DEFECT_BEAM"));
//        this.addToBot(new VFXAction(p, new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
//        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        atb(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
//        dmg(m, AbstractGameAction.AttackEffect.FIRE);

        this.addToBot(new GainEnergyAction(energyGain));
    }

    public void upp() {
        upgradeDamage(3);
    }
}