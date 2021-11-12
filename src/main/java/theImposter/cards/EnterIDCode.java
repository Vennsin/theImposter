package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;
import java.util.Random;

import static theImposter.ImposterMod.makeID;

public class EnterIDCode extends AbstractEasyCard {
    public final static String ID = makeID("EnterIDCode");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public EnterIDCode() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 0;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Random rand = new Random();

        int randomBlock = 0;
//        randomBlock = rand.nextInt(10);

        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(!mo.isDeadOrEscaped()) {
                randomBlock = rand.nextInt(10) + this.block;
                this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, randomBlock));
            }
        }

//        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//
//        while(var3.hasNext()) {
//            AbstractMonster mo = (AbstractMonster)var3.next();
//            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, randomBlock));
//        }
    }

    public void upp() {
        this.isInnate = true;
        uDesc();
    }
}