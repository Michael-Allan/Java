package Java;

import java.nio.CharBuffer;


/** A delimitable character sequence backed by a `char` buffer.  Note that calls
  * to `buffer.{@linkplain CharBuffer#compact() compact}` will not automatically
  * adjust the sequence boundaries.
  */
public final class BufferCharSequence implements DelimitableCharSequence {


    /** @see #buffer
      */
    public BufferCharSequence( CharBuffer buffer ) { this.buffer = buffer; }



    /** The backing buffer, a delimitable section of which defines this sequence.
      */
    public final CharBuffer buffer;



   // ━━━  C h a r   S e q u e n c e  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


    public @Override char charAt( final int c ) {
        if( c < 0 || c >= length ) throw new IndexOutOfBoundsException();
        return buffer.get( start + c ); }



    public @Override int length() { return length; }



    public @Override CharSequence subSequence( final int start, final int end ) {
        if( start < 0 || end > length() || end < start ) throw new IndexOutOfBoundsException/*sic*/();
        return new StringBuilder().append( this, start, end ).toString(); }
          // Pending a use case for returning (and documenting) a `BufferCharSequence`.



    public @Override String toString() { return new StringBuilder(this).toString(); }



   // ━━━  D e l i m i t a b l e   C h a r   S e q u e n c e  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


    public @Override int end() { return end; }



    public @Override void delimit( final int start, final int end ) {
        if( end < start ) throw new IllegalArgumentException();
        length = end - start;
        set( start, end ); }



    public @Override void project( final int start, final int length ) {
        if( length < 0 ) throw new IllegalArgumentException();
        this.length = length;
        set( start, start + length ); }



    public @Override int start() { return start; }



////  P r i v a t e  ////////////////////////////////////////////////////////////////////////////////////


    private int end;



    private int length;



    private void set( final int start, final int end ) {
        // Changing what follows?  Sync → `CharBuffers.ArrayedSequence`.
        if( start < 0 || end > buffer.limit() ) throw new IndexOutOfBoundsException();
        this.start = start;
        this.end = end; }



    private int start; }


                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
