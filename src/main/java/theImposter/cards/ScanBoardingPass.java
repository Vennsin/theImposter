//package theImposter.cards;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//import java.util.Iterator;
//
//import static theImposter.ImposterMod.makeID;
//
//public class ScanBoardingPass extends AbstractEasyCard {
//    public final static String ID = makeID("ScanBoardingPass");
//    // intellij stuff skill, self, basic, , ,  5, 3, ,
//
//    public ScanBoardingPass() {
//        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
//        this.isInnate = true;
//        this.exhaust = true;
//    }
//
//    public void use(AbstractPlayer p, AbstractMonster m) {
//
//    }
//
//    public void upp() {
//        upgradeBaseCost(0);
//    }
//}