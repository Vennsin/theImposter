package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.SusPower;

import static theImposter.ImposterMod.makeID;

public class ChartCourse extends AbstractEasyCard {
    public final static String ID = makeID("ChartCourse");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ChartCourse() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;
        this.baseBlock = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int blockAmount = 0;
        if (m.currentBlock > 0)
        {
            blockAmount = m.currentBlock;
        }

        this.addToBot(new RemoveAllBlockAction(m, p));
        blck();
        if (this.upgraded)
        {
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, blockAmount));
        }
        this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
        uDesc();
    }
}