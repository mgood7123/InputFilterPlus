package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
 * @see AlphaNumericalRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class NumberRangeFilter extends AlphaNumericalRangeFilter {

    NumberRangeFilter() {
        throw new RuntimeException("A constructor that accepts arguments must be called instead");
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @param min the minimum allowed numerical value
     * @param max the maximum allowed numerical value
     *
     * @see NumberRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumberRangeFilter(int min, int max) {
        super(min, max, Integer.toString(max).length(), new int[] { '0', '9' }, 10);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @param min    the minimum allowed numerical value
     * @param max    the maximum allowed numerical value
     * @param length the maximum allowed length, this is only exposed for length correction
     *
     * @see NumberRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumberRangeFilter(int min, int max, int length) {
        super(min, max, length, new int[] { '0', '9' }, 10);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @param min   the minimum allowed numerical value
     * @param max   the maximum allowed numerical value
     * @param range the allowed input range, expressed as pairs on min, max
     *
     * @see HexRangeFilter
     * @see NumberRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumberRangeFilter(int min, int max, int[] range) {
        super(min, max, range);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @param min   the minimum allowed numerical value
     * @param max   the maximum allowed numerical value
     * @param range the allowed input range, expressed as pairs on min, max
     * @param radix the radix to use when parsing strings, see {@link Integer#parseInt(String, int)}
     *
     * @see HexRangeFilter
     * @see NumberRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumberRangeFilter(int min, int max, int[] range, int radix) {
        super(min, max, range, radix);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @param min    the minimum allowed numerical value
     * @param max    the maximum allowed numerical value
     * @param length the maximum allowed length, this is only exposed for length correction
     * @param range  the allowed input range, expressed as pairs on min, max
     *
     * @see NumberRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumberRangeFilter(int min, int max, int length, int[] range) {
        super(min, max, length, range, 10);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @param min    the minimum allowed numerical value
     * @param max    the maximum allowed numerical value
     * @param length the maximum allowed length, this is only exposed for length correction
     * @param range  the allowed input range, expressed as pairs on min, max
     * @param radix  the radix to use when parsing strings, see {@link Integer#parseInt(String, int)}
     *
     * @see NumberRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumberRangeFilter(int min, int max, int length, int[] range, int radix) {
        throw new RuntimeException("NumberRangeFilter does not support a custom radix");
    }
}