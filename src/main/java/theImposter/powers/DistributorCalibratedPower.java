package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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

import java.util.Iterator;

public class DistributorCalibratedPower extends AbstractPower {
    private AbstractCreature source;
    public static final String POWER_NAME = "Distributor Calibrated";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));

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

    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, VotePlayerPower.POWER_ID));

        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            this.addToBot(new ApplyPowerAction(mo, this.owner, new SusPower(mo, this.owner, this.amount * 10), this.amount * 10));
            this.addToBot(new ApplyPowerAction(mo, this.owner, new VoteEnemyPower(mo, this.owner, this.amount * 3), this.amount * 3));
        }

        this.addToBot(new VentAction());
    }

    @Override
    public void updateDescription() {
        description = "At the end of your turn, remove 1 of your Votes, apply 10 Sus and 3 Votes to ALL enemies, then Vent.";
    }
}