package Dictionary;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Pronunciation {
    /**
     * Phát âm từ.
     * @param text từ cần phát âm
     */
    public static void speech(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(text);
        syntheticVoice.deallocate();
    }
}
