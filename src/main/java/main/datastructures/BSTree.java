package main.datastructures;

import main.datastructures.interfaces.*;
import main.exceptions.TreeException;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A Binary Search Tree (BST) data structure.
 *
 * @param <E> the generic type of elements in this tree. E must be Comparable.
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {

    private BSTreeNode<E> root;
    private int size;

    /**
     * Constructs an empty BSTree.
     */
    public BSTree() {
        root = null;
        size = 0;
    }

    /**
     * Returns the root of the tree.
     *
     * @return                  the root of the tree
     * @throws TreeException    if the tree is empty
     */
    @Override
    public BSTreeNode<E> getRoot() throws TreeException {
        if (root == null)
            throw new TreeException("Tree is empty!");
        return root;
    }

    /**
     * Returns the height of the tree.
     *
     * @return the height of the tree
     */
    @Override
    public int getHeight() {
        return getHeight(root);
    }

    // helper method to calculate height
    private int getHeight(BSTreeNode<E> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the tree, removing all elements.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Checks if the tree contains the specified element.
     *
     * @param   entry the element to search for
     * @return  true if the tree contains the element, false otherwise
     */
    @Override
    public boolean contains(E entry) {
        if (root == null)
            return false;
        return search(root, entry) != null;
    }

    /**
     * Returns the node containing the specified element.
     *
     * @param   entry the element to search for
     * @return  the node containing the element, or null if the element is not in the tree
     */
    @Override
    public BSTreeNode<E> search(E entry) {
        if (root == null)
            return null;
        return search(root, entry);
    }

    // helper method for recursive search
    private BSTreeNode<E> search(BSTreeNode<E> node, E entry) {
        if (node == null)
            return null;
        int compare = entry.compareTo(node.getElement());
        if (compare < 0)
            return search(node.getLeft(), entry);
        else if (compare > 0)
            return search(node.getRight(), entry);
        else
            return node;
    }

    /**
     * Adds a new element to the tree.
     *
     * @param   newEntry the element to add
     * @return  true if the element was added, false if the element already exists in the tree
     * @throws  NullPointerException if the specified element is null
     */
    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null)
            throw new NullPointerException("Cannot add null to a tree");
        if (contains(newEntry))
            return false;
        root = add(root, newEntry);
        size++;
        return true;
    }

    // helper method for recursive add
    private BSTreeNode<E> add(BSTreeNode<E> node, E entry) {
        if (node == null) {
            return new BSTreeNode<>(entry, null);
        }
        int compare = entry.compareTo(node.getElement());
        if (compare < 0) {
            node.setLeft(add(node.getLeft(), entry));
        } else if (compare > 0) {
            node.setRight(add(node.getRight(), entry));
        }
        return node;
    }

    /**
     * Returns an iterator for traversing the tree in in-order sequence.
     *
     * @return an in-order iterator over the tree elements
     */
    @Override
    public Iterator<E> inorderIterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<E> {
        private Stack<BSTreeNode<E>> stack = new Stack<>();

        public InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(BSTreeNode<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            BSTreeNode<E> node = stack.pop();
            pushLeft(node.getRight());
            return node.getElement();
        }
    }

    /**
     * Returns an iterator for traversing the tree in pre-order sequence.
     *
     * @return a pre-order iterator over the tree elements
     */
    @Override
    public Iterator<E> preorderIterator() {
        return new PreOrderIterator();
    }

    private class PreOrderIterator implements Iterator<E> {
        private Stack<BSTreeNode<E>> stack = new Stack<>();

        public PreOrderIterator() {
            if (root != null) {
                stack.push(root);
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            BSTreeNode<E> node = stack.pop();
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
            return node.getElement();
        }
    }

    /**
     * Returns an iterator for traversing the tree in post-order sequence.
     *
     * @return a post-order iterator over the tree elements
     */
    @Override
    public Iterator<E> postorderIterator() {
        return new PostOrderIterator();
    }

    private class PostOrderIterator implements Iterator<E> {
        private Stack<BSTreeNode<E>> stack = new Stack<>();
        private BSTreeNode<E> lastNodeVisited = null;

        public PostOrderIterator() {
            if (root != null) {
                stack.push(root);
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            while (true) {
                BSTreeNode<E> top = stack.peek();
                if (lastNodeVisited == null || lastNodeVisited.getLeft() == top || lastNodeVisited.getRight() == top) {
                    if (top.getLeft() != null) {
                        stack.push(top.getLeft());
                    } else if (top.getRight() != null) {
                        stack.push(top.getRight());
                    }
                } else if (top.getLeft() == lastNodeVisited) {
                    if (top.getRight() != null) {
                        stack.push(top.getRight());
                    }
                } else {
                    return stack.pop().getElement();
                }
                lastNodeVisited = top;
            }
        }
    }

}
