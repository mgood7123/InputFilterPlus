package smallville7123.inputFilter;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
 * @see NumberRangeFilter
 * @see AlphaNumericalRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class HexRangeFilter extends AlphaNumericalRangeFilter {

    HexRangeFilter() {
        throw new RuntimeException("A constructor that accepts arguments must be called instead");
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @param min the minimum allowed hexadecimal value
     * @param max the maximum allowed hexadecimal value
     *
     * @see NumberRangeFilter
     * @see AlphaNumericalRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter(int min, int max) {
        super(min, max, Integer.toHexString(max).length(), new int[] { '0', '9', 'a', 'f', 'A', 'F' }, 16);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @param min    the minimum allowed hexadecimal value
     * @param max    the maximum allowed hexadecimal value
     * @param length the maximum allowed length, this is only exposed for length correction
     *
     * @see NumberRangeFilter
     * @see AlphaNumericalRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter(int min, int max, int length) {
        super(min, max, length, new int[] { '0', '9', 'a', 'f', 'A', 'F' }, 16);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @param min   the minimum allowed hexadecimal value
     * @param max   the maximum allowed hexadecimal value
     * @param range the allowed input range, expressed as pairs on min, max
     *
     * @see NumberRangeFilter
     * @see AlphaNumericalRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter(int min, int max, int[] range) {
        super(min, max, Integer.toHexString(max).length(), range, 16);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @param min   the minimum allowed hexadecimal value
     * @param max   the maximum allowed hexadecimal value
     * @param range the allowed input range, expressed as pairs on min, max
     * @param radix the radix to use when parsing strings, see {@link Integer#parseInt(String, int)}
     *
     * @see NumberRangeFilter
     * @see AlphaNumericalRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter(int min, int max, int[] range, int radix) {
        super(min, max, Integer.toHexString(max).length(), range, radix);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @param min    the minimum allowed hexadecimal value
     * @param max    the maximum allowed hexadecimal value
     * @param length the maximum allowed length, this is only exposed for length correction
     * @param range  the allowed input range, expressed as pairs on min, max
     *
     * @see NumberRangeFilter
     * @see AlphaNumericalRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter(int min, int max, int length, int[] range) {
        super(min, max, length, range, 16);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @param min    the minimum allowed hexadecimal value
     * @param max    the maximum allowed hexadecimal value
     * @param length the maximum allowed length, this is only exposed for length correction
     * @param range  the allowed input range, expressed as pairs on min, max
     * @param radix  the radix to use when parsing strings, see {@link Integer#parseInt(String, int)}
     *
     * @see NumberRangeFilter
     * @see AlphaNumericalRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter(int min, int max, int length, int[] range, int radix) {
        throw new RuntimeException("HexRangeFilter does not support a custom radix");
    }
}