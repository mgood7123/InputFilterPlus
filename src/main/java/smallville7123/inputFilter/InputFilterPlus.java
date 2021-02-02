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
 * <li><strong><code>PROCESSES_MODE_APPEND_ORIGINAL</code></strong>
 * - return this if you want to append/replace with the original value
 * <li><strong><code>PROCESSES_MODE_APPEND_NOTHING</code></strong>
 * - return this if you want to reject the append/replacement all together
 * <li><strong><code>String</code></strong>
 * - return anything of type <strong><code>String</code></strong> if you want to append/replace with a custom string
 * <br>
 * <br>
 * the default return value for all callbacks is <strong><code>PROCESSES_MODE_APPEND_ORIGINAL</code></strong>
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
 * See <a href="https://github.com/mgood7123/InputFilterPlus#notes">Notes</a> for a list of all notes
 * as it is difficult to maintain notes in both <Strong><code>Github Markdown</code></Strong> and <Strong><code>Javadoc</code></Strong> format
 *
 * @see HexRangeFilter
 * @see NumericRangeFilter
 * @see AlphaNumericRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
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

        String oldString = filterInfo.dest.toString();
        String newString = filterInfo.source.toString();
        int oldLength = oldString.length();
        int newLength = newString.length();
        Log.d(TAG, "oldString = [" + (oldString) + "]");
        Log.d(TAG, "newString = [" + (newString) + "]");

        if (newLength == 1) {
            Log.d(TAG, "processFilter: [append] criteria match");
            if (filterInfo.dstart == filterInfo.dend) {
                if (filterInfo.dstart == 0) {
                    Log.d(TAG, "processFilter: appended letter (start)");

                    String string = onLetterAppendedToStart(oldString, newString);

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                if (oldLength == filterInfo.dend) {
                    Log.d(TAG, "processFilter: appended letter (end)");

                    String string = onLetterAppendedToEnd(oldString, filterInfo.dstart, newString);

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: appended letter (middle)");

                String string = onLetterAppendedToMiddle(oldString, filterInfo.dstart, newString);

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
            if (filterInfo.end == filterInfo.dend + 1) {
                Log.d(TAG, "processFilter: appended letter (end)");

                String string = onLetterAppendedToEnd(oldString, filterInfo.dstart, newString);

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

                    String string = onLetterRemovedFromStart(oldString, String.valueOf(removed));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                if (filterInfo.end != filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed string (start)");

                    CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                    String string = onStringRemovedFromStart(oldString, String.valueOf(removed));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
            } else if (filterInfo.dend != oldLength) {
                if (filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed letter (middle)");

                    char removed = oldString.charAt(filterInfo.dstart);

                    String string = onLetterRemovedFromMiddle(oldString, filterInfo.dstart, String.valueOf(removed));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: removed string (middle)");

                CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringRemovedFromMiddle(oldString, filterInfo.dstart, String.valueOf(removed));

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;

            } else if (filterInfo.dend == oldLength) {
                if (filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed letter (end)");

                    char removed = oldString.charAt(filterInfo.dstart);

                    String string = onLetterRemovedFromEnd(oldString, filterInfo.dstart, String.valueOf(removed));

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: removed string (end)");

                CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringRemovedFromEnd(oldString, filterInfo.dstart, String.valueOf(removed));

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

                    String replaceFrom = String.valueOf(oldString.charAt(filterInfo.dstart));

                    String string = onLetterReplacedFromStart(oldString, replaceFrom, newString);

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                    else return string;
                }
                Log.d(TAG, "processFilter: replaced string (start)");

                CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringReplacedFromStart(oldString, String.valueOf(replaceFrom), newString);

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                else return string;
            }
            if (filterInfo.dend != oldLength) {
                if (filterInfo.start == filterInfo.end - 1 && filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: replaced letter (middle)");

                    String replaceFrom = String.valueOf(oldString.charAt(filterInfo.dstart));

                    String string = onLetterReplacedFromMiddle(oldString, filterInfo.dstart, replaceFrom, newString);

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                    else return string;
                }
                Log.d(TAG, "processFilter: replaced string (middle)");

                CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                String string = onStringReplacedFromMiddle(oldString, filterInfo.dstart, String.valueOf(replaceFrom), newString);

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                else return string;
            }
            if (filterInfo.dend == oldLength) {
                if (filterInfo.start == filterInfo.end - 1 && filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: replaced letter (end)");

                    String replaceFrom = String.valueOf(oldString.charAt(filterInfo.dstart));

                    String string = onLetterReplacedFromEnd(oldString, filterInfo.dstart, replaceFrom, newString);

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                    else return string;
                }

                // Be smarter

                String end = oldString.substring(filterInfo.dstart);
                int endLength = end.length();

                if (endLength == newLength - 1 && newString.regionMatches(0, end, 0, endLength)) {
                    Log.d(TAG, "processFilter: appended letter (end)");

                    String letter = newString.substring(endLength);

                    String string = onLetterAppendedToEnd(oldString, oldLength, letter);

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return newString.substring(0, newLength-1);
                    else return newString.substring(0, newLength-1) + string;
                } else {
                    Log.d(TAG, "processFilter: replaced string (end)");

                    CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                    String string = onStringReplacedFromEnd(oldString, filterInfo.dstart, String.valueOf(replaceFrom), newString);

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                    else return string;
                }
            }
        }

        Log.d(TAG, "processFilter: [paste] criteria match");
        if (filterInfo.dstart == 0 && filterInfo.dend != oldLength) {
            Log.d(TAG, "processFilter: pasted string (start)");

            String string = onStringPastedFromStart(oldString, newString);

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        if (filterInfo.dend != oldLength) {
            Log.d(TAG, "processFilter: pasted string (middle)");

            String string = onStringPastedFromMiddle(oldString, filterInfo.dstart, newString);

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        if (filterInfo.dend == oldLength) {
            Log.d(TAG, "processFilter: pasted string (end)");

            String string = onStringPastedFromEnd(oldString, filterInfo.dstart, newString);

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        throw new RuntimeException("processFilter: unknown technique");
    }


    public String onLetterAppendedToStart(String currentString, String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterAppendedToMiddle(String currentString, int oldLetterStartLocation, String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterAppendedToEnd(String currentString, int oldLetterStartLocation, String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onLetterRemovedFromStart(String currentString, String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterRemovedFromMiddle(String currentString, int oldLetterStartLocation, String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterRemovedFromEnd(String currentString, int oldLetterStartLocation, String letter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onStringRemovedFromStart(String currentString, String string) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringRemovedFromMiddle(String currentString, int oldStringStartLocation, String string) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringRemovedFromEnd(String currentString, int oldStringStartLocation, String string) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onLetterReplacedFromStart(String currentString, String oldLetter, String newLetter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterReplacedFromMiddle(String currentString, int oldLetterStartLocation, String oldLetter, String newLetter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onLetterReplacedFromEnd(String currentString, int oldLetterStartLocation, String oldLetter, String newLetter) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }


    public String onStringReplacedFromStart(String currentString, String oldString, String newString) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringReplacedFromMiddle(String currentString, int oldStringStartLocation, String oldString, String newString) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringReplacedFromEnd(String currentString, int oldStringStartLocation, String oldString, String newString) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringPastedFromStart(String currentString, String string) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringPastedFromMiddle(String currentString, int oldStringStartLocation, String string) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    public String onStringPastedFromEnd(String currentString, int oldStringStartLocation, String string) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }
}
