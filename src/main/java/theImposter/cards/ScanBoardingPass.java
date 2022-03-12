package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static theImposter.ImposterMod.makeID;

public class ScanBoardingPass extends AbstractEasyCard {
    public final static String ID = makeID("ScanBoardingPass");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ScanBoardingPass() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.isInnate = true;
        this.exhaust = true;
        this.cardsToPreview = new SwipeCard();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.powers.size() > 0) {
            ArrayList<AbstractPower> debuffs = new ArrayList<>();
            for (AbstractPower pow : p.powers) {
                if (pow.type == AbstractPower.PowerType.DEBUFF) {
                    debuffs.add(pow);
                }
            }

            if (debuffs.size() > 0) {
                addToBot(new RemoveSpecificPowerAction(p, p, debuffs.get(AbstractDungeon.cardRandomRng.random.nextInt(debuffs.size()))));
            }
        }
        this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}