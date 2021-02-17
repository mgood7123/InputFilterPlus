package smallville7123.inputFilter;

import java.util.Arrays;

/**
 * a version of {@link InputFilterPlus} that serves as the base for alphabetical and numerical filters
 *
 * @see HexRangeFilter
 * @see NumericRangeFilter
 * @see AlphabeticalRangeFilter
 * @see AlphaNumericRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class BaseAlphaNumericRangeFilter extends InputFilterPlus {
    Integer max;
    Integer length;
    public int[] range;
    int radix;

    public static String filter(String string, int min, int max) {
        return filter(string, new int[] { min, max });
    }

    public static String filter(String string, int[] minmax) {
        if (minmax.length == 2 || (minmax.length % 2) == 0) {
            char[] input = string.toCharArray();
            char output[] = new char[input.length + 1];
            int i = 0;
            for (char c : input) {
                int i1 = 0;
                while (i1 < minmax.length) {
                    int min = minmax[i1++];
                    int max = minmax[i1++];
                    if (c >= min && c <= max) {
                        output[i++] = c;
                    }
                }
            }
            if (i == 0) return null;
            return String.valueOf(Arrays.copyOf(output, i));
        } else {
            throw new RuntimeException("the variable 'minmax' must have an even number of items");
        }
    }

    public static String filterLoop(String filtered, Integer max, Integer maxLength, int radix, boolean filterOnce) {
        if (filtered == null) return null;
        int filterLength = filtered.length();
        if (filterLength == 0) return null;
        if (maxLength != null) if (filterLength > maxLength) filtered = filtered.substring(filterLength - maxLength);
        if (filtered.length() == 0) return null;
        int filteredInt = Integer.parseInt(filtered, radix);
        if (max == null || filteredInt <= max) return filtered;
        // max is not null if this point is reached
        if (filterOnce) return null;
        while (true) {
            filtered = filtered.substring(1);
            if (filtered.length() == 0) return null;
            filteredInt = Integer.parseInt(filtered, radix);
            if (filteredInt <= max) return filtered;
        }
    }

    public static String filterLoop(String filtered, Integer max, Integer maxLength, int radix) {
        return filterLoop(filtered, max, maxLength, radix, false);
    }

    public static String trimLeadingZeroes(String input) {
        if (!isAll(input, '0')) {
            while (input.charAt(0) == '0') {
                input = input.substring(1);
            }
        }
        return input;
    }

    public static boolean isAll(String input, char character) {
        int len = input.length();
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) != character) return false;
        }
        return true;
    }

    int stringLength = 0;

    @Override
    public String onSetText(String currentString, String string) {
        stringLength = 0;
        if (length != null) if (stringLength >= length) return PROCESSES_MODE_APPEND_NOTHING;

        // filter out all characters that do not match the range '0' to '9'
        String filtered = filter(string, range);

        // return if newString has no numbers in it
        if (filtered == null) return PROCESSES_MODE_APPEND_NOTHING;
        if (length == null && max == null) return filtered;

        filtered = filterLoop(filtered, max, length, radix);
        if (filtered != null) {
            int oldStringLength = stringLength;
            stringLength += filtered.length();
            if (stringLength > length) {
                stringLength = oldStringLength;
                return PROCESSES_MODE_APPEND_NOTHING;
            }
            return filtered;
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onLetterAppendedToStart(String currentString, String letter) {
        if (length != null) if (stringLength >= length) return PROCESSES_MODE_APPEND_NOTHING;
        String filteredLetter = filter(letter, range);
        if (filteredLetter != null) {
            if (length == null && max == null) return filteredLetter;
            if (filterLoop(filteredLetter + currentString, max, length, radix, true) != null) {
                stringLength++;
                return filteredLetter;
            }
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onLetterAppendedToMiddle(String currentString, int oldLetterStartLocation, String letter) {
        if (length != null) if (stringLength >= length) return PROCESSES_MODE_APPEND_NOTHING;
        String filteredLetter = filter(letter, range);
        if (filteredLetter != null) {
            if (length == null && max == null) return filteredLetter;
            if (filterLoop(currentString.substring(0, oldLetterStartLocation) + filteredLetter + currentString.substring(oldLetterStartLocation), max, length, radix, true) != null) {
                stringLength++;
                return filteredLetter;
            }
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onLetterAppendedToEnd(String currentString, int oldLetterStartLocation, String letter) {
        if (length != null) if (stringLength >= length) return PROCESSES_MODE_APPEND_NOTHING;
        String filteredLetter = filter(letter, range);
        if (filteredLetter != null) {
            if (length == null && max == null) return filteredLetter;
            if (filterLoop(currentString + filteredLetter, max, length, radix, true) != null) {
                stringLength++;
                return filteredLetter;
            }
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onLetterRemovedFromStart(String currentString, String letter) {
        stringLength--;
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterRemovedFromMiddle(String currentString, int oldLetterStartLocation, String letter) {
        stringLength--;
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterRemovedFromEnd(String currentString, int oldLetterStartLocation, String letter) {
        stringLength--;
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringRemovedFromStart(String currentString, String string) {
        stringLength -= string.length();
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringRemovedFromMiddle(String currentString, int oldStringStartLocation, String string) {
        stringLength -= string.length();
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onStringRemovedFromEnd(String currentString, int oldStringStartLocation, String string) {
        stringLength -= string.length();
        return PROCESSES_MODE_APPEND_ORIGINAL;
    }

    @Override
    public String onLetterReplacedFromStart(String currentString, String oldLetter, String newLetter) {
        // string length is not modified
        String filteredLetter = filter(newLetter, range);
        if (filteredLetter != null) {
            if (max == null) return filteredLetter;
            if (filterLoop(filteredLetter + currentString.substring(1), max, length, radix, true) == null) {
                return PROCESSES_MODE_APPEND_NOTHING;
            }
            return filteredLetter;
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onLetterReplacedFromMiddle(String currentString, int oldLetterStartLocation, String oldLetter, String newLetter) {
        // string length is not modified
        String filteredLetter = filter(newLetter, range);
        if (filteredLetter != null) {
            if (max == null) return filteredLetter;
            if (filterLoop(currentString.substring(0, oldLetterStartLocation) + filteredLetter + currentString.substring(oldLetterStartLocation + 1), max, length, radix, true) == null) {
                return PROCESSES_MODE_APPEND_NOTHING;
            }
            return filteredLetter;
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onLetterReplacedFromEnd(String currentString, int oldLetterStartLocation, String oldLetter, String newLetter) {
        // string length is not modified
        String filteredLetter = filter(newLetter, range);
        if (filteredLetter != null) {
            if (max == null) return filteredLetter;
            if (filterLoop(currentString.substring(0, oldLetterStartLocation) + filteredLetter, max, length, radix, true) == null) {
                return PROCESSES_MODE_APPEND_NOTHING;
            }
            return filteredLetter;
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onStringReplacedFromStart(String currentString, String oldString, String newString) {
        // filter out all characters that do not match the range '0' to '9'
        String filtered = filter(newString, range);

        // return if newString has no numbers in it
        if (filtered == null) return PROCESSES_MODE_APPEND_NOTHING;
        if (length == null && max == null) return filtered;

        // copy currentString into REP so we can safely modify it
        String REP = new String(currentString);

        // remove oldString from REP
        int index = REP.indexOf(oldString);
        REP = REP.substring(0, index) + REP.substring(index + oldString.length());

        // copy REP to SAVED, we will need this later
        String SAVED = new String(REP);

        // add filtered newString to REP
        REP = filtered + REP;

        // reformat REP to range between 0 and 255
        REP = filterLoop(REP, max, length, radix);

        // remove SAVED from the end of REP
        REP = REP.substring(0, REP.lastIndexOf(SAVED));

        int newLength = REP.length();
        if (newLength == 0) return PROCESSES_MODE_APPEND_NOTHING;

        int oldLength = oldString.length();

        if (oldLength > newLength) {
            stringLength -= oldLength - newLength;
        } else if (oldLength < newLength) {
            stringLength += newLength - oldLength;
        }

        return REP;
    }

    @Override
    public String onStringReplacedFromMiddle(String currentString, int oldStringStartLocation, String oldString, String newString) {
        // filter out all characters that do not match the range '0' to '9'
        String filtered = filter(newString, range);

        // return if newString has no numbers in it
        if (filtered == null) return PROCESSES_MODE_APPEND_NOTHING;
        if (length == null && max == null) return filtered;

        // copy filtered newString to REP
        String start = currentString.substring(0, oldStringStartLocation);
        int startLength = start.length();
        String end = currentString.substring(oldStringStartLocation + oldString.length());
        int endLength = end.length();
        int allowedMiddleLength = length - (startLength + endLength);
        String REP = filtered;

        if (REP.length() > allowedMiddleLength) {
            REP = REP.substring(REP.length() - allowedMiddleLength);
        }

        // reformat REP to range between 0 and 255
        REP = trimLeadingZeroes(filterLoop(REP, max, length, radix));

        int newLength = REP.length();
        if (newLength == 0) return PROCESSES_MODE_APPEND_NOTHING;

        if (filterLoop(start + REP + end, max, length, radix, true) == null) return PROCESSES_MODE_APPEND_NOTHING;

        int oldLength = oldString.length();

        if (oldLength > newLength) {
            stringLength -= oldLength - newLength;
        } else if (oldLength < newLength) {
            stringLength += newLength - oldLength;
        }

        return REP;
    }

    @Override
    public String onStringReplacedFromEnd(String currentString, int oldStringStartLocation, String oldString, String newString) {
        // filter out all characters that do not match the range '0' to '9'
        String filtered = filter(newString, range);

        // return if newString has no numbers in it
        if (filtered == null) return PROCESSES_MODE_APPEND_NOTHING;
        if (length == null && max == null) return filtered;

        // remove oldString from current string and store the result in REP
        String start = currentString.substring(0, oldStringStartLocation);
        String REP = new String(start);

        // add filtered newString to REP
        REP = REP + filtered;

        if (REP.length() <= length) {
            // reformat REP to range between 0 and 255
            REP = trimLeadingZeroes(filterLoop(REP, max, length, radix));

            // remove SAVED from the end of REP
            REP = REP.substring(oldStringStartLocation);
            if (REP.length() > 1) {
                REP = trimLeadingZeroes(REP);
            }
        } else {
            // reformat REP to range between 0 and 255
            REP = trimLeadingZeroes(filterLoop(REP, max, length, radix));
        }

        if (((currentString.length() - oldString.length()) + REP.length()) > length) {
            REP = REP.substring(length - (currentString.length() - (REP.length() - oldString.length())));
        }

        int newLength = REP.length();
        if (newLength == 0) return PROCESSES_MODE_APPEND_NOTHING;

        if (filterLoop(start + REP, max, length, radix, true) == null) return PROCESSES_MODE_APPEND_NOTHING;

        int oldLength = oldString.length();

        if (oldLength > newLength) {
            stringLength -= oldLength - newLength;
        } else if (oldLength < newLength) {
            stringLength += newLength - oldLength;
        }

        return REP;
    }

    @Override
    public String onStringPastedFromStart(String currentString, String string) {
        if (length != null) if (stringLength >= length) return PROCESSES_MODE_APPEND_NOTHING;

        // filter out all characters that do not match the range '0' to '9'
        String filtered = filter(string, range);

        // return if newString has no numbers in it
        if (filtered == null) return PROCESSES_MODE_APPEND_NOTHING;
        if (length == null && max == null) return filtered;

        filtered = filterLoop(filtered, max, length, radix);
        if ((currentString.length() + filtered.length()) > length) {
            filtered = filtered.substring(filtered.length() - (length - currentString.length()));
        }
        if (filtered != null) {
            int oldStringLength = stringLength;
            stringLength += filtered.length();
            if (stringLength > length) {
                stringLength = oldStringLength;
                return PROCESSES_MODE_APPEND_NOTHING;
            }
            return filtered;
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onStringPastedFromMiddle(String currentString, int oldStringStartLocation, String string) {
        if (length != null) if (stringLength >= length) return PROCESSES_MODE_APPEND_NOTHING;

        // filter out all characters that do not match the range '0' to '9'
        String filtered = filter(string, range);

        // return if newString has no numbers in it
        if (filtered == null) return PROCESSES_MODE_APPEND_NOTHING;
        if (length == null && max == null) return filtered;

        filtered = filterLoop(filtered, max, length, radix);
        if ((currentString.length() + filtered.length()) > length) {
            filtered = filtered.substring(filtered.length() - (length - currentString.length()));
        }
        if (filtered != null) {
            int oldStringLength = stringLength;
            stringLength += filtered.length();
            if (stringLength > length) {
                stringLength = oldStringLength;
                return PROCESSES_MODE_APPEND_NOTHING;
            }
            return filtered;
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }

    @Override
    public String onStringPastedFromEnd(String currentString, int oldStringStartLocation, String string) {
        if (length != null) if (stringLength >= length) return PROCESSES_MODE_APPEND_NOTHING;

        // filter out all characters that do not match the range '0' to '9'
        String filtered = filter(string, range);

        // return if newString has no numbers in it
        if (filtered == null) return PROCESSES_MODE_APPEND_NOTHING;
        if (length == null && max == null) return filtered;

        filtered = filterLoop(filtered, max, length, radix);
        if ((currentString.length() + filtered.length()) > length) {
            filtered = filtered.substring(filtered.length() - (length - currentString.length()));
        }
        if (filtered != null) {
            int oldStringLength = stringLength;
            stringLength += filtered.length();
            if (stringLength > length) {
                stringLength = oldStringLength;
                return PROCESSES_MODE_APPEND_NOTHING;
            }
            return filtered;
        }
        return PROCESSES_MODE_APPEND_NOTHING;
    }
}
