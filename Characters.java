package Java;


/** @see java.lang.Character
  */
public final class Characters {


    private Characters() {}



    /** Whether character `ch` is a whitespace character according either to Java or to Unicode.
      * This method behaves the same as `Character.{@linkplain #isWhitespace(char) isWhitespace}`,
      * which excludes no-break spaces, except it includes those.
      */
    public static boolean isJavaOrUnicodeWhitespace( final char ch ) {
        return isJavaOrUnicodeWhitespace( (int)ch ); }


    /** Whether character `ch` is a whitespace character according either to Java or to Unicode.
      * This method behaves the same as `Character.{@linkplain #isWhitespace(int)  isWhitespace}`,
      * which excludes no-break spaces, except it includes those.
      */
    public static boolean isJavaOrUnicodeWhitespace( final  int ch ) {
        return Character.isWhitespace/*[TL]*/( ch ) // Which excludes several no-break spaces,
          || ch == '\u00A0' || ch == '\u2007' || ch == '\u202F'; }} // wherefore include them.



// NOTE
// ────
//   TL · Apparently implemented as a tabular lookup, at least in the JDK.  Very fast.



                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
