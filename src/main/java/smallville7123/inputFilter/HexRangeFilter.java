package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
 *
 * @see NumericRangeFilter
 * @see AlphabeticalRangeFilter
 * @see AlphaNumericRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class HexRangeFilter extends BaseAlphaNumericRangeFilter {

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @see NumericRangeFilter
     * @see AlphabeticalRangeFilter
     * @see AlphaNumericRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter() {
        this(null);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @param max the maximum allowed hexadecimal value,
     *            if this is null then there will be no length limit
     *
     * @see NumericRangeFilter
     * @see AlphabeticalRangeFilter
     * @see AlphaNumericRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter(Integer max) {
        this.max = max;
        this.length = max == null ? null : Integer.toHexString(max).length();
        this.range = new int[] { '0', '9', 'a', 'f', 'A', 'F' };
        this.radix = 16;
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to a hexadecimal range (0... 9... and A... to F...), supports both lowercase and UPPERCASE letters
     *
     * @param length the maximum allowed length,
     *               if this is null then there will be no length limit
     *
     * @see NumericRangeFilter
     * @see AlphabeticalRangeFilter
     * @see AlphaNumericRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public HexRangeFilter(Integer unused, Integer length) {
        this.max = null;
        this.length = length;
        this.range = new int[] { '0', '9', 'a', 'f', 'A', 'F' };
        this.radix = 16;
    }
}