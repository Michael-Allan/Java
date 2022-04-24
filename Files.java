package Java;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Files.*;
import static java.nio.file.FileVisitResult.CONTINUE;


/** @see java.nio.file.Files
  */
public final class Files {


    private Files() {}



    /** Deletes the content of directory `d`.  The result is indeterminate if `d` is not a directory.
      *
      *     @return The same directory `d`.
      */
    public static Path emptyDirectory​( final Path d ) throws IOException {
        return walkFileTree( d, new SimpleFileVisitor<Path>() {
            public @Override FileVisitResult visitFile( final Path p, BasicFileAttributes _a )
                  throws IOException {
                delete( p );
                return CONTINUE; }
            public @Override FileVisitResult postVisitDirectory( final Path p, final IOException x )
                  throws IOException {
                if( x != null ) throw x;
                if( !p.equals( d )) delete( p );
                return CONTINUE; }}); }



    /** Whether directory `d` is empty.  The result is indeterminate if `d` is not a directory.
      */
    public static boolean isDirectoryEmpty​( final Path d ) throws IOException {
        try( final DirectoryStream<Path> dS = newDirectoryStream​( d )) {
            return !dS.iterator().hasNext(); }}



    /** @return The same path `p`.
      * @throws IllegalArgumentException Unless `p` is a directory.
      */
    public static Path verifyDirectoryArgument( final Path p ) {
        if( isDirectory( p )) return p;
        throw new IllegalArgumentException( "Not a directory: " + p ); }}



                                                   // Copyright © 2020-2021  Michael Allan.  Licence MIT.
