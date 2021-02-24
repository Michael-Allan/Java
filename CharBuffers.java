package Java;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;


/** @see java.nio.CharBuffer
  */
public final class CharBuffers {


    private CharBuffers() {}



    /** Makes a `DelimitableCharSequence` backed by the given buffer.  Note that calls
      * to `buffer.{@linkplain CharBuffer#compact() compact}` will not automatically
      * adjust the sequence’s boundaries.
      */
    public static DelimitableCharSequence newDelimitableCharSequence( final CharBuffer buffer ) {
        final DelimitableCharSequence s;
        if( buffer.hasArray() ) {
            final char[] array = buffer.array();
            final int offset = buffer.arrayOffset(); // [BAO]
            if( offset == 0 ) s = new ArrayCS( buffer, array );
            else s = new OffsetArrayCS( buffer, array, offset ); }
        else s = new BufferCharSequence( buffer );
        return s; }



    /** Commands the reader to transfer characters to the buffer by the most direct means,
      * {@linkplain Reader#read(ch[],int,int) straight to the backing array} if the buffer has one.
      * Advances the buffer position by the transfer count.
      *
      * <p>This method works around suboptimal implementations (e.g. in some versions of the JDK)
      * of `reader.{@linkplain Reader#read(CharBuffer) read(buffer)}`.</p>
      *
      *     @return The number of characters transferred to the buffer.
      */
    public static int transferDirectly( final Reader reader, final CharBuffer buffer )
          throws IOException {
        final int n;
        if( buffer.hasArray() ) {
            final int offset = buffer.arrayOffset(); // [BAO]
            final int p = buffer.position();
            n = reader.read( buffer.array(), offset + p, buffer.remaining() ); /* A transfer in one step,
              what the JDK itself ought to be doing.  Instead its reader (of JDK 15, at last inspection)
              transfers 1) from the source to a temporary array, then 2) from the array to the buffer. */
            if( n > 0 ) buffer.position( p + n ); }
        else n = reader.read( buffer );
        return n; }



   // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀


    private static class ArrayCS extends ArrayCharSequence {


        /** @see #buffer
          * @see #array
          */
        private ArrayCS( CharBuffer buffer, char[] array ) {
            super( array );
            this.buffer = buffer; }



        /** The buffer whose array backs this sequence.
          */
        private final CharBuffer buffer;



       // ━━━  A r r a y   C h a r   S e q u e n c e  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


        /** @throws IndexOutOfBoundsException If `start` is negative, or `end` exceeds the buffer limit.
          */
        protected final @Override void set( final int start, final int end ) {
            // Changing what follows?  Sync → `BufferCharSequence`.
            if( start < 0 || end > buffer.limit() ) throw new IndexOutOfBoundsException();
            start( start );
            end( end ); }}



   // ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀


    private static final class OffsetArrayCS extends ArrayCS {


        /** @see #buffer
          * @see #array
          * @see #arrayStart
          */
        private OffsetArrayCS( CharBuffer buffer, char[] array, int arrayStart ) {
            super( buffer, array );
            this.arrayStart = arrayStart; }



        /** The inclusive start boundary of the backing array, which is its first accessible index.
          * The start and end boundaries of the character sequence are set relative to this boundary
          * in a manner transparent to the user.
          */
        private final int arrayStart;



       // ━━━  A r r a y   C h a r   S e q u e n c e  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


        protected @Override void end( int end ) { super.end( end + arrayStart ); }
          // Resolving to an absolute array index.



        protected @Override void start( int start ) { super.start( start + arrayStart ); }
          // Resolving to an absolute array index.



       // ━━━  D e l i m i t a b l e   C h a r   S e q u e n c e  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


        public @Override int end() { return super.end() - arrayStart; } // Relativizing.



        public @Override int start() { return super.start() - arrayStart; }}} // Relativizing.



// NOTE
// ────
//   BAO  Backing-array offset.  This is non-zero in case e.g. of an array-backed buffer formed as
//        a slice of another buffer.  https://stackoverflow.com/a/24601336/2402790



                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
