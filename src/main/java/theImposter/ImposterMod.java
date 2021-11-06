package theImposter;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theImposter.cards.AbstractEasyCard;
import theImposter.cards.cardvars.SecondDamage;
import theImposter.cards.cardvars.SecondMagicNumber;
import theImposter.potions.DistilledNightshade;
import theImposter.potions.EssenceOfInnocence;
import theImposter.potions.JugOfFuel;
import theImposter.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ImposterMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber {

    public static final String modID = "impostermod";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";

    public ImposterMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheImposter.Enums.IMPOSTER_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static String makePotionPath(String resourcePath) {
        return modID + "Resources/images/potions/" + resourcePath;
    }

    public static void initialize() {
        ImposterMod thismod = new ImposterMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheImposter(TheImposter.characterStrings.NAMES[1], TheImposter.Enums.THE_IMPOSTER),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheImposter.Enums.THE_IMPOSTER);

        receiveEditPotions();
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    public void receiveEditPotions() {
        // Class Specific Potion. If you want your potion to not be class-specific,
        // just remove the player class at the end.
        // Remember, you can press ctrl+P inside parentheses like addPotions)
        BaseMod.addPotion(JugOfFuel.class, Color.GOLDENROD.cpy(), null, null, JugOfFuel.POTION_ID, TheImposter.Enums.THE_IMPOSTER);
        BaseMod.addPotion(DistilledNightshade.class, Color.PURPLE.cpy(), null, null, DistilledNightshade.POTION_ID, TheImposter.Enums.THE_IMPOSTER);
        BaseMod.addPotion(EssenceOfInnocence.class, Color.WHITE.cpy(), null, null, EssenceOfInnocence.POTION_ID, TheImposter.Enums.THE_IMPOSTER);
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");

        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/eng/Potionstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public void receiveAddAudio() {
        BaseMod.addAudio(makeID("KILL"), modID + "Resources/audio/Kill.ogg");
        BaseMod.addAudio(makeID("EMERGENCYMEETING"), modID + "Resources/audio/EmergencyMeeting.ogg");
    }

//    public void receivePostInitialize() {
//        BaseMod.addPotion(JugOfFuel.class, Color.ROYAL.cpy(), Color.ROYAL.cpy(), Color.ROYAL.cpy(), JugOfFuel.POTION_ID, TheImposter.Enums.THE_IMPOSTER);
//    }
}
