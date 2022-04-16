package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.actions.TriggerIndividualVotesAction;
import theImposter.actions.TriggerVotesAction;
import theImposter.util.TexLoader;

import java.util.Iterator;

//import static theImposter.ImposterMod.makeID;

public class VoteEnemyPower extends AbstractPower {
    public static final String POWER_NAME = "Vote(s)";
    //    POWER_ID is hardcoded to differentiate between PlayerVotes and EnemyVotes
    public static final String POWER_ID = "impostermod:VotesEnemy";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public VoteEnemyPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = POWER_NAME;
//        this.ID = makeID(name.replaceAll("([ ])", ""));
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

    public int GetVoteTriggerAmount()
    {
        if (AbstractDungeon.player.hasPower(JamCommunications7Power.POWER_ID))
        {
            return 7;
        }
        else if (AbstractDungeon.player.hasPower(JamCommunications13Power.POWER_ID))
        {
            return 13;
        }

        return 10;
    }

    public int GetTotalVotes() {
        int totalVotes = 0;
        boolean playerHasVotes = false;
        boolean enemyHasVotes = false;
        if (AbstractDungeon.player.hasPower(VotePlayerPower.POWER_ID))
        {
            playerHasVotes = true;
            totalVotes += AbstractDungeon.player.getPower(VotePlayerPower.POWER_ID).amount;
        }

        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(monsterIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
            if (mo.hasPower(VoteEnemyPower.POWER_ID)) {
                enemyHasVotes = true;
                totalVotes += mo.getPower(VoteEnemyPower.POWER_ID).amount;
            }
        }

//        this is to prevent Votes from triggering twice if both Player and Enemies have votes
        if (playerHasVotes && enemyHasVotes)
        {
            totalVotes = 0;
        }

        return totalVotes;
    }

//moved to TriggerVotesAction
//    public void TriggerVotes() {
//        CardCrawlGame.sound.playA(ImposterMod.makeID("EMERGENCYMEETING"), 0.0F);
//
//        if (AbstractDungeon.player.hasPower("impostermod:VotesPlayer")) {
//            this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo((AbstractCreature)null, AbstractDungeon.player.getPower("impostermod:VotesPlayer").amount * 10, DamageInfo.DamageType.THORNS),
//                    AbstractGameAction.AttackEffect.FIRE));
//            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower("impostermod:VotesPlayer")));
//        }
//
//        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        while(monsterIterator.hasNext()) {
//            AbstractMonster mo = (AbstractMonster)monsterIterator.next();
//            if (mo.hasPower("impostermod:VotesEnemy")) {
//                this.addToBot(new DamageAction(mo, new DamageInfo((AbstractCreature)null, mo.getPower("impostermod:VotesEnemy").amount * 10, DamageInfo.DamageType.THORNS),
//                        AbstractGameAction.AttackEffect.FIRE));
//                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, AbstractDungeon.player, mo.getPower("impostermod:VotesEnemy")));
//            }
//        }
//    }

//    @Override
//    public void updateDescription() {
//        description = "Increases attack damage and block by #b"+ this.amount +".  Votes trigger when there are at least 10 Votes on the field: each creature takes damage equal to (10 * their number of Votes), then all Votes are removed.";
//    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
//        this.addToBot(new CheckVotesAction());
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && GetTotalVotes() >= GetVoteTriggerAmount())
        {
            this.flashWithoutSound();
            this.addToBot(new TriggerIndividualVotesAction(this.owner, false));
//            this.addToBot(new TriggerVotesAction());
        }
    }

//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
//    }
//
////    this is to simulate dex for enemies.  Need to check for Stun (Sus) though
//    public void atStartOfTurn() {
////        this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
////        AbstractMonster mo = (AbstractMonster) this.owner;
////        mo.intent
////        ((AbstractMonster)(this.owner)).intent
//
//        switch(((AbstractMonster)(this.owner)).intent) {
//            case ATTACK_DEFEND:
//            case DEFEND:
//            case DEFEND_DEBUFF:
//            case DEFEND_BUFF:
//                this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
//        }
//    }

//    public void onGainedBlock(float blockAmount) {
//        if (blockAmount > 0.0F) {
//            this.flash();
//            owner.addBlock(this.amount);
//        }
//
//    }

//    public float modifyBlock(float blockAmount) {
//        return (blockAmount += (float)this.amount) < 0.0F ? 0.0F : blockAmount;
//    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, VoteEnemyPower.POWER_ID));
        }
    }
}