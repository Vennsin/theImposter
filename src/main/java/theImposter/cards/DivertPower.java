package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;
import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class DivertPower extends AbstractEasyCard {
    public final static String ID = makeID("DivertPower");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DivertPower() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> groupCopy = new ArrayList();
        Iterator var4 = AbstractDungeon.player.hand.group.iterator();

        while(true) {
            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
                    groupCopy.add(c);
                }
            }

            var4 = AbstractDungeon.actionManager.cardQueue.iterator();

            while(var4.hasNext()) {
                CardQueueItem i = (CardQueueItem)var4.next();
                if (i.card != null) {
                    groupCopy.remove(i.card);
                }
            }

            AbstractCard c = null;
            if (groupCopy.isEmpty()) {
            } else {
                Iterator var9 = groupCopy.iterator();

                while(var9.hasNext()) {
                    AbstractCard cc = (AbstractCard)var9.next();
                }

                c = (AbstractCard)groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
            }

            if (c != null) {
                c.setCostForTurn(c.cost - 1);
            }

            if (c.type == CardType.ATTACK)
            {
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
            }
            else if (c.type == CardType.SKILL)
            {
                this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
            }
            else if (c.type == CardType.POWER)
            {
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
                this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
            }

            break;
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}