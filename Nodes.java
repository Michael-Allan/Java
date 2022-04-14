package Java;

import org.w3c.dom.Node;


/** @see org.w3c.dom.Node
  */
public final class Nodes {


    private Nodes() {}



    /** Returns the successor of `node` in document order, including any first child,
      * or null if `node` has no successor.
      *
      *     @see <a href='https://www.w3.org/TR/DOM-Level-3-Core/glossary.html#dt-document-order'>
      *       Definition of ‘document order’</a>
      */
    public static Node successor( Node node ) {
        Node s = node.getFirstChild();
        if( s == null ) s = successorAfter( node );
        return s; }



    /** Returns the exclusive successor of `node` in document order, or null if there is none.
      *
      *     @see <a href='https://www.w3.org/TR/DOM-Level-3-Core/glossary.html#dt-document-order'>
      *       Definition of ‘document order’</a>
      *     @return The first successor of `node` outside of its descendants, or null if none exists.
      */
    public static Node successorAfter( final Node node ) {
        Node s = node.getNextSibling();
        if( s == null ) {
            final Node p = node.getParentNode();
            if( p != null ) s = successorAfter( p ); }
        return s; }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
