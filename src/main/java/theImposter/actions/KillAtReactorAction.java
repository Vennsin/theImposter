package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class KillAtReactorAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    private int drawUpTo;

    public KillAtReactorAction(AbstractPlayer p, AbstractMonster m, int drawUpTo) {
        this.p = p;
        this.m = m;
        this.drawUpTo = drawUpTo;
    }

    public void update() {
        int numCardsToDraw = drawUpTo - this.p.hand.size();

        if (numCardsToDraw > 0) {
            this.addToBot(new DrawCardAction(numCardsToDraw));
        }
        this.addToBot(new KillAtReactorEnergyGainAction(p));

//        int energyGain = 0;
//        Iterator var3 = this.p.hand.group.iterator();
//
//        while(var3.hasNext()) {
//            AbstractCard c = (AbstractCard)var3.next();
//            if (c.type == AbstractCard.CardType.POWER || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
//                energyGain++;
//            }
//        }
//
//        this.addToBot(new GainEnergyAction(energyGain));

        this.isDone = true;
    }
}
