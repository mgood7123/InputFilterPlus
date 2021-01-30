package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that can replace text
 * @see smallville7123.inputFilter.SpaceFilter
 * @see smallville7123.inputFilter.BackSpaceFilter
 * @see smallville7123.inputFilter.InputFilterPlus
 */
public class ReplacementFilter extends InputFilterPlus {

    private final boolean allowReplacement;
    private final String what;
    private final String replacement;

    public ReplacementFilter(boolean allowReplacement, String what, String replacement) {
        this.allowReplacement = allowReplacement;
        this.what = what;
        this.replacement = replacement;
    }

    @Override
    public String onLetterAppendedToStart(String letter) {
        if (!allowReplacement && letter.contains(what)) return replacement;
        else return super.onLetterAppendedToStart(letter);
    }

    @Override
    public String onLetterAppendedToMiddle(String letter) {
        if (!allowReplacement && letter.contains(what)) return replacement;
        else return super.onLetterAppendedToMiddle(letter);
    }

    @Override
    public String onLetterAppendedToEnd(String letter) {
        if (!allowReplacement && letter.contains(what)) return replacement;
        else return super.onLetterAppendedToEnd(letter);
    }

    @Override
    public String onLetterReplacedFromStart(String oldLetter, String newLetter) {
        if (!allowReplacement && newLetter.contains(what)) return replacement;
        else return super.onLetterReplacedFromStart(oldLetter, newLetter);
    }

    @Override
    public String onLetterReplacedFromMiddle(String oldLetter, String newLetter) {
        if (!allowReplacement && newLetter.contains(what)) return replacement;
        else return super.onLetterReplacedFromMiddle(oldLetter, newLetter);
    }

    @Override
    public String onLetterReplacedFromEnd(String oldLetter, String newLetter) {
        if (!allowReplacement && newLetter.contains(what)) return replacement;
        else return super.onLetterReplacedFromEnd(oldLetter, newLetter);
    }

    @Override
    public String onStringReplacedFromStart(String oldString, String newString) {
        if (!allowReplacement && newString.contains(what)) {
            return newString.replace(what, replacement);
        }
        else return super.onStringReplacedFromStart(oldString, newString);
    }

    @Override
    public String onStringReplacedFromMiddle(String oldString, String newString) {
        if (!allowReplacement && newString.contains(what)) {
            return newString.replace(what, replacement);
        }
        else return super.onStringReplacedFromMiddle(oldString, newString);
    }

    @Override
    public String onStringReplacedFromEnd(String oldString, String newString) {
        if (!allowReplacement && newString.contains(what)) {
            return newString.replace(what, replacement);
        }
        else return super.onStringReplacedFromEnd(oldString, newString);
    }

    @Override
    public String onLetterPastedFromStart(String letter) {
        if (!allowReplacement && letter.contains(what)) return replacement;
        else return super.onLetterPastedFromStart(letter);
    }

    @Override
    public String onLetterPastedFromMiddle(String letter) {
        if (!allowReplacement && letter.contains(what)) return replacement;
        else return super.onLetterPastedFromMiddle(letter);
    }

    @Override
    public String onLetterPastedFromEnd(String letter) {
        if (!allowReplacement && letter.contains(what)) return replacement;
        else return super.onLetterPastedFromEnd(letter);
    }

    @Override
    public String onStringPastedFromStart(String string) {
        if (!allowReplacement && string.contains(what)) {
            return string.replace(what, replacement);
        }
        else return super.onStringPastedFromStart(string);
    }

    @Override
    public String onStringPastedFromMiddle(String string) {
        if (!allowReplacement && string.contains(what)) {
            return string.replace(what, replacement);
        }
        else return super.onStringPastedFromMiddle(string);
    }

    @Override
    public String onStringPastedFromEnd(String string) {
        if (!allowReplacement && string.contains(what)) {
            return string.replace(what, replacement);
        }
        else return super.onStringPastedFromEnd(string);
    }
}
