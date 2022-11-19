package Java;

import static Java.Characters.isJavaOrUnicodeWhitespace;
import static java.lang.Character.charCount;


/** Indicant of precisely where in a line of text a particular character occurs.
  */
public class IntralineCharacterPointer {


    /** @see #line
      * @see #column
      */
    public IntralineCharacterPointer( String line, int column ) {
        this.line = line;
        this.column = column; }



    /** The columnar offset at which the character occurs.  Columnar offsets are zero based and measured
      * in terms of grapheme clusters, beginning with the first cluster at offset zero in the text line.
      *
      *     @see <a href='https://unicode.org/reports/tr29/#Grapheme_Cluster_Boundaries'>
      *       Grapheme cluster boundaries in Unicode text segmentation</a>
      */
    public final int column;



    /** A copy of the text line in which the character occurs, with or without any terminal newline.
      */
    public final String line;



    /** Returns a multi-line string comprising an echo of the text line together with a column marker.
      */
    public final String markedLine() { return markedLine( line, column ); }



    /** Returns a multi-line string comprising an echo of a text line together with a column marker.
      *
      *     @param line The text line to be marked, with or without any terminal newline.
      *     @param column The columnar offset at which to mark the line.  Columnar offsets are zero based
      *       and measured in terms of grapheme clusters, beginning with the first cluster at offset zero
      *       in the text line.
      *     @see <a href='https://unicode.org/reports/tr29/#Grapheme_Cluster_Boundaries'>
      *       Grapheme cluster boundaries in Unicode text segmentation</a>
      */
    public static String markedLine( final String line, final int column ) {
        final StringBuilder b = new StringBuilder();
        for( int ch, c = 0, cN = line.length(); c < cN; c += charCount(ch) ) { // Sanitize:
            ch = line.codePointAt( c ); // By full code point, not to miss the problematic ones.
            b.appendCodePoint( isJavaOrUnicodeWhitespace(ch)? ' ' : ch ); }
        b.append( '\n' );
        for( int c = column; c > 0; --c ) b.append( ' ' );
        b.append( '^' );
        return b.toString(); }



    /** Returns a multi-line string comprising an echo of a text line together with a column marker.
      *
      *     @param line The text line to be marked, with or without any terminal newline.
      *     @param c The zero-based offset of the character whose column to mark.
      */
    public static String markedLine( final String line, final int c, final GraphemeClusterCounter gcc ) {
        return markedLine( line, /*column*/gcc.clusterCount(line,0,c) ); }



    /** Returns a multi-line string comprising an echo of a text line together with a column marker.
      *
      *     @param head Part one of the text line to be marked.  This might, for example,
      *        be a sequence of space characters that serve to indent the text.
      *     @param body Part two of the text line to be marked, with or without any terminal newline.
      *     @param b The zero-based offset in `body` of the character whose column to mark.
      */
    public static String markedLine( final String head, final String body, final int b,
          final GraphemeClusterCounter gcc ) {
        return markedLine( /*line*/head + body, /*c*/head.length() + b,  gcc ); }}



                                                   // Copyright © 2021-2022  Michael Allan.  Licence MIT.
