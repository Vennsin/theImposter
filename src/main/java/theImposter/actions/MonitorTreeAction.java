package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class MonitorTreeAction extends AbstractGameAction {
    private int blkIncrease;
    public MonitorTreeAction(int blkIncrease) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.blkIncrease = blkIncrease;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            this.increaseBlockInGroup(p.hand, this.blkIncrease);
            this.isDone = true;
        }

    }

    private void increaseBlockInGroup(CardGroup cardGroup, int blkIncrease) {
        Iterator var2 = cardGroup.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.type == AbstractCard.CardType.SKILL) {
                if (cardGroup.type == CardGroupType.HAND) {
                    c.superFlash();

                    this.addToBot(new ModifyBlockAction(c.uuid, blkIncrease));
                    c.isBlockModified = true;
                    c.applyPowers();
                }

//                c.upgrade();
//                c.applyPowers();
            }
        }

    }
}

