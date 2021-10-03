package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theImposter.ImposterMod.makeID;

public class InspectSamples extends AbstractEasyCard {
    public final static String ID = makeID("InspectSamples");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public InspectSamples() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 10;
        this.magicNumber = this.baseMagicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(2);
    }
}