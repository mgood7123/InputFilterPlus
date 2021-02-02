package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that limits text to an alphabetical range (A... to Z...), supports both lowercase and UPPERCASE letters
 *
 * @see HexRangeFilter
 * @see NumericRangeFilter
 * @see AlphaNumericRangeFilter
 * @see SpaceFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class AlphabeticalRangeFilter extends BaseAlphaNumericRangeFilter {

    /**
     * a version of {@link InputFilterPlus} that limits text to an alphabetical range (A... to Z...), supports both lowercase and UPPERCASE letters
     *
     * @see HexRangeFilter
     * @see NumericRangeFilter
     * @see AlphaNumericRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public AlphabeticalRangeFilter() {
        this(null);
    }

    /**
     * a version of {@link InputFilterPlus} that limits text to an alphabetical range (A... to Z...), supports both lowercase and UPPERCASE letters
     *
     * @param length the maximum allowed length,
     *               if this is null then there will be no length limit
     *
     * @see HexRangeFilter
     * @see NumericRangeFilter
     * @see AlphaNumericRangeFilter
     * @see ReplacementFilter
     * @see SpaceFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public AlphabeticalRangeFilter(Integer length) {
        this.max = null;
        this.length = length;
        this.range = new int[] { '0', '9' };
        this.radix = 10;
    }
}