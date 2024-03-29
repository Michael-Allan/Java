package Java;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import static org.w3c.dom.Node.ELEMENT_NODE;
import static org.w3c.dom.Node.TEXT_NODE;


/** @see org.w3c.dom.Node
  */
public final class Nodes {


    private Nodes() {}



    /** Whether `name` is the local name of `node`.
      *
      *     @throws NullPointerException If either `name` or `node` is null.
      */
    public static boolean hasName( final String name, final Node node ) {
        return name.equals( node.getLocalName() ); }



    /** Whether `node` is typed an an element.
      *
      *     @see Node#getNodeType()
      *     @throws NullPointerException If `node` is null.  Note the difference from the behaviour
      *       of the `instanceof` operator, which would instead return true.
      */
    public static boolean isElement( final Node node ) { return node.getNodeType() == ELEMENT_NODE; }



    /** Whether `node` is typed as text.
      *
      *     @see Node#getNodeType()
      *     @throws NullPointerException If `node` is null.  Note the difference from the behaviour
      *       of the `instanceof` operator, which would instead return true.
      */
    public static boolean isText( final Node node ) { return node.getNodeType() == TEXT_NODE; }



    /** Returns the nearest of the node’s subsequent siblings of the given local name,
      * or null if there are none.
      */
    public static Node nextSibling( Node node, final String name ) {
        do node = node.getNextSibling(); while( node != null && !hasName( name, node ));
        return node; }



    /** Returns the parent of `node` as an element, or null if `node` has no parent.
      *
      *     @throws ClassCastException If the parent of `node` is neither null nor an element.
      */
    public static Element parentAsElement( final Node node ) { return (Element)node.getParentNode(); }



    /** Returns the parent of `node` as an element; or null if `node` either has no parent,
      * or its parent is not an element.
      */
    public static Element parentElement( Node node ) {
        node = node.getParentNode();
        return node instanceof Element ? (Element)node : null; }



    /** Returns the nearest of the node’s prior siblings of the given local name,
      * or null if there are none.
      */
    public static Node previousSibling( Node node, final String name ) {
        do node = node.getPreviousSibling(); while( node != null && !hasName( name, node ));
        return node; }



    /** Returns the successor of `node` in document order, including any first child,
      * or null if `node` has no successor.
      *
      *     @see <a href='https://www.w3.org/TR/DOM-Level-3-Core/glossary.html#dt-document-order'>
      *       Definition of ‘document order’</a>
      */
    public static Node successor( final Node node ) {
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
        return s; }



    /** Returns the elemental successor of `n` in document order, including any first child,
      * or null if `n` has no elemental successor.
      *
      *     @see <a href='https://www.w3.org/TR/DOM-Level-3-Core/glossary.html#dt-document-order'>
      *       Definition of ‘document order’</a>
      */
    public static Element successorElement( Node n ) {
        do n = successor( n ); while( n != null  &&  !isElement(n) );
        return (Element)n; }



    /** Returns the exclusive elemental successor of `n` in document order, or null if there is none.
      *
      *     @see <a href='https://www.w3.org/TR/DOM-Level-3-Core/glossary.html#dt-document-order'>
      *       Definition of ‘document order’</a>
      *     @return The first elemental successor of `n` outside of its descendants,
      *       or null if none exists.
      */
    public static Element successorElementAfter( Node n ) {
        do n = successorAfter( n ); while( n != null  &&  !isElement(n) );
        return (Element)n; }



    /** Returns the flat text (aka `data`) of the first child of the given node,
      * which child must be a text node.
      */
    public static String textChildFlat( final Node n ) { return ((Text)n.getFirstChild()).getData(); }}



                                                        // Copyright © 2022  Michael Allan.  Licence MIT.
