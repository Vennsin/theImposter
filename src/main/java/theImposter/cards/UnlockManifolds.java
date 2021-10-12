package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Random;

import static theImposter.ImposterMod.makeID;

public class UnlockManifolds extends AbstractEasyCard {
    public final static String ID = makeID("UnlockManifolds");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public UnlockManifolds() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 6;
        this.secondMagic = this.baseSecondMagic = magicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Random rand = new Random();

        int randomBlock;
        randomBlock = rand.nextInt(4);

        this.secondMagic = randomBlock + this.magicNumber;

        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(3);
        uDesc();
    }
}