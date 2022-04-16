package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.util.TexLoader;

import static theImposter.ImposterMod.makeID;

public class ReactorMeltdownBuffPower extends AbstractPower {
    private AbstractCreature source;
    public static final String POWER_NAME = "Reactor Meltdown (str/dex)";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ReactorMeltdownBuffPower(AbstractCreature owner, int amount) {
        this.name = POWER_NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

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

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        int numVoteBuffs = 0;
        if (this.owner.hasPower(VoteBuffPower.POWER_ID))
        {
            numVoteBuffs = this.owner.getPower(VoteBuffPower.POWER_ID).amount;
        }
//        return type == DamageInfo.DamageType.NORMAL ? damage + (float)numVoteBuffs : damage;
        return type == DamageInfo.DamageType.NORMAL ? damage + ((float)Math.ceil(numVoteBuffs * AbstractDungeon.player.getPower(ReactorMeltdownBuffPower.POWER_ID).amount * 0.5)) : damage;
    }

    public float modifyBlock(float blockAmount) {
        int numVoteBuffs = 0;
        if (this.owner.hasPower(VoteBuffPower.POWER_ID))
        {
            numVoteBuffs = this.owner.getPower(VoteBuffPower.POWER_ID).amount;
        }
//        return (blockAmount += (float)numVoteBuffs) < 0.0F ? 0.0F : blockAmount;
        return (blockAmount += ((float)Math.ceil(numVoteBuffs * AbstractDungeon.player.getPower(ReactorMeltdownBuffPower.POWER_ID).amount * 0.5))) < 0.0F ? 0.0F : blockAmount;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount * 50) + DESCRIPTIONS[1];
    }
}
