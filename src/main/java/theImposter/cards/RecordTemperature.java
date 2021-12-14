package theImposter.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.actions.EasyModalChoiceAction;
import theImposter.actions.RecordTemperatureGainBlockAction;
import theImposter.actions.RecordTemperatureLoseHpAction;

import java.util.ArrayList;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.atb;

public class RecordTemperature extends AbstractEasyCard {
    public final static String ID = makeID("RecordTemperature");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public RecordTemperature() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseBlock = 0;
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new EasyModalChoiceCard("", "All enemies lose HP equal to their Sus.", () -> atb(new RecordTemperatureLoseHpAction())));
        easyCardList.add(new EasyModalChoiceCard("", "Gain block equal to the total Sus of ALL enemies.", () -> atb(new RecordTemperatureGainBlockAction())));
        atb(new EasyModalChoiceAction(easyCardList));
    }

    public void upp() {
        this.isEthereal = false;
        uDesc();
    }
}