package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
 *
 * @see HexRangeFilter
 * @see AlphabeticalRangeFilter
 * @see AlphaNumericRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class NumericRangeFilter extends BaseAlphaNumericRangeFilter {

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @see HexRangeFilter
     * @see AlphabeticalRangeFilter
     * @see AlphaNumericRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumericRangeFilter() {
        this(null);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @param max the maximum allowed numerical value,
     *            if this is null then there will be no length limit
     *
     * @see HexRangeFilter
     * @see AlphabeticalRangeFilter
     * @see AlphaNumericRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumericRangeFilter(Integer max) {
        this.max = max;
        this.length = max == null ? null : Integer.toString(max).length();
        this.range = new int[] { '0', '9' };
        this.radix = 10;
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a numerical range (0... to 9...)
     *
     * @param length the maximum allowed length,
     *               if this is null then there will be no length limit
     *
     * @see HexRangeFilter
     * @see AlphabeticalRangeFilter
     * @see AlphaNumericRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public NumericRangeFilter(Integer unused, Integer length) {
        this.max = null;
        this.length = length;
        this.range = new int[] { '0', '9' };
        this.radix = 10;
    }
}