package Java;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static java.io.File.separatorChar;


/** @see java.nio.file.Path
  */
public final class Paths {


    private Paths() {}



    /** Translates to a `Path` instance the given URI reference.
      *
      * <p>Unlike the method `Path.of`, this method (a) does not require `u` to use
      * a `file` scheme; and therefore (b) can translate relative-path references,
      * which are inexpressible under a `file` scheme.</p>
      *
      *     @see Path#of(URI)
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc8089#section-2'>File-scheme URI syntax</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.2'>
      *       URI generic syntax §4.2, ‘relative-path reference’</a>
      */
    public static Path toPath( URI u ) {
        boolean isRelativePath = false; /* Whether `u` was given as a relative-path reference,
          which `Path.of` would reject, and therefore got converted herein by resolving it
          against the default directory to form an intermediary, absolute-path reference. */
        if( u.getScheme() == null ) {
            if( u.getRawQuery() != null  ||  u.getRawFragment() != null ) {
                throw new IllegalArgumentException(
                  "Query or fragment component on a file-path reference" ); } /* Rather than ‘URI has
                    a query component’ or ‘fragment component’, as `Path.of` below would throw it. */
            String p = u.getPath();
            if( !p.startsWith( "/" )) { // Then `u` was given as a relative-path reference.
                isRelativePath = true;
                assert u.getAuthority() == null;
                u = defaultDirectoryURI.resolve/* to an intermediary, absolute-path reference */( u );
                p = u.getPath(); }
            try { u = new URI( "file", u.getAuthority(), p, /*query*/null, /*fragment*/null ); } /*
              With decoding (as opposed to raw) getters, as stipulated in (and above) § Identities:
              `https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/URI.html` */
            catch( URISyntaxException x ) { throw new Unhandled( x ); }}
              // Unexpected with a reconstruction of this sort.
        Path p = Path.of( u );
        if( isRelativePath )/* then restore it as such */ p = defaultDirectory.relativize( p );
        return p; }



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
        return s; }



////  P r i v a t e  ////////////////////////////////////////////////////////////////////////////////////


    /** The absolute path of the default directory.
      */
    private static final Path defaultDirectory = Path.of("").toAbsolutePath();



    /** The absolute path of the default directory formed as a URI.
      */
    private static final URI defaultDirectoryURI = defaultDirectory.toUri(); }



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
