package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that can disallow backspaces
 * @see smallville7123.inputFilter.ReplacementFilter
 * @see smallville7123.inputFilter.SpaceFilter
 * @see smallville7123.inputFilter.InputFilterPlus
 */
public class BackSpaceFilter extends InputFilterPlus {

    private final boolean allowBackspaces;

    public BackSpaceFilter(boolean allowBackspaces) {
        this.allowBackspaces = allowBackspaces;
    }

    @Override
    public String onLetterRemovedFromStart(String letter) {
        if (!allowBackspaces) return letter;
        else return super.onLetterRemovedFromStart(letter);
    }

    @Override
    public String onLetterRemovedFromMiddle(String letter) {
        if (!allowBackspaces) return letter;
        else return super.onLetterRemovedFromMiddle(letter);
    }

    @Override
    public String onLetterRemovedFromEnd(String letter) {
        if (!allowBackspaces) return letter;
        else return super.onLetterRemovedFromEnd(letter);
    }

    @Override
    public String onStringRemovedFromStart(String String) {
        if (!allowBackspaces) return String;
        else return super.onStringRemovedFromStart(String);
    }

    @Override
    public String onStringRemovedFromMiddle(String String) {
        if (!allowBackspaces) return String;
        else return super.onStringRemovedFromMiddle(String);
    }

    @Override
    public String onStringRemovedFromEnd(String String) {
        if (!allowBackspaces) return String;
        else return super.onStringRemovedFromEnd(String);
    }
}
