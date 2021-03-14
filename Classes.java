package Java;


/** @see java.lang.Class
  */
public final class Classes {


    private Classes() {}



    /** Returns `cl.getName` stripped of any leading package qualifier.
      */
    public static String nameWithoutPackageLeader( final Class cl ) {
        String name = cl.getName();
        if( !cl.isArray() ) { // Then a package qualifier might lead the name.
            final int strip = cl.getPackageName().length();
            if( strip > 0 ) name = name.substring( strip + /*dot separator*/1 ); }
        return name; }}



                                                        // Copyright © 2021  Michael Allan.  Licence MIT.
