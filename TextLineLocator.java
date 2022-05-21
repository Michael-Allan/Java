package Java;


/** Means to locate for a given offset in a text the line in which it falls.  Each call to `locateLine`
  * is restricted to a particular region of the text, which the caller may change from call to call.
  * Regardless offsets are always measured from the start of the whole text in fixed-width units,
  * typically UTF-16 code units.
  */
public class TextLineLocator {


    /** @see #endsRegional
      */
    public TextLineLocator( IntArrayExtensor endsRegional ) { this.endsRegional = endsRegional; }



    /** The end boundaries of the lines of the present region of the text.  Each end boundary is an
      * offset in the text, which is the offset either of the first character of the succeeding line,
      * or of the end boundary of the text.
      *
      * <p>Each end boundary is preceded by a newline except that of the final line of the text,
      * which may or may not be.  It will be so preceded if the text ends with a newline.
      * In that case, its final line will be empty and therefore share the end boundary
      * of its predecessor.  If the present region covers such an empty terminal line, then
      * the array of `endsRegional` will record two equal offsets in its final two components.</p>
      */
    public final IntArrayExtensor endsRegional;



    /** The index in `{@linkplain #endsRegional endsRegional}` of the line last located.
      */
    public final int index() { return index; }



    /** Locates the line in which the given offset falls.
      *
      *     @param offset An offset in the text.  Normally it lies in the present region.  If rather
      *       it lies before `offsetRegional`, then instead this method uses `offsetRegional`;
      *       or if it lies after the region, then this method yields the last line of the region.
      *     @param offsetRegional The offset in the text of the present region.
      *     @param numberRegional The ordinal number in the text of the first line of the present region.
      */
    public void locateLine( final int offset, final int offsetRegional, final int numberRegional ) {
        final int[] endsArray = endsRegional.array;
        int e = 0, n = numberRegional, s = offsetRegional;
        for( int end, eN = endsRegional.length;      // For each line, if its end boundary
          e < eN && (end = endsArray[e]) <= offset; // lies at or before the given offset,
          ++e, ++n, s = end );                     // then advance to the next line.
        index = e;
        number = n;
        start = s; } // The end boundary of its predecessor, if any, else `offsetRegional`.



    /** The ordinal number in the text of the line last located.  Lines are numbered beginning at one.
      */
    public final int number() { return number; }



    /** The offset in the text of the line last located.
      * This is the offset at which it starts, its end boundary meanwhile being given by
      * `{@linkplain #endsRegional endsRegional}[{@linkplain #index() index}]`. *//*
      *
      * Not to say here ‘the offset of the first character’ of the line, for if it were the final line
      * of a text that ends with a newline, then it would be empty of characters [see `endsRegional`].
      */
    public final int start() { return start; }



////  P r i v a t e  ////////////////////////////////////////////////////////////////////////////////////


    private int index;



    private int number;



    private int start; }



                                                   // Copyright © 2020-2022  Michael Allan.  Licence MIT.
