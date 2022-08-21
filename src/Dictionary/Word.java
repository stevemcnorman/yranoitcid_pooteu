package Dictionary;

public class Word {
    private String target;
    private String pronunciation;
    private String explain;

    public Word(String target, String pronunciation, String explain) {
        this.target = target;
        this.pronunciation = pronunciation;
        this.explain = explain;
    }

    public Word() {}

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        if (pronunciation != null) {
            return String.format("%s   %s\n\n%s\n", target, pronunciation,
                    explain.replaceAll("=", "    • ").replaceAll("\\+", ":"));
        } else {
            return String.format("%s\n\n%s\n", target,
                    explain.replaceAll("=", "    • ").replaceAll("\\+", ":"));
        }
    }
}
