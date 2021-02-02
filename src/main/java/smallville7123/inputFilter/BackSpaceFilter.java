package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that can disallow backspaces
 * @see HexRangeFilter
 * @see DigitRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see InputFilterPlus
 */
public class BackSpaceFilter extends InputFilterPlus {

    private final boolean allowBackspaces;

    BackSpaceFilter() {
        throw new RuntimeException("A constructor that accepts arguments must be called instead");
    };

    /**
     * a version of {@link InputFilterPlus} that can disallow backspaces
     *
     * @param allowBackspaces true if backspaces are allowed, otherwise false
     *
     * @see HexRangeFilter
     * @see DigitRangeFilter
     * @see SpaceFilter
     * @see ReplacementFilter
     * @see InputFilterPlus
     */
    public BackSpaceFilter(boolean allowBackspaces) {
        this.allowBackspaces = allowBackspaces;
    }

    @Override
    public String onLetterRemovedFromStart(String currentString, String letter) {
        if (!allowBackspaces) return letter;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterRemovedFromMiddle(String currentString, int oldLetterStartLocation, String letter) {
        if (!allowBackspaces) return letter;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterRemovedFromEnd(String currentString, int oldLetterStartLocation, String letter) {
        if (!allowBackspaces) return letter;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringRemovedFromStart(String currentString, String string) {
        if (!allowBackspaces) return string;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringRemovedFromMiddle(String currentString, int oldStringStartLocation, String string) {
        if (!allowBackspaces) return string;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringRemovedFromEnd(String currentString, int oldStringStartLocation, String string) {
        if (!allowBackspaces) return string;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }
}
