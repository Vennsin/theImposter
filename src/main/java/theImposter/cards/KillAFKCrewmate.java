package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theImposter.TheImposter;
import theImposter.powers.AbstractEasyPower;
import theImposter.powers.VotePlayerPower;

public class KillAFKCrewmate extends AbstractEasyCard {
    public final static String ID = makeID("KillAFKCrewmate");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillAFKCrewmate() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.isInnate = true;
        baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 3;
        this.exhaust = true;
        tags.add(TheImposter.Enums.KILL);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
//        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(2);
    }
}