package Java;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.COMMENTS;
import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.UNICODE_CASE;
import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;
import static java.util.regex.Pattern.UNIX_LINES;


/** @see java.util.regex.Pattern
  */
public final class Patterns {


    private Patterns() {}



    /** Appends to `b` the character representations of all `p.flags` that have one.
      */
    public static void appendFlags( final Pattern p, final StringBuilder b ) {
        final int f = p.flags();
        if( (f & UNIX_LINES              ) != 0 ) b.append( 'd' );
        if( (f & CASE_INSENSITIVE        ) != 0 ) b.append( 'i' );
        if( (f & MULTILINE               ) != 0 ) b.append( 'm' );
        if( (f & DOTALL                  ) != 0 ) b.append( 's' );
        if( (f & UNICODE_CHARACTER_CLASS ) != 0 ) b.append( 'U' );
        if( (f & UNICODE_CASE            ) != 0 ) b.append( 'u' );
        if( (f & COMMENTS                ) != 0 ) b.append( 'x' ); }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
