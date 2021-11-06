package theImposter.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static theImposter.ImposterMod.makeID;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;

public class StoreArtifacts extends AbstractEasyCard {
    public final static String ID = makeID("StoreArtifacts");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public StoreArtifacts() {
        super(ID, 4, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        FleetingField.fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.rarity == CardRarity.RARE)
            {
                c.cost = 0;
                c.costForTurn = 0;
                c.isCostModified = true;
                c.superFlash(Color.GOLD.cpy());
            }
        }

        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.COMMON);
    }

    public void upp() {
        FleetingField.fleeting.set(this, false);
        this.exhaust = true;
        uDesc();
    }
}