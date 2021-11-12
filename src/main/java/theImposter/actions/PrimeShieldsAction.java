package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PrimeShieldsAction extends AbstractGameAction {

    public PrimeShieldsAction() {

    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.SKILL)
            {
                c.setCostForTurn(-9);
            }
        }

        isDone = true;
    }
}
