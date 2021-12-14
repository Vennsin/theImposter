package theImposter.potions;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.abstracts.CustomPotion;
import theImposter.ImposterMod;
import theImposter.cards.FuelCanister;

public class JugOfFuel extends CustomPotion {

    public static final String POTION_ID = ImposterMod.makeID("JugOfFuel");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public JugOfFuel() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.M, PotionColor.NONE);

        // Potency is the damage/magic number equivalent of potions.
        potency = getPotency();

        // Initialize the Description
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];

        // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;

        // Initialize the on-hover name + description
//        tips.add(new PowerTip(name, description));

    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractCard fuelCanister = new FuelCanister();
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new MakeTempCardInHandAction(fuelCanister.makeStatEquivalentCopy(), this.potency));
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new JugOfFuel();
    }

    @Override
    public int getPotency(final int potency) {
        return 2;
    }

//    public void upgradePotion()
//    {
//        potency += 1;
//        tips.clear();
//        tips.add(new PowerTip(name, description));
//    }
}
