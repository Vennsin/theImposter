package theImposter.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.Iterator;

public class DivertPowerAction extends AbstractGameAction {
    private AbstractPlayer p;

    public DivertPowerAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            boolean betterPossible = false;
            boolean possible = false;
            Iterator var3 = this.p.hand.group.iterator();

            while(var3.hasNext()) {
                AbstractCard c = (AbstractCard)var3.next();
                if (c.costForTurn > 0) {
                    betterPossible = true;
                } else if (c.cost > 0) {
                    possible = true;
                }
            }

            if (betterPossible || possible) {
                this.findAndModifyCard(betterPossible);
            }
        }

        this.tickDuration();
    }

    private void findAndModifyCard(boolean better) {
        AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
        if (better) {
            if (c.costForTurn > 0) {
                c.costForTurn--;
//                c.isCostModified = true;
                c.superFlash(Color.GOLD.cpy());

                if (c.type == AbstractCard.CardType.ATTACK)
                {
                    this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
                }
                else if (c.type == AbstractCard.CardType.SKILL)
                {
                    this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
                }
                else if (c.type == AbstractCard.CardType.POWER)
                {
                    this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
                    this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
                }
            } else {
                this.findAndModifyCard(better);
            }
        } else if (c.cost > 0) {
            c.costForTurn--;
//            c.isCostModified = true;
            c.superFlash(Color.GOLD.cpy());

            if (c.type == AbstractCard.CardType.ATTACK)
            {
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            }
            else if (c.type == AbstractCard.CardType.SKILL)
            {
                this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
            }
            else if (c.type == AbstractCard.CardType.POWER)
            {
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
                this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
            }
        } else {
            this.findAndModifyCard(better);
        }

//        if (c.type == AbstractCard.CardType.ATTACK)
//        {
//            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
//        }
//        else if (c.type == AbstractCard.CardType.SKILL)
//        {
//            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
//        }
//        else if (c.type == AbstractCard.CardType.POWER)
//        {
//            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
//            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
//        }
    }
}
