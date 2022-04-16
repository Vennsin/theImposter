package theImposter.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.CheckVotesAction;
import theImposter.actions.EasyModalChoiceAction;
import theImposter.actions.JamCommunicationsAction;

import java.util.ArrayList;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.atb;

public class JamCommunications extends AbstractEasyCard {
    public final static String ID = makeID("JamCommunications");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public JamCommunications() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard("", "Votes now trigger at 7 stacks among us.", () -> atb(new JamCommunicationsAction(7))));
        easyCardList.add(new EasyModalChoiceCard("", "Votes now trigger at 13 stacks among us.", () -> atb(new JamCommunicationsAction(13))));
        atb(new EasyModalChoiceAction(easyCardList));

        this.addToBot(new CheckVotesAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}



//package theImposter.cards;
//
//        import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//        import com.megacrit.cardcrawl.characters.AbstractPlayer;
//        import com.megacrit.cardcrawl.monsters.AbstractMonster;
//        import com.megacrit.cardcrawl.powers.AbstractPower;
//        import theImposter.powers.JamCommunicationsPower;
//
//        import java.util.Iterator;
//
//        import static theImposter.ImposterMod.makeID;
//
//public class JamCommunications extends AbstractEasyCard {
//    public final static String ID = makeID("JamCommunications");
//    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
//
//    public JamCommunications() {
//        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
//    }
//
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        boolean powerExists = false;
//        Iterator var4 = p.powers.iterator();
//
//        while(var4.hasNext()) {
//            AbstractPower pow = (AbstractPower)var4.next();
//            if (pow.ID.equals(JamCommunicationsPower.POWER_ID)) {
//                powerExists = true;
//                break;
//            }
//        }
//
//        if (!powerExists) {
//            this.addToBot(new ApplyPowerAction(p, p, new JamCommunicationsPower(p)));
//        }
//
//        this.addToBot(new ApplyPowerAction(p, p, new JamCommunicationsPower(p)));
//    }
//
//    public void upp() {
//        upgradeBaseCost(1);
//    }
//}