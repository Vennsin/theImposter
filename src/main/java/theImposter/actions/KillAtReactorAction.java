package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class KillAtReactorAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    private int numCards;

    public KillAtReactorAction(AbstractPlayer p, AbstractMonster m, int numCards) {
        this.p = p;
        this.m = m;
        this.numCards = numCards;
    }

    public void update() {
        this.addToBot(new DrawCardAction(p, this.numCards));

        int energyGain = 0;
        Iterator var3 = this.p.hand.group.iterator();

        while(var3.hasNext()) {
            AbstractCard c = (AbstractCard)var3.next();
            if (c.type == AbstractCard.CardType.POWER || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
                energyGain++;
            }
        }

        this.addToBot(new GainEnergyAction(energyGain));

        this.isDone = true;
    }
}
