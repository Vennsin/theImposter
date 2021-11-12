package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import theImposter.actions.StoreArtifactsAction;

public class StoreArtifacts extends AbstractEasyCard {
    public final static String ID = makeID("StoreArtifacts");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public StoreArtifacts() {
        super(ID, 4, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        FleetingField.fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new StoreArtifactsAction());
//        add a placeholder power just as a reminder to the player

//        for (AbstractCard c : AbstractDungeon.player.hand.group) {
//            if (c.rarity == CardRarity.RARE)
//            {
//                c.cost = 0;
//                c.costForTurn = 0;
//                c.isCostModified = true;
//                c.superFlash(Color.GOLD.cpy());
//            }
//        }
//
//        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.COMMON);
    }

    public void upp() {
        FleetingField.fleeting.set(this, false);
        this.exhaust = true;
        uDesc();
    }
}