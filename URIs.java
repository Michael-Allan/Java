package Java;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;


/** @see URI
  */
public final class URIs {


    private URIs() {}



    /** A pattern for `lookingAt` a schemed URI reference (a proper URI, that is) through its
      * leading scheme component and ‘:’ separator.
      *
      *     @see java.util.regex.Matcher#lookingAt()
      *     @see <a href='https://tools.ietf.org/html/rfc3986#section-4.1'>URI reference</a>
      *     @see <a href='https://tools.ietf.org/html/rfc3986#section-3'>URI</a>
      *     @see <a href='https://tools.ietf.org/html/rfc3986#section-3.1'>Scheme</a>
      */
    public static final Pattern schemedPattern = Pattern.compile( "[a-z0-9A-Z][a-z0-9A-Z+.-]*:" );
      // See also `https://tools.ietf.org/html/rfc2234#section-6`.



    /** Removes any fragment of the given URI and returns the result.  If the URI had no fragment
      * to begin with, then this method simply returns the given URI.
      */
    public static URI unfragmented( URI u ) {
        if( u.getFragment() != null ) {
            try { u = new URI( u.getScheme(), u.getSchemeSpecificPart(), /*fragment*/null ); } /* Using
              the decoded getters, not the raw ones.  For authority on this usage, see API § Identity.
              https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/net/URI.html */
            catch( URISyntaxException x ) { throw new Unhandled( x ); }}
              // Unexpected for a mere reconstruction.
        return u; }}



                                                       // Copyright © 2021  Michael Allan.  Licence MIT.
