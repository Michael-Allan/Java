package Java;

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
            final int arrayStart = buffer.arrayOffset(); /* Non-zero in case e.g. of the buffer
              being a slice of another buffer.  https://stackoverflow.com/a/24601336/2402790 */
            if( arrayStart == 0 ) s = new ArrayCS( buffer, array );
            else s = new OffsetArrayCS( buffer, array, arrayStart ); }
        else s = new BufferCharSequence( buffer );
        return s; }



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



                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
