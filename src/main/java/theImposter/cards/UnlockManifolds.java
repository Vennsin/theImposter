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
        baseBlock = 6;
        this.magicNumber = this.baseMagicNumber = baseBlock + 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Random rand = new Random();

        int randomBlock;
        randomBlock = rand.nextInt(4);

        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, (this.block + randomBlock)));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(3);
    }
}