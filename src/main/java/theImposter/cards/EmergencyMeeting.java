package theImposter.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.EmergencyMeetingAction;

import static theImposter.ImposterMod.makeID;

public class EmergencyMeeting extends AbstractEasyCard {
    public final static String ID = makeID("EmergencyMeeting");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public EmergencyMeeting() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EmergencyMeetingAction(this.upgraded));
    }

    public void upp() {
        upgradeBaseCost(2);
        uDesc();
    }
}