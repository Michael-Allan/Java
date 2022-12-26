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
        if( (f & COMMENTS                ) != 0 ) b.append( 'x' ); }



    /** A string comprising all characters that have special meaning outside of a character class.
      */
    public static final String metacharacters = "\\^.$|()*+?[]{}";



    /** Appends to `b` the characters of `seq`, escaping by backslash `\` any that is a metacharacter.
      * Unlike `Pattern.quote`, which would simply bracket the whole with `\Q` and `\E` (JDK 18),
      * this method escapes only what needs escaping.
      *
      *     @see #metacharacters
      *     @see Pattern#quote(String)
      */
    public static void quote( final CharSequence seq, final StringBuilder b ) {
        final int cN = seq.length();
        for( int c = 0; c < cN; ++c ) {
            final char ch = seq.charAt( c );
            if( !( ch == ' '  ||  '_' <= ch && ch <= 'z'  ||  metacharacters.indexOf(ch) < 0 )) {
                b.append( '\\' ); }
            b.append( ch ); }}}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
