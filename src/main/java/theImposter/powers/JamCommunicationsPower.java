package theImposter.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.ImposterMod;
import theImposter.actions.TriggerVotesAction;
import theImposter.util.TexLoader;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class JamCommunicationsPower extends AbstractPower {
    private AbstractCreature source;
    public static final String POWER_NAME = "Jam Communications";
    public static final String POWER_ID = makeID(POWER_NAME.replaceAll("([ ])", ""));
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean upgraded = false;

    public JamCommunicationsPower(AbstractCreature owner) {
        this.name = POWER_NAME;
        this.ID = POWER_ID;

        this.owner = owner;
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

//    TODO: find a better way to implement this
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

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (GetTotalVotes() >= 7)
        {
            this.flashWithoutSound();
            this.addToBot(new TriggerVotesAction());
        }
    }

//    @Override
//    public void updateDescription() {
//        description = "Votes now trigger at 7 stacks among us.";
//    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
