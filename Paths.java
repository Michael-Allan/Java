package Java;

import java.net.URI;
import java.nio.file.Path;

import static java.io.File.separatorChar;
import static Java.URI_References.isRemote;


/** @see java.nio.file.Path
  */
public final class Paths {


    private Paths() {}



    /** Translates to a `Path` instance the given URI path reference.
      *
      * <p>The alternative method `Path.of(URI)` fails for relative-path references.  It requires
      * grafting a scheme onto the reference, and the only relevant scheme it supports is `file`
      * (at least by default), which cannot encode a relative-path reference.</p>
      *
      *     @param ref A path reference, whether an absolute-path reference or a relative-path reference.
      *     @throws IllegalArgumentException If `ref` includes a query component or fragment component,
      *       or is not a path reference.
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.2'>
      *       URI generic syntax §4.2</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc8089#section-2'>File-scheme URI syntax</a>
      *     @see Path#of(URI)
      */
    public static Path toPath( final URI ref ) {
        if( isRemote( ref )) throw new IllegalArgumentException( "Not a path reference" );
        if( ref.getRawQuery() != null  ||  ref.getRawFragment() != null ) {
            throw new IllegalArgumentException( "Query or fragment component on a path reference" ); }
        String s = ref.getPath();
        if( separatorChar != '/' ) s = s.replace( '/', separatorChar );
        return Path.of( s ); }



    /** Translates to a URI relative-path reference the given relative path.
      *
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.2'>
      *       URI generic syntax §4.2</a>
      *     @throws IllegalArgumentException If `path` is absolute.
      *     @see Path#toUri()
      */
    public static String toRelativePathReference( final Path path ) {
        if( path.isAbsolute() ) throw new IllegalArgumentException();
        String s = path.toString();
        if( separatorChar != '/' ) s = s.replace( separatorChar, '/' );
        return s; }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
