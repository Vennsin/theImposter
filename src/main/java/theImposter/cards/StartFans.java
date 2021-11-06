package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theImposter.TheImposter;
import theImposter.actions.VentAction;

import static theImposter.ImposterMod.makeID;

public class StartFans extends AbstractEasyCard {
    public final static String ID = makeID("StartFans");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public StartFans() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.exhaust = true;
        tags.add(TheImposter.Enums.VENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(ArtifactPower.POWER_ID)) {
//            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, AbstractDungeon.player, m.getPower(ArtifactPower.POWER_ID)));
            this.addToBot(new RemoveSpecificPowerAction(m, AbstractDungeon.player, m.getPower(ArtifactPower.POWER_ID)));
        }
        this.addToBot(new VentAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}