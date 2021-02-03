package Java;


public final class Hashing {


    private Hashing() {}



    /** Calculates for a hash table with a load factor of 0.75 an initial capacity that suffices to avoid
      * rehashing under typical loads.  For use e.g. with `new HashMap( initialCapacity, 0.75f )`.
      *
      *     @see java.util.HashMap
      *     @param maximumTypicalLoad An estimate of the maximum number of mappings to be contained
      *       in the map during typical use.
      */
    public static int initialCapacity( final int maximumTypicalLoad ) {
        return initialCapacity( maximumTypicalLoad, 0.75f ); }



    /** Calculates for a hash table with the given load factor an initial capacity that suffices to avoid
      * rehashing under typical loads.  For use e.g. with `new HashMap( initialCapacity, loadFactor )`.
      *
      *     @see java.util.HashMap
      *     @param maximumTypicalLoad An estimate of the maximum number of mappings to be contained
      *       in the map during typical use.
      */
    public static int initialCapacity( final int maximumTypicalLoad, final float loadFactor ) {
        int c = (int)Math.ceil( /*rehash threshold*/(maximumTypicalLoad+1) / loadFactor );
        ++c; // Allowing for a margin of error.
        return c; }}


                                                   // Copyright © 2020-2021  Michael Allan.  Licence MIT.
