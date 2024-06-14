package Java;

import java.util.regex.Pattern;


public final class Unicode {


    private Unicode() {}



    /** The pattern of a grapheme cluster.
      *
      *     @see <a href='http://unicode.org/reports/tr29/#Grapheme_Cluster_Boundaries'>
      *       Grapheme cluster boundaries in Unicode text segmentation</a>
      */
    public static final Pattern graphemeClusterPattern = Pattern.compile( "\\X" ); /*
      An alternative means of cluster discovery is `java.txt.BreakIterator`.
      Long outdated in this regard,  [https://bugs.openjdk.org/browse/JDK-8174266]
      it was updated for JDK 20.  [https://bugs.openjdk.org/browse/JDK-8291660,
      https://stackoverflow.com/a/76109241/2402790] */



    /** The general category value for codepoint `ch`.
      *
      *     @see <a href='https://www.unicode.org/reports/tr44/tr44-26.html#General_Category_Values'>
      *       General category values</a>
      */
    public static String generalCategory( final int ch ) {
        return switch( Character.getType( ch )) {
            case Character.UPPERCASE_LETTER -> "Lu";
            case Character.LOWERCASE_LETTER -> "Ll";
            case Character.TITLECASE_LETTER -> "Lt";
            case Character.MODIFIER_LETTER  -> "Lm";
            case Character.OTHER_LETTER     -> "Lo";
            case Character.NON_SPACING_MARK       -> "Mn";
            case Character.COMBINING_SPACING_MARK -> "Mc";
            case Character.ENCLOSING_MARK         -> "Me";
            case Character.DECIMAL_DIGIT_NUMBER -> "Nd";
            case Character.LETTER_NUMBER        -> "Nl";
            case Character.OTHER_NUMBER         -> "No";
            case Character.CONNECTOR_PUNCTUATION     -> "Pc";
            case Character.DASH_PUNCTUATION          -> "Pd";
            case Character.START_PUNCTUATION         -> "Ps";
            case Character.END_PUNCTUATION           -> "Pe";
            case Character.INITIAL_QUOTE_PUNCTUATION -> "Pi";
            case Character.FINAL_QUOTE_PUNCTUATION   -> "Pf";
            case Character.OTHER_PUNCTUATION         -> "Po";
            case Character.MATH_SYMBOL     -> "Sm";
            case Character.CURRENCY_SYMBOL -> "Sc";
            case Character.MODIFIER_SYMBOL -> "Sk";
            case Character.OTHER_SYMBOL    -> "So";
            case Character.SPACE_SEPARATOR     -> "Zs";
            case Character.LINE_SEPARATOR      -> "Zl";
            case Character.PARAGRAPH_SEPARATOR -> "Zp";
            case Character.CONTROL     -> "Cc";
            case Character.FORMAT      -> "Cf";
            case Character.SURROGATE   -> "Cs";
            case Character.PRIVATE_USE -> "Co";
            case Character.UNASSIGNED  -> "Cn";
            default -> throw new IllegalStateException(); }; }}



                                                   // Copyright © 2021-2024  Michael Allan.  Licence MIT.
