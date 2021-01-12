package Java;


/** Thrown for an event that might better be handled, given a reason to do so.
  */
public class Unhandled extends RuntimeException {


    /** @see #getCause()
      */
    public Unhandled( Throwable cause ) { super( cause ); }}


                                                   // Copyright © 2020-2021  Michael Allan.  Licence MIT.
