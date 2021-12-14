package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.util.TexLoader;

import static theImposter.ImposterMod.makeID;

public class GasLightingPower extends AbstractPower  implements BetterOnApplyPowerPower {
    private AbstractCreature source;
    private int counter = 1;
    public static final String POWER_NAME = "GasLighting";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GasLightingPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = POWER_NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        this.amount = amount;

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

    public void atStartOfTurn() {
        this.counter = this.amount;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;

        this.counter += stackAmount;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature owner, AbstractCreature source) {
        if (this.counter > 0 && power.ID.equals(VotePlayerPower.POWER_ID)) {
            counter--;
            return false;
        }
        return true;
    }

    @Override
    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount)
    {
//        if (this.counter > 0 && power.ID.equals(VotePlayerPower.POWER_ID)) {
//            stackAmount -=- this.counter;
//            if (stackAmount < 0)
//            {
//                this.counter = -stackAmount;
//                stackAmount = 0;
//            }
//        }
        return stackAmount;
    }
}