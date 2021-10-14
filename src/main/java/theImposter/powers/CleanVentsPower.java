package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theImposter.ImposterMod;
import theImposter.actions.VentAction;
import theImposter.util.TexLoader;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

import static theImposter.ImposterMod.makeID;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class CleanVentsPower extends AbstractPower {
    private AbstractCreature source;
    public static final String POWER_NAME = "Clean Vents";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CleanVentsPower(AbstractCreature owner, int amount) {
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

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            this.flash();
            this.addToBot(new ApplyPowerToRandomEnemyAction(AbstractDungeon.player, new VulnerablePower((AbstractCreature)null, amount, false), amount, false));
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, CleanVentsPower.POWER_ID));
    }

//    @Override
//    public void updateDescription() {
//        description = "When you play a skill this turn, apply 1 Vulnerable to a random enemy.";
//    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}