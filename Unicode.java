package Java;

import java.util.regex.Pattern;


public final class Unicode {


    private Unicode() {}



    /** The pattern of a grapheme cluster.
      *
      *     @see <a href='https://unicode.org/reports/tr29/#Grapheme_Cluster_Boundaries'>
      *       Grapheme cluster boundaries in Unicode text segmentation</a>
      */
    public static final Pattern graphemePattern = Pattern.compile( "\\X" ); } /*
      The alternative for cluster discovery (within the JDK) is `java.txt.BreakIterator`, but
      apparently it is outdated in this regard, whereas `java.util.regex` was updated for JDK 15.
      https://bugs.openjdk.java.net/browse/JDK-8174266
      https://bugs.openjdk.java.net/browse/JDK-8243579 */



                                                   // Copyright © 2021-2022  Michael Allan.  Licence MIT.
