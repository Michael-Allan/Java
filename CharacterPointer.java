package Java;


/** Indicant of precisely where in a text a particular character occurs.
  */
public final class CharacterPointer extends IntralineCharacterPointer {


    /** @see #line
      * @see #lineNumber
      * @see #column
      */
    public CharacterPointer( String line, int column, int lineNumber ) {
        super( line, column );
        this.lineNumber = lineNumber; }



    /** The ordinal number of the text line in which the character occurs.
      * Lines are numbered beginning at one.
      */
    public final int lineNumber; }



                                                   // Copyright © 2021-2022  Michael Allan.  Licence MIT.
