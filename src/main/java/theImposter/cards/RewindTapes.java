package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

public class RewindTapes extends AbstractEasyCard {
    public final static String ID = makeID("RewindTapes");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public RewindTapes() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 0;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new BetterDiscardPileToHandAction(this.magicNumber));

        if (this.upgraded)
        {
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.discardPile.size()));

        }
        blck();
    }

    public void upp() {
        uDesc();
    }
}