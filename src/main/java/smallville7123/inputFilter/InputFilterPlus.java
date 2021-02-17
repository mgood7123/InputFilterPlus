package smallville7123.inputFilter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.Log;

import androidx.annotation.NonNull;

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
 * <li>set text
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

        String currentString;
        String returned;
        Object mode;

        FilterInfo(CharSequence source, int start, int end, Spanned dest, int dstart, int dend){
            this.source = source;
            this.start = start;
            this.end = end;
            this.dest = dest;
            this.dstart = dstart;
            this.dend = dend;
        }

        /**
         * Returns a string representation of the object. In general, the
         * {@code toString} method returns a string that
         * "textually represents" this object. The result should
         * be a concise but informative representation that is easy for a
         * person to read.
         * It is recommended that all subclasses override this method.
         * <p>
         * The {@code toString} method for class {@code Object}
         * returns a string consisting of the name of the class of which the
         * object is an instance, the at-sign character `{@code @}', and
         * the unsigned hexadecimal representation of the hash code of the
         * object. In other words, this method returns a string equal to the
         * value of:
         * <blockquote>
         * <pre>
         * getClass().getName() + '@' + Integer.toHexString(hashCode())
         * </pre></blockquote>
         *
         * @return a string representation of the object.
         */
        @NonNull
        @Override
        public String toString() {
            return "mode = [" + modeToString(mode) + "], currentString = [" + currentString + "], returned { original = [" + (returned == PROCESSES_MODE_APPEND_ORIGINAL ? "true" : "false") + "], nothing = [" + (returned == PROCESSES_MODE_APPEND_NOTHING ? "true" : "false") + "] }, source = [" + source + "], start = [" + start + "], end = [" + end + "], dest = [" + dest + "], dstart = [" + dstart + "], dend = [" + dend + "]";
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

    static final Object
            set_text = new Object(),

            appended_letter_start = new Object(),
            appended_letter_middle = new Object(),
            appended_letter_end = new Object(),
    
            removed_letter_start = new Object(),
            removed_letter_middle = new Object(),
            removed_letter_end = new Object(),

            removed_string_start = new Object(),
            removed_string_middle = new Object(),
            removed_string_end = new Object(),

            replaced_letter_start = new Object(),
            replaced_letter_middle = new Object(),
            replaced_letter_end = new Object(),

            replaced_string_start = new Object(),
            replaced_string_middle = new Object(),
            replaced_string_end = new Object(),

            pasted_string_start = new Object(),
            pasted_string_middle = new Object(),
            pasted_string_end = new Object()
    ;

    String modeToString(Object object) {
        if (set_text.equals(object)) return "set text";
        else if (appended_letter_start.equals(object)) return "appended letter start";
        else if (appended_letter_middle.equals(object)) return "appended letter middle";
        else if (appended_letter_end.equals(object)) return "appended letter end";
        else if (removed_letter_start.equals(object)) return "removed letter start";
        else if (removed_letter_middle.equals(object)) return "removed letter middle";
        else if (removed_letter_end.equals(object)) return "removed letter end";
        else if (removed_string_start.equals(object)) return "removed string start";
        else if (removed_string_middle.equals(object)) return "removed string middle";
        else if (removed_string_end.equals(object)) return "removed string end";
        else if (replaced_letter_start.equals(object)) return "replaced letter start";
        else if (replaced_letter_middle.equals(object)) return "replaced letter middle";
        else if (replaced_letter_end.equals(object)) return "replaced letter end";
        else if (replaced_string_start.equals(object)) return "replaced string start";
        else if (replaced_string_middle.equals(object)) return "replaced string middle";
        else if (replaced_string_end.equals(object)) return "replaced string end";
        else if (pasted_string_start.equals(object)) return "pasted string start";
        else if (pasted_string_middle.equals(object)) return "pasted string middle";
        else if (pasted_string_end.equals(object)) return "pasted string end";
        else return null;
    }

    FilterInfo previous, current;

    private CharSequence processFilter(FilterInfo filterInfo) {
        previous = current;
        current = filterInfo;
        Log.d(TAG, "previous = [" + (previous) + "]");
        Log.d(TAG, "current = [" + (current) + "]");

        // process text input

        if (previous != null) {
            if (current.dest.length() == 0) {
                String oldString = previous.source.toString();
                String newString = current.source.toString();
                int oldLength = oldString.length();
                int newLength = newString.length();
                Log.d(TAG, "oldString = [" + (oldString) + "]");
                Log.d(TAG, "newString = [" + (newString) + "]");
                Log.d(TAG, "processFilter: [setText] criteria match");

                current.currentString = oldString;
                String string = onSetText(oldString, newString);

                current.mode = set_text;
                current.returned = string;

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
        }

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

                    current.currentString = oldString;
                    String string = onLetterAppendedToStart(oldString, newString);

                    current.mode = appended_letter_start;
                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                if (oldLength == filterInfo.dend) {
                    Log.d(TAG, "processFilter: appended letter (end)");

                    current.currentString = oldString;
                    String string = onLetterAppendedToEnd(oldString, filterInfo.dstart, newString);

                    current.mode = appended_letter_end;
                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: appended letter (middle)");

                current.currentString = oldString;
                String string = onLetterAppendedToMiddle(oldString, filterInfo.dstart, newString);

                current.mode = appended_letter_middle;
                current.returned = string;

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;
            }
            if (filterInfo.end == filterInfo.dend + 1) {
                Log.d(TAG, "processFilter: appended letter (end)");

                current.currentString = oldString;
                String string = onLetterAppendedToEnd(oldString, filterInfo.dstart, newString);

                current.mode = appended_letter_end;
                current.returned = string;

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

                    current.mode = removed_letter_start;
                    current.currentString = oldString;
                    String string = onLetterRemovedFromStart(oldString, String.valueOf(removed));

                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                if (filterInfo.end != filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed string (start)");

                    CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                    current.mode = removed_string_start;
                    current.currentString = oldString;
                    String string = onStringRemovedFromStart(oldString, String.valueOf(removed));

                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
            } else if (filterInfo.dend != oldLength) {
                if (filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed letter (middle)");

                    char removed = oldString.charAt(filterInfo.dstart);

                    current.mode = removed_letter_middle;
                    current.currentString = oldString;
                    String string = onLetterRemovedFromMiddle(oldString, filterInfo.dstart, String.valueOf(removed));

                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: removed string (middle)");

                CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                current.mode = removed_string_middle;
                current.currentString = oldString;
                String string = onStringRemovedFromMiddle(oldString, filterInfo.dstart, String.valueOf(removed));

                current.returned = string;

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                else return string;

            } else if (filterInfo.dend == oldLength) {
                if (filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: removed letter (end)");

                    char removed = oldString.charAt(filterInfo.dstart);

                    current.mode = removed_letter_end;
                    current.currentString = oldString;
                    String string = onLetterRemovedFromEnd(oldString, filterInfo.dstart, String.valueOf(removed));

                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
                    else return string;
                }
                Log.d(TAG, "processFilter: removed string (end)");

                CharSequence removed = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                current.mode = removed_string_end;
                current.currentString = oldString;
                String string = onStringRemovedFromEnd(oldString, filterInfo.dstart, String.valueOf(removed));

                current.returned = string;

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

                    current.mode = replaced_letter_start;
                    current.currentString = oldString;
                    String string = onLetterReplacedFromStart(oldString, replaceFrom, newString);

                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                    else return string;
                }
                Log.d(TAG, "processFilter: replaced string (start)");

                CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                current.mode = replaced_string_start;
                current.currentString = oldString;
                String string = onStringReplacedFromStart(oldString, String.valueOf(replaceFrom), newString);

                current.returned = string;

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                else return string;
            }
            if (filterInfo.dend != oldLength) {
                if (filterInfo.start == filterInfo.end - 1 && filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: replaced letter (middle)");

                    String replaceFrom = String.valueOf(oldString.charAt(filterInfo.dstart));

                    current.mode = replaced_letter_middle;
                    current.currentString = oldString;
                    String string = onLetterReplacedFromMiddle(oldString, filterInfo.dstart, replaceFrom, newString);

                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                    else return string;
                }
                Log.d(TAG, "processFilter: replaced string (middle)");

                CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                current.mode = replaced_string_middle;
                current.currentString = oldString;
                String string = onStringReplacedFromMiddle(oldString, filterInfo.dstart, String.valueOf(replaceFrom), newString);

                current.returned = string;

                if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                else return string;
            }
            if (filterInfo.dend == oldLength) {
                if (filterInfo.start == filterInfo.end - 1 && filterInfo.dstart == filterInfo.dend - 1) {
                    Log.d(TAG, "processFilter: replaced letter (end)");

                    String replaceFrom = String.valueOf(oldString.charAt(filterInfo.dstart));

                    current.mode = replaced_letter_end;
                    current.currentString = oldString;
                    String string = onLetterReplacedFromEnd(oldString, filterInfo.dstart, replaceFrom, newString);

                    current.returned = string;

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

                    current.mode = appended_letter_end;
                    current.currentString = oldString;
                    String string = onLetterAppendedToEnd(oldString, oldLength, letter);

                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return newString.substring(0, newLength-1);
                    else return newString.substring(0, newLength-1) + string;
                } else {
                    Log.d(TAG, "processFilter: replaced string (end)");

                    CharSequence replaceFrom = oldString.subSequence(filterInfo.dstart, filterInfo.dend);

                    current.mode = replaced_string_end;
                    current.currentString = oldString;
                    String string = onStringReplacedFromEnd(oldString, filterInfo.dstart, String.valueOf(replaceFrom), newString);

                    current.returned = string;

                    if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
                    else if (string == PROCESSES_MODE_APPEND_NOTHING) return replaceFrom;
                    else return string;
                }
            }
        }

        Log.d(TAG, "processFilter: [paste] criteria match");
        if (filterInfo.dstart == 0 && filterInfo.dend != oldLength) {
            Log.d(TAG, "processFilter: pasted string (start)");

            current.mode = pasted_string_start;
            current.currentString = oldString;
            String string = onStringPastedFromStart(oldString, newString);

            current.returned = string;

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        if (filterInfo.dend != oldLength) {
            Log.d(TAG, "processFilter: pasted string (middle)");

            current.mode = pasted_string_middle;
            current.currentString = oldString;
            String string = onStringPastedFromMiddle(oldString, filterInfo.dstart, newString);

            current.returned = string;

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        if (filterInfo.dend == oldLength) {
            Log.d(TAG, "processFilter: pasted string (end)");

            current.mode = pasted_string_end;
            current.currentString = oldString;
            String string = onStringPastedFromEnd(oldString, filterInfo.dstart, newString);

            current.returned = string;

            if (string == PROCESSES_MODE_APPEND_ORIGINAL) return null;
            else if (string == PROCESSES_MODE_APPEND_NOTHING) return "";
            else return string;
        }
        throw new RuntimeException("processFilter: unknown technique");
    }

    public String onSetText(String currentString, String string) {
        return PROCESSES_MODE_APPEND_ORIGINAL;
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
