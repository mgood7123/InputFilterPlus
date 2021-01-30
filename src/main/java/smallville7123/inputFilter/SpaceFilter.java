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
 * @see smallville7123.inputFilter.ReplacementFilter
 * @see smallville7123.inputFilter.SpaceFilter
 * @see smallville7123.inputFilter.BackSpaceFilter
 * @see smallville7123.inputFilter.InputFilterPlus
 */
public class SpaceFilter extends ReplacementFilter {
    public SpaceFilter(boolean allowSpaces) {
        super(allowSpaces, " ", "");
    }
}
