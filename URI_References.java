package Java;

import static Java.URIs.schemedPattern;


/** @see <a href='https://tools.ietf.org/html/rfc3986#section-4.1'>URI reference</a>
  */
public final class URI_References {


    private URI_References() {}



    /** Returns true if `ref` begins either with a scheme, or the ‘//’ of a network-path reference;
      * false otherwise.
      *
      * <p>This method may fail for a Windows file path.  Linux is assumed.</p>
      *
      *     @see <a href='https://tools.ietf.org/html/rfc3986#section-3.1'>Scheme</a>
      *     @see <a href='https://tools.ietf.org/html/rfc3986#section-4.2'>Network-path reference</a>
      *     @see <a href='http://reluk.ca/project/editorial_guidelines.brec.xht'>
      *       Operating system, assumption of Linux</a> *//*
      *
      * Re Windows, see `https://stackoverflow.com/q/11687916/2402790`.
      */
    public static boolean isRemote( String ref ) {
        return ref.startsWith("//") || schemedPattern.matcher(ref).lookingAt(); }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
