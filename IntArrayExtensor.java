package Java;

import static java.util.Arrays.copyOf;


/** An augmentor that effects dynamic resizing of a primitive `int` array.
  */
public class IntArrayExtensor {


    /** Makes an extensor that initially has a {@linkplain #capacity() capacity} of 16,
      * and an effective length of zero.
      */
    public IntArrayExtensor() { array = new int[16]; }



    /** Makes an extensor that initially has an array instance of `array`, a capacity of `array.length`,
      * and an effective length of zero.
      */
    public IntArrayExtensor( int[] array ) { this.array = array; }



    /** Makes an extensor that initially has an array instance of `array`, a capacity of `array.length`,
      * and an effective length of 'length`.
      *
      *     @throws IllegalArgumentException If the given `length` is greater
      *       than the {@linkplain #capacity() capacity} of the array.
      */
    public IntArrayExtensor( final int[] array, final int length ) {
        this.array = array;
        this.length = length;
        if( length > capacity() ) throw new IllegalArgumentException(); }



    /** Increments the effective length of the array and assigns `i` as its final element.
      *
      * <p>Warning: this method is liable to overwrite the {@linkplain #array array} instance.</p>
      */
    public void add( int i ) {
        if( length >= capacity() ) {
            assert length == capacity(); // Never should it be greater.
            array = copyOf( array, length * 2 ); }
        array[length++] = i; }



    /** The present instance of the array.  Avoid using the enhanced `for` statement on it;
      * its inbuilt and {@linkplain #length effective} lengths may differ.
      *
      * <p>Warning: this instance is liable to be overwritten by anything able to extend
      * its effective length.</p>
      *
      *     @see <a href='https://docs.oracle.com/javase/specs/jls/se15/html/jls-14.html#jls-14.14.2'>
      *       The enhanced `for` statement</a>
      */
    public int[] array;



    /** The maximum {@linkplain #length effective length} of the present instance of the array,
      * which is simply `{@linkplain #array array}.length`.
      */
    public final int capacity() { return array.length; }



    /** Sets the {@linkplain #length effective length} to zero.
      */
    public void clear() { length = 0; }



    /** Whether the {@linkplain #length effective length} is zero.
      */
    public final boolean isEmpty() { return length == 0; }



    /** The effective length of the array.  Use this field instead of the inbuilt `array.length`,
      * wherefore avoid using the enhanced `for` statement on the array.
      *
      *     @see #capacity()
      *     @see <a href='https://docs.oracle.com/javase/specs/jls/se15/html/jls-14.html#jls-14.14.2'>
      *       The enhanced `for` statement</a>
      */
    public int length = 0; }


                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
