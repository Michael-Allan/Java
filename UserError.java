package Java;


/** Thrown on encountering an anomaly the user is likely in a position to correct.
  */
public class UserError extends Exception {


    public UserError() {}



    /** @see #getMessage()
      */
    public UserError( String message ) { super( message ); }}



                                                   // Copyright © 2020-2021  Michael Allan.  Licence MIT.
