package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theImposter.powers.DevelopPhotosPower;
import theImposter.powers.SusPower;

import static theImposter.ImposterMod.makeID;

public class DevelopPhotos extends AbstractEasyCard {
    public final static String ID = makeID("DevelopPhotos");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DevelopPhotos() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new ApplyPowerAction(p, p, new DevelopPhotosPower(p, 2), 2));
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new SusPower(mo, p, this.magicNumber), this.magicNumber, true));
        }
        this.addToBot(new PressEndTurnButtonAction());
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}