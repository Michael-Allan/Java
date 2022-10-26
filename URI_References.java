package Java;

import java.net.URI;

import static Java.URIs.schemedPattern;


/** @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>URI reference</a>
  */
public final class URI_References {


    private URI_References() {}



    /** Returns the same instance of `directory` if already it ends with a slash character ‘/’,
      * or is empty ‘’; otherwise returns a copy of `directory` with a slash ‘/’ appended.
      *
      *     @param directory A <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
      *       URI reference</a>.
      */
    public static String enslash( String directory ) {
        if( !( directory.isEmpty() || directory.endsWith("/") )) directory += "/";
        return directory; }



    /** Returns true if `ref` begins with either a scheme or authority, false otherwise.
      *
      *     @param ref A <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
      *       URI reference</a>.
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.1'>Scheme</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.2'>Authority</a>
      */
    public static boolean isRemote( final String ref ) {
        return ref.startsWith("//") || schemedPattern.matcher(ref).lookingAt(); }



    /** Returns true if `ref` begins with either a scheme or authority, false otherwise.
      *
      *     @param ref A <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
      *       URI reference</a>.
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.1'>Scheme</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.2'>Authority</a>
      */
    public static boolean isRemote( final URI ref ) {
      return ref.getScheme() != null  ||  ref.getRawAuthority() != null; }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
