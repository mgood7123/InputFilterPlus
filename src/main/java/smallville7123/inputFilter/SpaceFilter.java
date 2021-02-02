package smallville7123.inputFilter;

/**
 * a version of {@link InputFilterPlus} that can disallow spaces
 * <br>
 * <br>
 * this is actually just a wrapper around {@link ReplacementFilter}:
 * <br>
 * <pre>{@code
 * public class SpaceFilter extends ReplacementFilter {
 *     public SpaceFilter(boolean allowSpaces) {
 *         super(allowSpaces, " ", "");
 *     }
 * }
 * }</pre>
 *
 * @see HexRangeFilter
 * @see NumberRangeFilter
 * @see AlphaNumericalRangeFilter
 * @see ReplacementFilter
 * @see BackSpaceFilter
 * @see InputFilterPlus
 */
public class SpaceFilter extends ReplacementFilter {

    SpaceFilter() {
        throw new RuntimeException("A constructor that accepts arguments must be called instead");
    }

    /**
     * a version of {@link InputFilterPlus} that can disallow spaces
     * <br>
     * <br>
     * this is actually just a wrapper around {@link ReplacementFilter}:
     * <br>
     * <pre>{@code
     * public class SpaceFilter extends ReplacementFilter {
     *     public SpaceFilter(boolean allowSpaces) {
     *         super(allowSpaces, " ", "");
     *     }
     * }
     * }</pre>
     *
     * @param allowSpaces true if spaces are allowed, otherwise false
     *
     * @see HexRangeFilter
     * @see NumberRangeFilter
     * @see AlphaNumericalRangeFilter
     * @see ReplacementFilter
     * @see BackSpaceFilter
     * @see InputFilterPlus
     */
    public SpaceFilter(boolean allowSpaces) {
        super(allowSpaces, " ", "");
    }
}
