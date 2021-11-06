//package theImposter.cards;
//
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import theImposter.powers.CleanVentsPower;
//import theImposter.powers.MarinationPower;
//
//import static theImposter.ImposterMod.makeID;
//
//public class ReactorMeltdown extends AbstractEasyCard {
//    public final static String ID = makeID("ReactorMeltdown");
//    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
//
//    public ReactorMeltdown() {
//        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
//        this.magicNumber = this.baseMagicNumber = 1;
//    }
//
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        if (p.hasPower(ReactorMeltingDownPower.POWER_ID)) {
//            this.addToBot(new ApplyPowerAction(p, p, new ReactorMeltingDownPower(p, this.magicNumber), this.magicNumber));
//        }
//    }
//
//    public void upp() {
//        upgradeBaseCost(2);
//    }
//}