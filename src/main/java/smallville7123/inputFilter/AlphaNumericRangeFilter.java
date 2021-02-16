package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that limits text to an alphanumeric range (0... 9... and A... to Z...), supports both lowercase and UPPERCASE letters
 * @see HexRangeFilter
 * @see NumericRangeFilter
 * @see AlphabeticalRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class AlphaNumericRangeFilter extends BaseAlphaNumericRangeFilter {

    /**
     * a version of {@link InputFilterPlus} that limits text to an alphanumeric range (0... 9... and A... to Z...), supports both lowercase and UPPERCASE letters
     *
     * @see HexRangeFilter
     * @see NumericRangeFilter
     * @see AlphabeticalRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public AlphaNumericRangeFilter() {
        this(null);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to an alphanumeric range (0... 9... and A... to Z...), supports both lowercase and UPPERCASE letters
     *
     * @param length the maximum allowed length,
     *               if this is null then there will be no length limit
     *
     * @see HexRangeFilter
     * @see NumericRangeFilter
     * @see AlphabeticalRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public AlphaNumericRangeFilter(Integer length) {
        this.max = null;
        this.length = length;
        this.range = new int[]{'0', '9', 'a', 'z', 'A', 'Z'};
        this.radix = 10;
    }
}