package Java;


public @AsyncSafe class WhitespaceCollapser {


    /** Removes any leading or trailing whitepace from `b` and replaces all other whitespace sequences
      * with a single space each.
      *
      *     @return The same string builder `b`.
      *     @see StringBuilding#collapseWhitespace(StringBuilder)
      */
    public final StringBuilder collapseWhitespace( final StringBuilder b ) {
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
        return b; }



    /** Whether character `ch` is considered to be a whitespace for purposes of whitespace collapsing.
      */
    public boolean isWhitespace( char ch ) { return Character.isWhitespace( ch ); }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
