package Java;

import static Java.Characters.isJavaOrUnicodeWhitespace;
import static java.lang.Character.charCount;


/** Indicant of precisely where in a text a character occurs.
  */
public final class CharacterPointer {


    /** @see #line
      * @see #lineNumber
      * @see #column
      */
    public CharacterPointer( String line, int lineNumber, int column ) {
        this.line = line;
        this.lineNumber = lineNumber;
        this.column = column; }



    /** The columnar offset at which the character occurs.  Columnar offsets are zero based and measured
      * in terms of grapheme clusters, beginning with the first cluster at offset zero in the text line.
      */
    public final int column;



    /** A copy of the text line in which the character occurs, up to and including any terminal newline.
      */
    public final String line;



    /** The ordinal number of the text line in which the character occurs.
      * Lines are numbered beginning at one.
      */
    public final int lineNumber;



    /** Returns an echo of the text line, plus a column mark.
      */
    public String markedLine() {
        final StringBuilder b = new StringBuilder();
        for( int ch, c = 0, cN = line.length(); c < cN; c += charCount(ch) ) { // Sanitize:
            ch = line.codePointAt( c ); // By full code point, not to miss the problematic ones.
            b.appendCodePoint( isJavaOrUnicodeWhitespace(ch)? ' ' : ch ); }
        b.append( '\n' );
        for( int c = column; c > 0; --c ) b.append( ' ' );
        b.append( '^' );
        return b.toString(); }}



                                                   // Copyright © 2021-2022  Michael Allan.  Licence MIT.
