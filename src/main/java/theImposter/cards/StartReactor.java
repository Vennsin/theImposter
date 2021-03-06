package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.StartReactorAction;

import static theImposter.ImposterMod.makeID;

public class StartReactor extends AbstractEasyCard {
    public final static String ID = makeID("StartReactor");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public StartReactor() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
        this.addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));

        this.addToBot(new StartReactorAction());
        if (this.upgraded)
        {
            this.addToBot(new StartReactorAction());
        }

//        ArrayList<AbstractCard> groupCopy = new ArrayList();
//        Iterator var4 = AbstractDungeon.player.hand.group.iterator();

//        while(true) {
//            while(var4.hasNext()) {
//                AbstractCard c = (AbstractCard)var4.next();
//                if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
//                    groupCopy.add(c);
//                }
//            }
//
//            var4 = AbstractDungeon.actionManager.cardQueue.iterator();
//
//            while(var4.hasNext()) {
//                CardQueueItem i = (CardQueueItem)var4.next();
//                if (i.card != null) {
//                    groupCopy.remove(i.card);
//                }
//            }
//
//            AbstractCard c = null;
//            if (groupCopy.isEmpty()) {
//            } else {
//                Iterator var9 = groupCopy.iterator();
//
//                while(var9.hasNext()) {
//                    AbstractCard cc = (AbstractCard)var9.next();
//                }
//
//                c = (AbstractCard)groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
//            }
//
//            if (c != null) {
//                c.setCostForTurn(0);
//            }
//
//            break;
//        }
    }

    public void upp() {
        upgradeBaseCost(2);
        upgradeMagicNumber(1);
        uDesc();
    }
}