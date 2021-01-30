package smallville7123.inputFilter;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

/**
 * an InputFilter which offers a vast array of callbacks in place of
 * {@link #filter(CharSequence, int, int, Spanned, int, int) InputFilter#filter}
 * <br>
 * <br>
 * <strong>Callback return values</strong>
 * <br>
 * <br>
 * these should be self explanatory
 * <br>
 * <br>
 * all callbacks accept either
 * <br>
 * <br>
 * <strong>PROCESSES_MODE_APPEND_ORIGINAL</strong>
 * <br>
 * <br>
 * or
 * <br>
 * <br>
 * <strong>PROCESSES_MODE_APPEND_NOTHING</strong>
 * <br>
 * <br>
 * <br>
 * <br>
 * <strong>Callbacks</strong>
 * <br>
 * <br>
 * <li>appended letter (start)
 * <li>appended letter (middle)
 * <li>appended letter (end)
 * <br>
 * <br>
 * <li>removed letter (start)
 * <li>removed letter (middle)
 * <li>removed letter (end)
 * <br>
 * <br>
 * <li>removed string (start)
 * <li>removed string (middle)
 * <li>removed string (end)
 * <br>
 * <br>
 * <li>replaced letter (start)
 * <li>replaced letter (middle)
 * <li>replaced letter (end)
 * <br>
 * <br>
 * <li>replaced string (start)
 * <li>replaced string (middle)
 * <li>replaced string (end)
 * <br>
 * <br>
 * <li>pasted letter (start)
 * <li>pasted letter (middle)
 * <li>pasted letter (end)
 * <br>
 * <br>
 * <li>pasted string (start)
 * <li>pasted string (middle)
 * <li>pasted string (end)
 * <br>
 * <br>
 * <Strong>NOTE:</Strong>
 * <br>
 * <br>
 * for some unknown reason, the
 * <br>
 * <br>
 * <strong>select text, and then press space</strong>
 * <br>
 * <br>
 * is interpreted not as a single
 * {@link #filter(CharSequence, int, int, Spanned, int, int) InputFilter#filter}
 * <strong>replaced string</strong>
 * <br>
 * <br>
 * but instead as a
 * {@link #filter(CharSequence, int, int, Spanned, int, int) InputFilter#filter}
 * <strong>removed string</strong>
 * followed by a
 * {@link #filter(CharSequence, int, int, Spanned, int, int) InputFilter#filter}
 * <strong>appended letter</strong>
 * <br>
 * <br>
 * <Strong>NOTE:</Strong>
 * <br>
 * <br>
 * text that contains spaces behaves in a non conforming way with
 * <br>
 * <br>
 * {@link #filter(CharSequence, int, int, Spanned, int, int)}
 * <br>
 * <br>
 * <Strong>NOTE:</Strong>
 * <br>
 * <br>
 * since <Strong>pasting text</Strong>, and <Strong>glide/swipe text input</Strong>,
 * emit the same functionality, it is impossible to distinguish between the two
 * <br>
 * <br>
 * for example:
 * <br>
 * <br>
 * <li>glide/swipe  : <Strong>source = [focus], start = [0], end = [5], dest = [lll], dstart = [2], dend = [2]</Strong>
 * <li>paste        : <Strong>source = [focus], start = [0], end = [5], dest = [lll], dstart = [2], dend = [2]</Strong>
 * <br>
 * <br>
 * for this reason it is also impossible to append a string since
 * this functionality is equivalent to <Strong>paste string</Strong>
 * <br>
 * <br>
 * this is also true for <Strong>remove string</Strong> and <Strong>cut string</Strong>
 * <br>
 * <br>
 * @see smallville7123.inputFilter.ReplacementFilter
 * @see smallville7123.inputFilter.SpaceFilter
 * @see smallville7123.inputFilter.BackSpaceFilter
 */
public class InputFilterPlus implements InputFilter {

    private static final String TAG = "InputFilterPlus";

    class FilterInfo {
        CharSequence source;
        int start;
        int end;
        Spanned dest;
        int dstart;
        int dend;

        FilterInfo(CharSequence source, int start, int end, Spanned dest, int dstart, int dend){
            Log.d(TAG, "FilterInfo() called with: source = [" + source + "], start = [" + start + "], end = [" + end + "], dest = [" + dest + "], dstart = [" + dstart + "], dend = [" + dend + "]");
            this.source = source;
            this.start = start;
            this.end = end;
            this.dest = dest;
            this.dstart = dstart;
            this.dend = dend;
        }
    }

