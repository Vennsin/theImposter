package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theImposter.TheImposter;
import theImposter.powers.VotePlayerPower;

import static theImposter.ImposterMod.makeID;

public class KillAndRun extends AbstractEasyCard {
    public final static String ID = makeID("KillAndRun");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillAndRun() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
//        this.magicNumber = this.baseMagicNumber = 1;
        baseBlock = 4;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
//        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(1);
    }
}