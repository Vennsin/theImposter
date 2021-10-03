package theImposter.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static theImposter.ImposterMod.makeID;

public class AssembleArtifact extends AbstractEasyCard {
    public final static String ID = makeID("AssembleArtifact");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public AssembleArtifact() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 11;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        this.isInnate = true;
        uDesc();
    }
}