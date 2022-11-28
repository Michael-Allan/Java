package Java;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static java.io.File.separatorChar;


/** @see java.nio.file.Path
  */
public final class Paths {


    private Paths() {}



    /** Whether `p` has a name that ends with the given extension.
      */
    public static boolean hasExtension( final String extension, final Path p ) {
        return p.getNameCount() != 0  &&  p.getFileName().toString().endsWith( extension ); }



    /** Translates to a `Path` instance the given URI reference, which must not be
      * a relative-path reference.  Unlike the method `Path.of`, this method
      * does not require `reference` to use a `file` scheme.
      *
      *     @see Path#of(URI)
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
      *       URI generic syntax §4.1, URI reference</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.2'>
      *       URI generic syntax §4.2, ‘relative-path reference’</a>
      *     @throws IllegalArgumentException If `reference` is a relative-path reference.
      */
    public static Path toPath( final URI reference ) {
        if( reference.getScheme() == null  &&  !reference.getPath().startsWith("/") ) {
            throw new IllegalArgumentException( "Relative-path reference: " + reference ); }
        return toPath( reference, /*referrer*/null ); }



    /** Translates to a `Path` instance the given URI reference.
      *
      * <p>Unlike the method `Path.of`, this method (a) does not require `reference` to use
      * a `file` scheme; and therefore (b) can translate relative-path references,
      * which are inexpressible under a `file` scheme.</p>
      *
      *     @param referrer The referring file, wherein the reference is contained.  This argument
      *       will be used only if `reference` is a relative-path reference; otherwise its value
      *       may be given as null.
      *     @see Path#of(URI)
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc8089#section-2'>File-scheme URI syntax</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
      *       URI generic syntax §4.1, URI reference</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.2'>
      *       URI generic syntax §4.2, ‘relative-path reference’</a>
      */
    public static Path toPath( final URI reference, final Path referrer ) {
        URI u = reference;
        Path backstop = null; /* If a relative-path reference was given (which `Path.of` would reject),
           then `backstop` is set to the absolute directory against which the reference is resolved
           to produce (in `u`) an intermediary, absolute-path reference which `Path.of` will accept. */
        if( u.getScheme() == null ) {
            if( u.getRawQuery() != null  ||  u.getRawFragment() != null ) {
                throw new IllegalArgumentException(
                  "Query or fragment component on a file-path reference: " + reference ); } /*
                    Rather than ‘URI has a query component’ or ‘fragment component’,
                    as `Path.of` below would throw it. */
            String p = u.getPath();
            if( p.startsWith( "/" )) { /* Then `u` was given as a network-path or absolute-path reference
                  and needs only the addition of a `file` scheme to make it acceptable to `Path.of`. */
                try { u = new URI( "file", u.getAuthority(), p, /*query*/null, /*fragment*/null ); } /*
                  With decoding (as opposed to raw) getters, as stipulated in (and above) § Identities:
                  `https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/URI.html` */
                catch( URISyntaxException x ) { throw new Unhandled( x ); }}
                  // Unexpected with a reconstruction of this sort.
            else { // Then `u` was given as a relative-path reference, which `Path.of` would reject.
                assert u.getAuthority() == null; /* As required for a relative-path reference,
                  and also for the `resolve` call below to work. */
                final Path referrerAbsolute = referrer.toAbsolutePath();
                backstop = referrerAbsolute.getParent();
                if( backstop == null ) {
                    throw new IllegalArgumentException( "Not a regular file: " + referrer ); }
                u = backstop.toUri().resolve/* to an intermediary, absolute-path reference */( u ); }} /*
                  Not just any URI with an absolute path will suffice here; it must be deep enough in the
                  hierarchy to resolve any `..` segments.  (Using the default directory as the backstop
                  has resulted in mistranslations.)  Hence the `referrer` argument. */
        Path p = Path.of( u );
        if( backstop != null )/* then restore it to relative form */ p = backstop.relativize( p );
        return p; }



    /** Translates to a URI relative-path reference the given rootless path.
      *
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.2'>
      *       URI generic syntax §4.2, ‘relative-path reference’</a>
      *     @throws IllegalArgumentException If `path` includes a root.
      *     @see Path#toUri()
      */
    public static String to_URI_relativePathReference( final Path path ) {
        if( path.getRoot() != null ) throw new IllegalArgumentException();
        String s = path.toString();
        if( separatorChar != '/' ) s = s.replace( separatorChar, '/' );
        return s; }



    /** Translates to a URI relative reference the given file path.
      *
      * <p>Unlike the method `Path.toUri` which returns a URI with a `file` scheme, this method
      * returns a URI with no scheme at all, namely a relative reference.  Therefore it can
      * translate a rootless path as such, namely to a relative-path reference,
      * which is inexpressible under a `file` scheme.</p>
      *
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.2'>
      *       URI generic syntax §4.2, Relative reference</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc8089#section-2'>File-scheme URI syntax</a>
      *     @see Path#toUri()
      */
    public static String to_URI_relativeReference( final Path path ) {
        if( path.getRoot() == null ) return to_URI_relativePathReference( path );
        URI u = path.toUri();
        try { // With decoding (as opposed to raw) getters:
            u = new URI( /*scheme*/null, u.getAuthority(), u.getPath(), u.getQuery(),
              u.getFragment() ); } /* So as stipulated in (and above) § Identities:
                `https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/URI.html` */
        catch( URISyntaxException x ) { throw new Unhandled( x ); }
          // Unexpected with a reconstruction of this sort.
        return u.toASCIIString(); }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
