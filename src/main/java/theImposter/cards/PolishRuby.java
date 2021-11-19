package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theImposter.actions.EasyModalChoiceAction;

import java.util.ArrayList;

import static theImposter.ImposterMod.makeID;
import static theImposter.util.Wiz.*;

public class PolishRuby extends AbstractEasyCard {
    public final static String ID = makeID("PolishRuby");
    // intellij stuff skill, self, uncommon, , , , , ,

    public PolishRuby() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
        p.gainGold(this.magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(25);
    }
}