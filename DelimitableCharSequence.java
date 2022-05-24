package Java;


/** A character sequence of redefinable boundaries based on a backing sequence.
  */
public interface DelimitableCharSequence extends CharSequence {


    /** The exclusive end boundary of this sequence.
      */
    public int end();



    /** Sets the boundaries of this sequence, yielding a length of `end - start`.
      *
      *     @throws IllegalArgumentException If `end` is less than `start`.
      *     @throws IndexOutOfBoundsException If `start` is negative, or `end` exceeds the length
      *       of the backing sequence.
      *     @see project(int,int)
      */
    public void delimit( int start, int end );



    /** Delimits this sequence to `other.start` and `other.end`.
      *
      *     @see delimit(int,int)
      */
    public default void delimitAs( final DelimitableCharSequence other ) {
        delimit( other.start(), other.end() ); }



    /** Extends this sequence across `length` characters from `start`,
      * yielding an end boundary of `start + length`.
      *
      *     @throws IllegalArgumentException If `length` is negative.
      *     @throws IndexOutOfBoundsException If `start` is negative, or `start + length` exceeds
      *       the length of the backing sequence.
      *     @see delimit(int,int)
      */
    public void project( int start, int length );



    /** The inclusive start boundary of this sequence.
      */
    public int start(); }


                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
