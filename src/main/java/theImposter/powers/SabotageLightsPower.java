package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.util.TexLoader;

import static theImposter.ImposterMod.makeID;

public class SabotageLightsPower extends AbstractPower implements BetterOnApplyPowerPower {
    public static final String POWER_NAME = "Sabotage Lights";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SabotageLightsPower(AbstractCreature owner, int amount) {
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

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature owner, AbstractCreature source) {
        if (power.ID.equals(SusPower.POWER_ID))
        {
            return true;
        }

        return false;
    }

    @Override
    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount)
    {
//        each stack of SabotageLights will increase Sus applied by 50%
        power.amount += (int)(stackAmount * AbstractDungeon.player.getPower(SabotageLightsPower.POWER_ID).amount * 0.5);
//        power.amount++;
        stackAmount += (int)(stackAmount * AbstractDungeon.player.getPower(SabotageLightsPower.POWER_ID).amount * 0.5);
//        stackAmount = stackAmount + (int)(stackAmount * AbstractDungeon.player.getPower(SabotageLightsPower.POWER_ID).amount * 0.5);
//        stackAmount += (AbstractDungeon.player.getPower(SabotageLightsPower.POWER_ID).amount * 0.5);
//        if (power.ID.equals(SusPower.POWER_ID))
//        {
//            stackAmount += (AbstractDungeon.player.getPower(SabotageLightsPower.POWER_ID).amount * 0.5);
//        }
        return stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount * 50) + DESCRIPTIONS[1];
    }

//    @Override
//    public void updateDescription() {
//        description = "All Sus applications are increased by " + this.amount * 50 + "%.";
//    }
}