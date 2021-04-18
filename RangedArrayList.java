package Java;

import java.util.ArrayList;


/** An array list with a public `{@linkplain #removeRange(int,int) removeRange}` method.
  */
public class RangedArrayList<E> extends ArrayList<E> {


    /** @see ArrayList#ArrayList()
      */
    public RangedArrayList() { super(); }



    /** @see ArrayList#ArrayList(int)
      */
    public RangedArrayList( int initialCapacity ) { super( initialCapacity ); }



   // ━━━  A b s t r a c t   L i s t  ━━━  A r r a y    L i s t  ━━━━━━━━━━━━━━━━━━━━━━


    public @Override final void removeRange( int fromIndex, int toIndex ) {
        super.removeRange( fromIndex, toIndex ); }}



                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
