package main.datastructures;

import java.io.Serializable;

/**
 * Represents a node in a Binary Search Tree (BST).
 * Each node contains an element of type E, a reference to a parent node,
 * and references to the left and right child nodes.
 *
 * @param <E> the generic element held in a node. E must be Comparable.
 */
public class BSTreeNode<E extends Comparable<? super E>> implements Serializable {
    private E element;
    private BSTreeNode<E> left;
    private BSTreeNode<E> right;
    private BSTreeNode<E> parent;

    /**
     * Constructs a new BSTreeNode
     *
     * @param element the element to be stored in this node
     * @param parent the parent of this node
     */
    public BSTreeNode(E element, BSTreeNode<E> parent) {
        this.element = element;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    /**
     * Gets the element
     *
     * @return the element in this node
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets the element
     *
     * @param element the new element to be stored in this node
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Gets the left child
     *
     * @return the left child of this node, or null if there isn't one
     */
    public BSTreeNode<E> getLeft() {
        return left;
    }

    /**
     * Sets the left child
     *
     * @param left the new left child for this node
     */
    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    /**
     * Gets the right child
     *
     * @return the right child of this node, or null if there isn't one
     */
    public BSTreeNode<E> getRight() {
        return right;
    }

    /**
     * Sets the right child
     *
     * @param right the new right child for this node
     */
    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }

    /**
     * Gets the parent
     *
     * @return the parent of this node, or null if this node is the root
     */
    public BSTreeNode<E> getParent() {
        return parent;
    }

    /**
     * Sets the parent
     *
     * @param parent the new parent for this node
     */
    public void setParent(BSTreeNode<E> parent) {
        this.parent = parent;
    }
}
