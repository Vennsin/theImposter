package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.util.TexLoader;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class HardAccusationsPower extends AbstractPower {
    private AbstractCreature source;
    public static final String POWER_NAME = "Hard Accusations";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int counter;

    public HardAccusationsPower(AbstractCreature owner, int amt) {
        this.name = POWER_NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        this.amount = amt;

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

    public void atTurnStart() {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        ++this.counter;
        if (this.counter % 4 == 0) {
            this.flash();
            this.counter = 0;

            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VotePlayerPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VoteBuffPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseVoteBuffPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));

            Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(monsterIterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster)monsterIterator.next();
                this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new VoteEnemyPower(mo, AbstractDungeon.player, 2), 2));
                this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new VoteBuffPower(mo, AbstractDungeon.player, 2), 2));
                this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new LoseVoteBuffPower(mo, AbstractDungeon.player, 2), 2));
            }
        }
    }

//    @Override
//    public void updateDescription() {
//        description = "Every time you play 4 cards in a single turn, gain 1 Vote and apply 1 Vote to ALL enemies.";
//    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
