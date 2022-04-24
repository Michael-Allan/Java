package Java;

import java.util.function.BiConsumer;
import java.util.Iterator;
import java.util.Map;


/** @see java.util.Collections
  */
public final class Collections {


    private Collections() {}



    /** Performs the given action for each remaining key-value pair of the iterator until all pairs have
      * been processed or the action throws an exception.  Use this method for iterations that require
      * access to the iterator; otherwise use `Map.{@linkplain Map#forEach(BiConsumer) forEach}`.
      *
      *     @param <K> The type of map key.
      *     @param <V> The type of mapped value.
      *     @see Iterator#forEachRemaining(java.util.function.Consumer)
      */
    public static <K,V> void forEachRemaining​( final Iterator<Map.Entry<K,V>> iterator,
          final  BiConsumer<? super K, ? super V> action ) {
        iterator.forEachRemaining( entry -> action.accept( entry.getKey(), entry.getValue() )); }}



                                                   // Copyright © 2020-2021  Michael Allan.  Licence MIT.
