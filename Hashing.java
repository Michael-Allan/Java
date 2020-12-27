package Java;


public final class Hashing {


    private Hashing() {}



    /** Calculates for a hash table an initial capacity that suffices to avoid rehashing
      * under typical loads.  For use e.g. with `new HashMap( initialCapacity, loadFactor )`.
      *
      *     @see java.util.HashMap
      */
    public static int initialCapacity( int maximumTypicalLoad, final float loadFactor ) {
        int c = (int)Math.ceil( /*presumed rehash threshold*/maximumTypicalLoad++ / loadFactor );
        ++c; // Allowing for a margin of error.
        return c; }}


                                                        // Copyright © 2020  Michael Allan.  Licence MIT.
