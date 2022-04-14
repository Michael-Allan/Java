package Java;

import static java.lang.Character.isWhitespace;


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
      */
    public static StringBuilder collapseWhitespace( final StringBuilder b ) {
        boolean wasWhitespaceLast = true; // Thus to trim any leading space.
        int r = 0; // Index of next read in `b`.
        int w = 0; // Index of next write in `b`.
        for( final int cN = b.length(); r < cN; ++r ) {
            char ch = b.charAt( r );
            if( !isWhitespace( ch )) { // Then ensure `ch` appears here.
                if( w != r ) b.setCharAt( w, ch ); // Else already `ch` is there.
                ++w;
                wasWhitespaceLast = false; }
            else if( !wasWhitespaceLast ) { // Then ensure a space ' ' appears here.
                if( w != r || ch != ' ' ) b.setCharAt( w, ' ' ); // Else already ' ' is there.
                ++w;
                wasWhitespaceLast = true; } /*
            Else omit this unwanted whitespace character. */ }
        if( w > 0 && wasWhitespaceLast ) --w; // Trim any trailing space, necessarily a single space.
        b.setLength( w );
        return b; }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
