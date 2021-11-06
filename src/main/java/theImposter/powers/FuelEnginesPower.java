package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.util.TexLoader;

import static theImposter.ImposterMod.makeID;

public class FuelEnginesPower extends AbstractPower {
    private AbstractCreature source;
    public static final String POWER_NAME = "Fuel Engines";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCard card;
    private static int fuelEnginesIdOffset;

    public FuelEnginesPower(AbstractCreature owner, int numTurns, AbstractCard copyMe) {
        this.name = POWER_NAME;
//        this.ID = POWER_ID;
        this.ID = POWER_ID + fuelEnginesIdOffset;
        ++fuelEnginesIdOffset;

        this.owner = owner;
        this.amount = numTurns;
        this.type = AbstractPower.PowerType.BUFF;

        this.card = copyMe.makeStatEquivalentCopy();
        this.card.resetAttributes();

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
        if(this.amount > 1) {
            this.amount--;
        } else if(this.amount == 1) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }

        this.addToBot(new MakeTempCardInHandAction(this.card, this.amount));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.card.name, "y") + DESCRIPTIONS[1] + this.amount +  DESCRIPTIONS[2];
    }
}