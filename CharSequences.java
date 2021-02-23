package Java;


/** @see java.lang.CharSequence
  */
public final class CharSequences {


    private CharSequences() {}



    /** Returns true iff `s` and `t` contain identical characters in the same order, or are both empty.
      *
      *     @see #compare(CharSequence,CharSequence)
      *     @see String#contentEquals(CharSequence)
      */
    public static boolean equalInContent( final CharSequence s, final CharSequence t ) {
        int c = s.length();
        if( c != t.length() ) return false;
        while( c > 0 ) if( s.charAt(--c) != t.charAt(c) ) return false;
        return true; }}



                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
