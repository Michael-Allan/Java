package Java;


/** @see java.nio.file.Path
  */
public final class Path {


    private Path() {}



    /** Returns the same `directoryName` if already it ends with a slash `/`, or is empty;
      * otherwise returns `directoryName` with a `/` character appended.
      */
    public static String enslash( String directoryName ) {
        if( !( directoryName.isEmpty() || directoryName.endsWith("/") )) directoryName += "/";
        return directoryName; }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
