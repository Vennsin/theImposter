package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.powers.JamCommunicationsPower;

import java.util.Iterator;

import static theImposter.ImposterMod.makeID;

public class JamCommunications extends AbstractEasyCard {
    public final static String ID = makeID("JamCommunications");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public JamCommunications() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean powerExists = false;
        Iterator var4 = p.powers.iterator();

        while(var4.hasNext()) {
            AbstractPower pow = (AbstractPower)var4.next();
            if (pow.ID.equals(JamCommunicationsPower.POWER_ID)) {
                powerExists = true;
                break;
            }
        }

        if (!powerExists) {
            this.addToBot(new ApplyPowerAction(p, p, new JamCommunicationsPower(p)));
        }

        this.addToBot(new ApplyPowerAction(p, p, new JamCommunicationsPower(p)));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}