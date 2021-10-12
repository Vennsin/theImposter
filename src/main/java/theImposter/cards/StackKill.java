//package theImposter.cards;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import theImposter.TheImposter;
//import theImposter.powers.SusPower;
//import theImposter.powers.VotePlayerPower;
//
//import static theImposter.ImposterMod.makeID;
//
//import java.util.Iterator;
//
//public class StackKill extends AbstractEasyCard {
//    public final static String ID = makeID("StackKill");
//    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
//
//    public StackKill() {
//        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
//        baseDamage = 12;
//        this.baseMagicNumber = 10;
//        this.magicNumber = this.baseMagicNumber;
//        this.baseSecondMagic = 3;
//        this.secondMagic = this.baseSecondMagic;
//        tags.add(TheImposter.Enums.KILL);
//    }
//
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        int numMonsters = AbstractDungeon.getCurrRoom().monsters.monsters.size();
//
//        //for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
//        for (int i = 0 ; i < numMonsters ; i++)
//        {
//            if (!m.isDeadOrEscaped()) {
//                dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
//            }
//        }
//
//        this.magicNumber -= numMonsters;
//        if (this.magicNumber < 0)
//        {
//            this.magicNumber = 0;
//        }
//
//        Iterator monsterIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        while(monsterIterator.hasNext() && !m.isDeadOrEscaped()) {
//            this.addToBot(new ApplyPowerAction(m, p, new SusPower(m, p, this.magicNumber), this.magicNumber));
//        }
//        this.addToBot(new ApplyPowerAction(p, p, new VotePlayerPower(p, p, this.secondMagic), this.secondMagic));
//    }
//
//    public void upp() {
//        upgradeDamage(3);
//    }
//}