package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that can replace text
 * @see HexRangeFilter
 * @see NumericRangeFilter
 * @see AlphaNumericRangeFilter
 * @see SpaceFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class ReplacementFilter extends InputFilterPlus {

    private final boolean allowReplacement;
    private final String what;
    private final String replacement;

    ReplacementFilter() {
        throw new RuntimeException("A constructor that accepts arguments must be called instead");
    }

    /**
     * a version of {@link InputFilterPlus} that can replace text
     *
     * @param allowReplacement true if text is allowed to be replaced, otherwise false
     * @param what the string to be replaced
     * @param replacement the string that <Strong>what</Strong> should be replaced with
     *
     * @see HexRangeFilter
     * @see NumericRangeFilter
     * @see AlphaNumericRangeFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public ReplacementFilter(boolean allowReplacement, String what, String replacement) {
        this.allowReplacement = allowReplacement;
        this.what = what;
        this.replacement = replacement;
    }

    @Override
    public String onSetText(String currentString, String string) {
        if (allowReplacement && string.contains(what)) {
            return string.replace(what, replacement);
        }
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterAppendedToStart(String currentString, String letter) {
        if (allowReplacement && letter.contains(what)) return replacement;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterAppendedToMiddle(String currentString, int oldLetterStartLocation, String letter) {
        if (allowReplacement && letter.contains(what)) return replacement;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterAppendedToEnd(String currentString, int oldLetterStartLocation, String letter) {
        if (allowReplacement && letter.contains(what)) return replacement;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterReplacedFromStart(String currentString, String oldLetter, String newLetter) {
        if (allowReplacement && newLetter.contains(what)) return replacement;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterReplacedFromMiddle(String currentString, int oldLetterStartLocation, String oldLetter, String newLetter) {
        if (allowReplacement && newLetter.contains(what)) return replacement;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterReplacedFromEnd(String currentString, int oldLetterStartLocation, String oldLetter, String newLetter) {
        if (allowReplacement && newLetter.contains(what)) return replacement;
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringReplacedFromStart(String currentString, String oldString, String newString) {
        if (allowReplacement && newString.contains(what)) {
            return newString.replace(what, replacement);
        }
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringReplacedFromMiddle(String currentString, int oldStringStartLocation, String oldString, String newString) {
        if (allowReplacement && newString.contains(what)) {
            return newString.replace(what, replacement);
        }
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringReplacedFromEnd(String currentString, int oldStringStartLocation, String oldString, String newString) {
        if (allowReplacement && newString.contains(what)) {
            return newString.replace(what, replacement);
        }
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringPastedFromStart(String currentString, String string) {
        if (allowReplacement && string.contains(what)) {
            return string.replace(what, replacement);
        }
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringPastedFromMiddle(String currentString, int oldStringStartLocation, String string) {
        if (allowReplacement && string.contains(what)) {
            return string.replace(what, replacement);
        }
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringPastedFromEnd(String currentString, int oldStringStartLocation, String string) {
        if (allowReplacement && string.contains(what)) {
            return string.replace(what, replacement);
        }
        else return PROCESSES_MODE_APPEND_ORIGINAL;
    }
}
