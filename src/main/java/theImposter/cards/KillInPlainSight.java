package theImposter.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theImposter.ImposterMod.makeID;

import theImposter.powers.VotePower;
import java.util.Random;

public class KillInPlainSight extends AbstractEasyCard {
    public final static String ID = makeID("KillInPlainSight");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public KillInPlainSight() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 12;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Random rand = new Random();

        int n = rand.nextInt(3);
        this.magicNumber += n;

        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        this.addToBot(new ApplyPowerAction(m, p, new VotePower(m, p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
    }
}