package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theImposter.cards.CalibrateAgain;
import theImposter.cards.MonitorTree;

import java.util.Iterator;

public class WaterPlantsAction extends AbstractGameAction {
    private int dmgIncrease;
    public WaterPlantsAction(int dmgIncrease) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.dmgIncrease = dmgIncrease;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            this.increaseAttackDamageInGroup(p.hand, this.dmgIncrease);
            this.isDone = true;
        }

    }

    private void increaseAttackDamageInGroup(CardGroup cardGroup, int dmgIncrease) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(c -> c.cardID.equals(MonitorTree.ID)))
        {
            dmgIncrease *= 2;
        }

        Iterator var2 = cardGroup.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.type == AbstractCard.CardType.ATTACK) {
                if (cardGroup.type == CardGroupType.HAND && c.baseDamage >= 0) {
                    c.superFlash();

                    this.addToBot(new ModifyDamageAction(c.uuid, dmgIncrease));
                    c.isDamageModified = true;
                    c.applyPowers();
                }

//                c.upgrade();
//                c.applyPowers();
            }
        }

    }
}

