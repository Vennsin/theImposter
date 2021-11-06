package theImposter.powers;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.util.TexLoader;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

import static theImposter.ImposterMod.makeID;

public class SusPower extends AbstractPower {
    private AbstractCreature source;
    public static final String POWER_NAME = "Sus";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SusPower(AbstractCreature owner, AbstractCreature source, int amount) {
//        this.name = "Sus";
//        this.ID = makeID(name.replaceAll("([ ])", ""));
        this.name = POWER_NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.type = AbstractPower.PowerType.DEBUFF;
//        priority is mainly for Sus to apply Stun before VoteBuffPower applies block (to make the VoteBuffPower check easier)
        this.priority = 1;

        Texture normalTexture = TexLoader.getTexture(ImposterMod.modID + "Resources/images/powers/" + name.replaceAll("([ ])", "") + "32.png");
        Texture hiDefImage = TexLoader.getTexture(ImposterMod.modID + "Resources/images/powers/" + name.replaceAll("([ ])", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        this.updateDescription();
    }

//    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
//        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
//            if ((((AbstractMonster) this.owner).getIntentDmg() > 0 && this.amount >= ((AbstractMonster) this.owner).getIntentDmg()) ||
//                    (((AbstractMonster) this.owner).getIntentDmg() == 0 && this.amount >= 20)) {
//                this.flash();
////            this.amount -=  ((AbstractMonster)this.owner).getIntentDmg();
////
////            if (this.amount <= 0) {
////                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
////            }
//                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
//                this.addToBot(new StunMonsterAction((AbstractMonster) this.owner, this.owner, 1));
//            }
//        }
//    }

    public void atStartOfTurn() {
        boolean isMultiDmg = (boolean) ReflectionHacks.getPrivate(this.owner, AbstractMonster.class, "isMultiDmg");
        int intentMultiAmt = intentMultiAmt = ReflectionHacks.getPrivate(this.owner, AbstractMonster.class, "intentMultiAmt");;
//        int totalIntentDmg =  intentMultiAmt * ((AbstractMonster) this.owner).getIntentDmg();
        int totalIntentDmg = 0;
        if (isMultiDmg) {
            totalIntentDmg = intentMultiAmt * ((AbstractMonster) this.owner).getIntentDmg();
        }
        else
        {
            totalIntentDmg = ((AbstractMonster) this.owner).getIntentDmg();
        }

        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if ((((AbstractMonster) this.owner).getIntentBaseDmg() > 0 && this.amount >= totalIntentDmg) ||
                    (((AbstractMonster) this.owner).getIntentBaseDmg() <= 0 && this.amount >= 20)) {
                this.flash();

                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                this.addToBot(new StunMonsterAction((AbstractMonster) this.owner, this.owner, 1));
            }
        }

//        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
//            if ((((AbstractMonster) this.owner).getIntentDmg() > 0 && this.amount >= ((AbstractMonster) this.owner).getIntentDmg()) ||
//                    (((AbstractMonster) this.owner).getIntentDmg() == 0 && this.amount >= 20)) {
//                this.flash();
////            this.amount -=  ((AbstractMonster)this.owner).getIntentDmg();
////
////            if (this.amount <= 0) {
////                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
////            }
//                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
//                this.addToBot(new StunMonsterAction((AbstractMonster) this.owner, this.owner, 1));
//            }
//        }
    }

//    @Override
//    public void updateDescription() {
//        description = "At start of turn, stun target if: Sus greater than or equal to target's total attack intent OR enemy will not attack and Sus >= 20 (either way, consume all stacks of Sus).";
//    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, SusPower.POWER_ID));
        }
    }
}