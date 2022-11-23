package Java;

import java.net.URI;

import static Java.URIs.schemedPattern;


/** @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
  *   URI generic syntax §4.1, URI reference</a>
  */
public final class URI_References {


    private URI_References() {}



    /** Returns the given directory if it ends with a slash character ‘/’ or is empty,
      * otherwise returns a copy of the directory with a slash character appended.
      *
      *     @param directory A URI reference to a directory.
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
      *       URI generic syntax §4.1, URI reference</a>
      */
    public static String enslash( String directory ) {
        if( !( directory.isEmpty() || directory.endsWith("/") )) directory += "/";
        return directory; }



    /** Returns true if URI reference `ref` begins with either a scheme or authority, false otherwise.
      *
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
      *       URI generic syntax §4.1, URI reference</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.1'>
      *       URI generic syntax §3.1, Scheme</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.2'>
      *       URI generic syntax §3.2, Authority</a>
      */
    public static boolean isRemote( final String ref ) {
        return ref.startsWith("//") || schemedPattern.matcher(ref).lookingAt(); }



    /** Returns true if URI reference `ref` begins with either a scheme or authority, false otherwise.
      *
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-4.1'>
      *       URI generic syntax §4.1, URI reference</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.1'>
      *       URI generic syntax §3.1, Scheme</a>
      *     @see <a href='https://www.rfc-editor.org/rfc/rfc3986#section-3.2'>
      *       URI generic syntax §3.2, Authority</a>
      */
    public static boolean isRemote( final URI ref ) {
      return ref.getScheme() != null  ||  ref.getRawAuthority() != null; }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
