package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.actions.VoteAction;
import theImposter.util.TexLoader;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class VotePower extends AbstractPower {
        public VotePower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = "Vote(s)";
        this.ID = makeID(name.replaceAll("([ ])", ""));

        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.DEBUFF;

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

    @Override
    public void updateDescription() {
        description = "Increases attack damage and block by #b"+ this.amount +".  Votes trigger when there are at least 10 Votes on the field: each creature takes damage equal to (10 * their number of Votes), then all Votes are removed.";
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        int totalVotes = 0;
        if (AbstractDungeon.player.hasPower("impostermod:Vote(s)"))
        {
            totalVotes += AbstractDungeon.player.getPower("impostermod:Vote(s)").amount;
        }

        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (mo.hasPower("impostermod:Vote(s)")) {
                totalVotes += mo.getPower("impostermod:Vote(s)").amount;
            }
        }

        if (totalVotes >= 10)
        {
            CardCrawlGame.sound.playA(ImposterMod.makeID("EMERGENCYMEETING"), 0.0F);

            if (AbstractDungeon.player.hasPower("impostermod:Vote(s)")) {
                this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo((AbstractCreature)null, AbstractDungeon.player.getPower("impostermod:Vote(s)").amount * 10, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.FIRE));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower("impostermod:Vote(s)")));
            }

            monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(monsterIterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster)monsterIterator.next();
                if (mo.hasPower("impostermod:Vote(s)")) {
                    this.addToBot(new DamageAction(mo, new DamageInfo((AbstractCreature)null, mo.getPower("impostermod:Vote(s)").amount * 10, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.FIRE));
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, AbstractDungeon.player, mo.getPower("impostermod:Vote(s)")));
                }
            }
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
    }

    public float modifyBlock(float blockAmount) {
        return (blockAmount += (float)this.amount) < 0.0F ? 0.0F : blockAmount;
    }
}