package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import com.megacrit.cardcrawl.powers.AbstractPower;
import theImposter.powers.AbstractEasyPower;
import theImposter.powers.VotePower;

public class KillAFKCrewmate extends AbstractEasyCard {
    public final static String ID = makeID("KillAFKCrewmate");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillAFKCrewmate() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.isInnate = true;
        baseDamage = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        this.addToBot(new ApplyPowerAction(m, p, new VotePower(p, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
    }
}