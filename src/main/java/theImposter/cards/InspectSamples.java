//package theImposter.cards;
//
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.PoisonPower;
//
//import static theImposter.ImposterMod.makeID;
//
//public class InspectSamples extends AbstractEasyCard {
//    public final static String ID = makeID("InspectSamples");
//    // intellij stuff skill, self, basic, , ,  5, 3, ,
//
//    public InspectSamples() {
//        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
//        this.magicNumber = this.baseMagicNumber = 3;
//        this.exhaust = true;
//    }
//
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new ApplyPowerAction(p, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber));
//    }
//
//    public void upp() {
//        this.exhaust = false;
//        uDesc();
//    }
//}