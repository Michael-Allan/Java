package Java;


/** @see StringBuilder
  */
public final class StringBuilding {


    private StringBuilding() {}



    /** Sets the length of `b` to zero.
      *
      *     @return The same string builder `b`.
      */
    public static StringBuilder clear( final StringBuilder b ) {
        b.setLength( 0 );
        return b; }



    /** Removes any leading or trailing whitepace from `b` and replaces all other whitespace sequences
      * with a single space each.
      *
      *     @return The same string builder `b`.
      *     @see WhitespaceCollapser#collapseWhitespace(StringBuilder)
      */
    public static StringBuilder collapseWhitespace( StringBuilder b ) {
        return collapser.collapseWhitespace( b ); }



////  P r i v a t e  ////////////////////////////////////////////////////////////////////////////////////


    private static final WhitespaceCollapser collapser = new WhitespaceCollapser(); }



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
