package Java;


/** A delimitable character sequence backed by an array.
  */
public class ArrayCharSequence implements DelimitableCharSequence {


    /** @see #array
      */
    public ArrayCharSequence( final char[] array ) { this.array = array; }



    /** The backing array, a delimitable section of which defines this sequence.
      */
    public final char[] array;



    /** Sets the end boundary.
      */
    protected void end( int e ) { end = e; }



    /** Sets the boundaries by calling `{@linkplain #start(int) start}` and `{@linkplain #end(int) end}`.
      *
      *     @throws IndexOutOfBoundsException If `start` is negative, or `end` exceeds the length
      *       of the backing array.
      */
    protected void set( final int start, final int end ) {
        if( start < 0 || end > array.length ) throw new IndexOutOfBoundsException();
        start( start );
        end( end ); }



    /** Sets the start boundary.
      */
    protected void start( int s ) { start = s; }



   // ━━━  C h a r   S e q u e n c e  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


    public final @Override char charAt( final int c ) { return array[start + c]; }



    public final @Override int length() { return length; }



    public @Override CharSequence subSequence( final int start, final int end ) {
        if( start < 0 || end > length || end < start ) throw new IndexOutOfBoundsException(); // Per API.
        return new String( array, this.start + start, end - start ); } /* Pending a use case for
          returning (and documenting) an `ArrayCharSequence`.  Cf. `CharBuffers.OffsetArrayCS`. */



    public final @Override String toString() { return new String( array, start, length ); }



   // ━━━  D e l i m i t a b l e   C h a r   S e q u e n c e  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


    public @Override int end() { return end; }



    /** {@inheritDoc} Delegates to `{@linkplain #set(int,int) set}`.
      */
    public final @Override void delimit( final int start, final int end ) {
        if( end < start ) throw new IllegalArgumentException();
        length = end - start;
        set( start, end ); }



    /** {@inheritDoc} Delegates to `{@linkplain #set(int,int) set}`.
      */
    public final @Override void project( final int start, final int length ) {
        if( length < 0 ) throw new IllegalArgumentException();
        this.length = length;
        set( start, start + length ); }



    public @Override int start() { return start; }



////  P r i v a t e  ////////////////////////////////////////////////////////////////////////////////////


    private int end;



    private int length;



    private int start; }


                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
