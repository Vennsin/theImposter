package theImposter.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static theImposter.util.Wiz.atb;

public class SortSamplesAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    private int dmgPerCard;
    private int blckPerCard;
    private int cardsDrawnPerCard;

    public SortSamplesAction(AbstractPlayer p, AbstractMonster m, int dmgPerCard, int blckPerCard, int cardsDrawnPerCard) {
        this.p = p;
        this.m = m;
        this.dmgPerCard = dmgPerCard;
        this.blckPerCard = blckPerCard;
        this.cardsDrawnPerCard = cardsDrawnPerCard;
    }

    public void update() {
        int totalDmg = 0;
        int totalBlck = 0;
        int totalDraws = 0;
        Iterator var3 = this.p.hand.group.iterator();

        while(var3.hasNext()) {
            AbstractCard c = (AbstractCard)var3.next();
            if (c.type == AbstractCard.CardType.ATTACK) {
                totalDmg += dmgPerCard;
            } else if (c.type == AbstractCard.CardType.SKILL) {
                totalBlck += blckPerCard;
            } else if (c.type == AbstractCard.CardType.POWER) {
                totalDraws += cardsDrawnPerCard;
            }
        }

        this.addToBot(new DamageAction(m, new DamageInfo(p, totalDmg), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        atb(new GainBlockAction(p, AbstractDungeon.player, totalBlck));
        this.addToBot(new DrawCardAction(p, totalDraws));

        this.isDone = true;
    }
}
