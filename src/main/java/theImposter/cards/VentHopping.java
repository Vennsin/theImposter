package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theImposter.actions.VentAction;

import java.util.ArrayList;
import java.util.Random;

import static theImposter.ImposterMod.makeID;

public class VentHopping extends AbstractEasyCard {
    public final static String ID = makeID("VentHopping");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public VentHopping() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<String> possibleDebuffs = new ArrayList();
        possibleDebuffs.add(VulnerablePower.POWER_ID);
        possibleDebuffs.add(WeakPower.POWER_ID);
        possibleDebuffs.add(FrailPower.POWER_ID);

        Random rand = new Random();
        String randomPower = "";

        for (int i = 0; i < this.magicNumber; i++)
        {
            randomPower = possibleDebuffs.get(rand.nextInt(possibleDebuffs.size()));
            possibleDebuffs.remove(randomPower);

            switch (randomPower) {
                case VulnerablePower.POWER_ID:
                    this.addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1));
                    break;
                case WeakPower.POWER_ID:
                    this.addToBot(new ApplyPowerAction(p, p, new WeakPower(p, 1, false), 1));
                    break;
                case FrailPower.POWER_ID:
                    this.addToBot(new ApplyPowerAction(p, p, new FrailPower(p, 1, false), 1));
                    break;
            }

        }
        this.addToBot(new VentAction());
        modifyCostForCombat(1);
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}