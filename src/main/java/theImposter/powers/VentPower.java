//package theImposter.powers;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.PowerStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import theImposter.ImposterMod;
//import theImposter.util.TexLoader;
//
//import static theImposter.ImposterMod.makeID;
//
//public class VentPower extends AbstractPower {
//    public VentPower(AbstractCreature owner, AbstractCreature source, int amount) {
//        this.name = "Vent";
//        this.ID = makeID(name.replaceAll("([ ])", ""));
//
//        this.owner = owner;
//        this.amount = amount;
//        this.type = AbstractPower.PowerType.DEBUFF;
//
//        Texture normalTexture = TexLoader.getTexture(ImposterMod.modID + "Resources/images/powers/" + name.replaceAll("([ ])", "") + "32.png");
//        Texture hiDefImage = TexLoader.getTexture(ImposterMod.modID + "Resources/images/powers/" + name.replaceAll("([ ])", "") + "84.png");
//        if (hiDefImage != null) {
//            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
//            if (normalTexture != null)
//                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
//        } else if (normalTexture != null) {
//            this.img = normalTexture;
//            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
//        }
//
//        this.updateDescription();
//    }
//    @Override
//    public void updateDescription() {
//        description = "TODO: Vote(s) not yet implemented.";
//    }
//}