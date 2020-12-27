package Java;

import java.util.function.BiConsumer;
import java.util.Iterator;
import java.util.Map;


/** @see java.util.Collections
  */
public final class Collections {


    private Collections() {}



    /** Performs the given action for each remaining key-value pair of the iterator until all pairs
      * have been processed or the action throws an exception.
      *
      *     @param <K> The type of map key.
      *     @param <V> The type of mapped value.
      *     @see java.util.Iterator#forEachRemaining(java.util.function.Consumer)
      */
    public static <K,V> void forEachRemaining​( final Iterator<Map.Entry<K,V>> iterator,
          final  BiConsumer<? super K, ? super V> action) {
        iterator.forEachRemaining( pair -> { action.accept( pair.getKey(), pair.getValue() ); });}}



                                                        // Copyright © 2020  Michael Allan.  Licence MIT.
