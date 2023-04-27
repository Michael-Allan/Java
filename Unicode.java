package Java;

import java.util.regex.Pattern;


public final class Unicode {


    private Unicode() {}



    /** The pattern of a grapheme cluster.
      *
      *     @see <a href='https://unicode.org/reports/tr29/#Grapheme_Cluster_Boundaries'>
      *       Grapheme cluster boundaries in Unicode text segmentation</a>
      */
    public static final Pattern graphemeClusterPattern = Pattern.compile( "\\X" ); } /*
      An alternative means of cluster discovery is `java.txt.BreakIterator`.
      Long outdated in this regard,  [https://bugs.openjdk.org/browse/JDK-8174266]
      it was updated for JDK 20.  [https://bugs.openjdk.org/browse/JDK-8291660,
      https://stackoverflow.com/a/76109241/2402790] */



                                                   // Copyright © 2021-2023  Michael Allan.  Licence MIT.
