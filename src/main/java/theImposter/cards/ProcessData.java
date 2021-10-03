package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class ProcessData extends AbstractEasyCard {
    public final static String ID = makeID("ProcessData");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ProcessData() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = 3;
        this.secondMagic = this.baseSecondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            this.addToBot(new ApplyPowerAction(mo, p, new SusPower(mo, p, this.magicNumber), this.magicNumber));
        }

        this.addToBot(new DrawCardAction(this.secondMagic));
    }

    public void upp() {
        upgradeSecondMagic(1);
        uDesc();
    }
}