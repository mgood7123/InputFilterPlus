package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that can disallow backspaces
 * @see smallville7123.inputFilter.DigitRangeFilter
 * @see smallville7123.inputFilter.SpaceFilter
 * @see smallville7123.inputFilter.ReplacementFilter
 * @see smallville7123.inputFilter.InputFilterPlus
 */
public class BackSpaceFilter extends InputFilterPlus {

    private final boolean allowBackspaces;

    /**
     * a version of {@link InputFilterPlus} that can disallow backspaces
     *
     * @param allowBackspaces true if backspaces are allowed, otherwise false
     *
     * @see smallville7123.inputFilter.DigitRangeFilter
     * @see smallville7123.inputFilter.SpaceFilter
     * @see smallville7123.inputFilter.ReplacementFilter
     * @see smallville7123.inputFilter.InputFilterPlus
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