    @Override
    public final CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        return processFilter(new FilterInfo(source, start, end, dest, dstart, dend));
    }

    @SuppressWarnings("StringOperationCanBeSimplified")
    // new String() required for string reference equality testing
    public static final String PROCESSES_MODE_APPEND_ORIGINAL = new String();

    @SuppressWarnings("StringOperationCanBeSimplified")
    // new String() required for string reference equality testing
    public static final String PROCESSES_MODE_APPEND_NOTHING = new String();

    private final CharSequence processFilter(FilterInfo filterInfo) {

        Spanned oldString = filterInfo.dest;
        CharSequence newString = filterInfo.source;
        int oldLength = oldString.length();
        int newLength = newString.length();
        Log.d(TAG, "oldString = [" + (oldString) + "]");
        Log.d(TAG, "newString = [" + (newString) + "]");

        if (newLength == 1) {
            Log.d(TAG, "processFilter: [append] criteria match");
            if (filterInfo.dstart == filterInfo.dend) {
                if (filterInfo.dstart == 0) {
                    Log.d(TAG, "processFilter: appended letter (start)");

                    String string = onLetterAppendedToStart(String.valueOf(newString));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: appended letter (middle)");

                String string = onLetterAppendedToMiddle(String.valueOf(newString));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
            if (filterInfo.end == filterInfo.dend + 1) {
                Log.d(TAG, "processFilter: appended letter (end)");

                String string = onLetterAppendedToEnd(String.valueOf(newString));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
        }

        if (newLength == 0) {
            Log.d(TAG, "processFilter: [remove] criteria match");
            if (filterInfo.dstart == 0) {
                if (filterInfo.dend == 1) {
                    Log.d(TAG, "processFilter: removed letter (start)");

                    CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                    String string = onLetterRemovedFromStart(String.valueOf(removed));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                if (filterInfo.end != filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed string (start)");

                    CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                    String string = onStringRemovedFromStart(String.valueOf(removed));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
            } else if (filterInfo.dend != oldLength) {
                if (filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed letter (middle)");

                    char removed = oldString.charAt(filterInfo.dstart);

                    String string = onLetterRemovedFromMiddle(String.valueOf(removed));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: removed string (middle)");

                CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringRemovedFromMiddle(String.valueOf(removed));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;

            } else if (filterInfo.dend == oldLength) {
                if (filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed letter (end)");

                    char removed = oldString.charAt(filterInfo.dstart);

                    String string = onLetterRemovedFromEnd(String.valueOf(removed));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: removed string (end)");

                CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringRemovedFromEnd(String.valueOf(removed));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
        }

        if (filterInfo.dstart != filterInfo.dend) {
            Log.d(TAG, "processFilter: [replace] criteria match");
            if (filterInfo.dstart == 0 && filterInfo.dend != oldLength) {
                if (filterInfo.start == filterInfo.end - 1 && filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: replaced letter (start)");

                    char replaceFrom = oldString.charAt(filterInfo.dstart);

                    String string = onLetterReplacedFromStart(String.valueOf(replaceFrom), String.valueOf(newString));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: replaced string (start)");

                CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringReplacedFromStart(String.valueOf(replaceFrom), String.valueOf(newString));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
            if (filterInfo.dend != oldLength) {
                if (filterInfo.start == filterInfo.end - 1 && filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: replaced letter (middle)");

                    char replaceFrom = oldString.charAt(filterInfo.dstart);

                    String string = onLetterReplacedFromMiddle(String.valueOf(replaceFrom), String.valueOf(newString));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: replaced string (middle)");

                CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringReplacedFromMiddle(String.valueOf(replaceFrom), String.valueOf(newString));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
            if (filterInfo.dend == oldLength) {
                if (filterInfo.start == filterInfo.end - 1 && filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: replaced letter (end)");

                    char replaceFrom = oldString.charAt(filterInfo.dstart);

                    String string = onLetterReplacedFromEnd(String.valueOf(replaceFrom), String.valueOf(newString));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: replaced string (end)");

                CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringReplacedFromEnd(String.valueOf(replaceFrom), String.valueOf(newString));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
        }

        Log.d(TAG, "processFilter: [paste] criteria match");
        if (filterInfo.dstart == 0 && filterInfo.dend != oldLength) {
            if (newLength == 1) {
                Log.d(TAG, "processFilter: pasted letter (start)");

                String string = onLetterPastedFromStart(String.valueOf(newString));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
            Log.d(TAG, "processFilter: pasted string (start)");

            String string = onStringPastedFromStart(String.valueOf(newString));

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        if (filterInfo.dend != oldLength) {
            if (newLength == 1) {
                Log.d(TAG, "processFilter: pasted letter (middle)");

                String string = onLetterPastedFromMiddle(String.valueOf(newString));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
            Log.d(TAG, "processFilter: pasted string (middle)");

            String string = onStringPastedFromMiddle(String.valueOf(newString));

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        if (filterInfo.dend == oldLength) {
            if (newLength == 1) {
                Log.d(TAG, "processFilter: pasted letter (end)");

                String string = onLetterPastedFromEnd(String.valueOf(newString));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
            Log.d(TAG, "processFilter: pasted string (end)");

            String string = onStringPastedFromEnd(String.valueOf(newString));

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        throw new RuntimeException("processFilter: unknown technique");
    }


    public String onLetterAppendedToStart(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterAppendedToMiddle(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterAppendedToEnd(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onLetterRemovedFromStart(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterRemovedFromMiddle(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterRemovedFromEnd(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onStringRemovedFromStart(String String) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringRemovedFromMiddle(String String) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringRemovedFromEnd(String String) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onLetterReplacedFromStart(String oldLetter, String newLetter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterReplacedFromMiddle(String oldLetter, String newLetter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterReplacedFromEnd(String oldLetter, String newLetter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onStringReplacedFromStart(String oldString, String newString) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringReplacedFromMiddle(String oldString, String newString) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringReplacedFromEnd(String oldString, String newString) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onLetterPastedFromStart(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterPastedFromMiddle(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterPastedFromEnd(String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onStringPastedFromStart(String String) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringPastedFromMiddle(String String) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringPastedFromEnd(String String) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }
}
