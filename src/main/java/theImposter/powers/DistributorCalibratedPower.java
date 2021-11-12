package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.actions.TriggerVotesAction;
import theImposter.actions.VentAction;
import theImposter.util.TexLoader;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theImposter.ImposterMod.makeID;

import java.util.Iterator;

public class DistributorCalibratedPower extends AbstractPower {
    private AbstractCreature source;
    public static final String POWER_NAME = "Distributor Calibrated";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DistributorCalibratedPower(AbstractCreature owner, int amount) {
        this.name = POWER_NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;

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

    public int GetTotalVotes() {
        int totalVotes = 0;
        if (AbstractDungeon.player.hasPower(VotePlayerPower.POWER_ID))
        {
            totalVotes += AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID).amount;
        }

        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (mo.hasPower(VoteEnemyPower.POWER_ID)) {
                totalVotes += mo.getPower(VoteEnemyPower.POWER_ID).amount;
            }
        }

        return totalVotes;
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
//        if (this.owner.hasPower(VotePlayerPower.POWER_ID)) {
//            if (this.owner.getPower(VotePlayerPower.POWER_ID).amount < this.amount)
//            {
//                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID)));
//            }
//            else {
////                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VotePlayerPower(this.owner, this.owner, -this.amount), -this.amount));
//                this.addToBot(new ReducePowerAction(this.owner, this.owner, VotePlayerPower.POWER_ID, this.amount));
//            }
//        }

        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VoteBuffPower(this.owner, this.owner, this.amount), this.amount));

        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            this.addToBot(new ApplyPowerAction(mo, this.owner, new SusPower(mo, this.owner, this.amount * 10), this.amount * 10));
            this.addToBot(new ApplyPowerAction(mo, this.owner, new VoteEnemyPower(mo, this.owner, this.amount * 3), this.amount * 3));
            this.addToBot(new ApplyPowerAction(mo, this.owner, new VoteBuffPower(mo, this.owner, this.amount * 3), this.amount * 3));
            this.addToBot(new ApplyPowerAction(mo, this.owner, new LoseVoteBuffPower(mo, this.owner, this.amount * 3), this.amount * 3));
        }

        if (GetTotalVotes() >= 10)
        {
            this.flashWithoutSound();
            this.addToBot(new TriggerVotesAction());
        }

        this.addToBot(new VentAction());
    }

//    @Override
//    public void updateDescription() {
//        description = "At the end of your turn, remove 1 of your Votes, apply 10 Sus and 3 Votes to ALL enemies, then Vent.";
//    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount * 10 + DESCRIPTIONS[2] + this.amount * 3 + DESCRIPTIONS[3];
    }
}