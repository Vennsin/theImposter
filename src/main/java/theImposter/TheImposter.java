package theImposter;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import theImposter.cards.*;

import theImposter.relics.SecondImposter;

import java.util.ArrayList;
import java.util.List;

import static theImposter.TheImposter.Enums.IMPOSTER_COLOR;
import static theImposter.ImposterMod.*;

public class TheImposter extends CustomPlayer {
    private static final String[] orbTextures = {
            modID + "Resources/images/char/mainChar/orb/layer1.png",
            modID + "Resources/images/char/mainChar/orb/layer2.png",
            modID + "Resources/images/char/mainChar/orb/layer3.png",
            modID + "Resources/images/char/mainChar/orb/layer4.png",
            modID + "Resources/images/char/mainChar/orb/layer5.png",
            modID + "Resources/images/char/mainChar/orb/layer6.png",
            modID + "Resources/images/char/mainChar/orb/layer1d.png",
            modID + "Resources/images/char/mainChar/orb/layer2d.png",
            modID + "Resources/images/char/mainChar/orb/layer3d.png",
            modID + "Resources/images/char/mainChar/orb/layer4d.png",
            modID + "Resources/images/char/mainChar/orb/layer5d.png",};
    static final String ID = makeID("TheImposter");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;

//    basemod.animations.AbstractAnimation ani = new SpriterAnimation(modID + "Resources/images/char/mainChar/static.scml");
//    String x = "Resources/images/char/mainChar/static" + rand + ".scml";
    public TheImposter(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, modID + "Resources/images/char/mainChar/orb/vfx.png", null), new SpriterAnimation(
                modID + "Resources/images/char/mainChar/static.scml"));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                65, 65, 0, 99, 10, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            retVal.add(Strike.ID);
//        }
//        for (int i = 0; i < 4; i++) {
//            retVal.add(Defend.ID);
//        }
//        retVal.add(CleanKill.ID);
//        retVal.add(ClearAsteroids.ID);
//        retVal.add(VentHopping.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(WalkThePlank.ID);
        retVal.add(KillAndVent.ID);

        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(SecondImposter.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
//        CardCrawlGame.sound.playA("EMERGENCYMEETING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.sound.playA(ImposterMod.makeID("EMERGENCYMEETING"), 0.0F);
//        CardCrawlGame.sound.playA(ImposterMod.makeID("EMERGENCYMEETING"), MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return ImposterMod.makeID("EMERGENCYMEETING");
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 6;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return IMPOSTER_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return characterColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
//        System.out.println("YOU NEED TO SET getStartCardForEvent() in your " + getClass().getSimpleName() + " file!");
        return new CleanKill();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheImposter(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return characterColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return characterColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList();
        panels.add(new CutscenePanel(modID + "Resources/images/scenes/imposter1.png"));
        panels.add(new CutscenePanel(modID + "Resources/images/scenes/imposter2.png"));
        panels.add(new CutscenePanel(modID + "Resources/images/scenes/imposter3.png"));
        return panels;
//        look at Spire w/ Friends/chrono/images/cutscenes/AllTogether.png
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        //TODO: Change these.
        @SpireEnum
        public static AbstractCard.CardTags VENT;
        public static AbstractCard.CardTags KILL;
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_IMPOSTER;
        @SpireEnum(name = "IMPOSTER_COLOR")
        public static AbstractCard.CardColor IMPOSTER_COLOR;
        @SpireEnum(name = "IMPOSTER_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
