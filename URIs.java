package Java;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;


/** @see URI
  */
public final class URIs {


    private URIs() {}



    /** Whether the given scheme is `http` or `https`.
      */
    public static final boolean isHTTP( final String scheme ) {
        if( scheme.startsWith( "http" )) {
            final int sN = scheme.length();
            if( sN == 4  ) return true;
            if( sN == 5 && scheme.endsWith("s") ) return true; }
        return false; }



    /** A `lookingAt` pattern for detecting a URI by the presence of its leading scheme component
      * and ‘:’ separator.
      *
      *     @see java.util.regex.Matcher#lookingAt()
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3'>URI</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.1'>Scheme</a>
      */
    public static final Pattern schemedPattern = Pattern.compile( "[a-z0-9A-Z][a-z0-9A-Z+.-]*:" );



    /** Removes any fragment of the given URI and returns the result.  If the URI had no fragment
      * to begin with, then this method simply returns the given URI.
      */
    public static URI unfragmented( URI u ) {
        if( u.getFragment() != null ) {
            try { u = new URI( u.getScheme(), u.getSchemeSpecificPart(), /*fragment*/null ); } /*
              With decoding (as opposed to raw) getters, as stipulated in (and above) § Identities:
              `https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/URI.html` */
            catch( URISyntaxException x ) { throw new Unhandled( x ); }}
              // Unexpected with a reconstruction of this sort.
        return u; }}



                                                   // Copyright © 2021-2022  Michael Allan.  Licence MIT.
