package Java;

import java.util.regex.Matcher;

import static Java.Unicode.graphemeClusterPattern;


/** @see <a href='https://unicode.org/reports/tr29/#Grapheme_Cluster_Boundaries'>
  *   Grapheme cluster boundaries in Unicode text segmentation</a>
  */
public final class GraphemeClusterCounter {


    /** Makes a cluster counter with an empty string as the initial input sequence.
      */
    public GraphemeClusterCounter() { this( "" ); }



    /** @param text The input sequence, which is the text wherein to count.
      */
    public GraphemeClusterCounter( final CharSequence text ) {
      this( text, graphemeClusterPattern.matcher( text )); }



    /** @param text The input sequence, which is the text wherein to count.
      * @param matcher A matcher whose `find` method iterates over grapheme clusters, one cluster per
      *   call, so counting them.  Ensure its input sequence has been initialized to the given `text`.
      */
    public GraphemeClusterCounter( CharSequence text, Matcher matcher ) {
        this.text = text;
        this.matcher = matcher; }



    /** Sets the input sequence and returns `clusterCount( start, end )`.
      *
      *     @param text The input sequence, which is the text wherein to count.
      *     @see #clusterCount(int,int)
      */
    public int clusterCount( final CharSequence text, final int start, final int end ) {
        matcher.reset( this.text = text );
        return clusterCount( start, end ); }



    /** Returns the number of grapheme clusters within the given span of the input sequence.
      * Omits any partial cluster at the end of the span.
      *
      *     @param start The start index of the span (inclusive) wherein to count.
      *     @param end The end index of the span (exclusive) wherein to count.
      */
    public int clusterCount( int start, int end ) { return clusterCount( start, end, true ); }



////  P r i v a t e  ////////////////////////////////////////////////////////////////////////////////////


    /** @param wholeOnly Whether to omit any partial cluster at the end of the span.  If `true` and `end`
      *   bisects the final cluster — the character at position `end` being part of the same extended
      *   cluster as the preceding character — then the final cluster is omitted from the count.
      *   Otherwise the final cluster is included.
      *       <p>Omitting partial clusters at the end is generally the behaviour you want in order
      *   to print a line of text with a character pointer positioned beneath it (e.g. ‘^’)
      *   pointing *into* the cluster of the character at the given index, as opposed to after.</p>
      */
    private int clusterCount( final int start, final int end, final boolean wholeOnly ) {
        matcher.region( start, end );
        int count = 0;
        while( matcher.find() ) ++count;
        if( wholeOnly  &&  count > 0  &&  end < text.length() ) {
            final int countNext = clusterCount( start, end + 1, false );
            if( countNext == count ) --count; } /* The character at `end` bisects the final cluster,
              which therefore lies partly outside the span.  Therefore exclude it from the count.
                  The would-be alternative of `\X\b{g}` instead of  `\X` in `graphemeClusterPattern`
              fails to work, the addition of `\b{g}` having no apparent effect. */
        return count; }



    private final Matcher matcher;



    /** The input sequence of `matcher`, which is the text wherein to count.
      */
    private CharSequence text; }



                                                   // Copyright © 2021-2022  Michael Allan.  Licence MIT.
