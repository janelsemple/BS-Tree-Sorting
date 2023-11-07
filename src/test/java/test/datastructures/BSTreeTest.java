package test.datastructures;

import main.datastructures.BSTree;
import main.datastructures.BSTreeNode;
import main.datastructures.interfaces.Iterator;
import main.exceptions.TreeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import test.TestResultsLogger;

/**
 * This class contains tests for the Binary Search Tree (BST) data structure.
 */
@ExtendWith(TestResultsLogger.class)
public class BSTreeTest {

    /**
     * The BSTree that will be used in the tests.
     */
    private BSTree<Integer> bstree;

    /**
     * This method is executed before each test. It initializes the BSTree.
     */
    @BeforeEach
    public void setUp() {
        bstree = new BSTree<>();
    }

    /**
     * Tests if the getRoot method throws an exception when the tree is empty.
     */
    @Test
    public void testGetRootWhenTreeIsEmpty() {
        Exception exception = assertThrows(TreeException.class, () -> {
            bstree.getRoot();
        });
        assertEquals("Tree is empty!", exception.getMessage());
    }

    /**
     * Tests the getRoot method when the tree is not empty.
     */
    @Test
    public void testGetRootWhenTreeIsNotEmpty() {
        Integer rootValue = 5;
        bstree.add(rootValue);
        try {
            assertEquals(rootValue, bstree.getRoot().getElement());
        } catch (TreeException e) {
            fail("Exception should not have been thrown");
        }
    }

    /**
     * Tests the isEmpty method.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(bstree.isEmpty());
        bstree.add(10);
        assertFalse(bstree.isEmpty());
    }

    /**
     * Tests the size method.
     */
    @Test
    public void testSize() {
        assertEquals(0, bstree.size());
        bstree.add(10);
        assertEquals(1, bstree.size());
    }

    /**
     * Tests the getHeight method.
     */
    @Test
    public void testHeight() {
        assertEquals(-1, bstree.getHeight());
        bstree.add(10);
        assertEquals(0, bstree.getHeight());
        bstree.add(20);
        assertEquals(1, bstree.getHeight());
    }

    /**
     * Tests the contains method.
     */
    @Test
    public void testContains() {
        assertFalse(bstree.contains(10));
        bstree.add(10);
        assertTrue(bstree.contains(10));
    }

    /**
     * Tests the setElement method of the BSTreeNode.
     */
    @Test
    public void testSetElement() {
        BSTreeNode<Integer> node = new BSTreeNode<>(10, null);
        assertEquals(10, node.getElement());
        node.setElement(20);
        assertEquals(20, node.getElement());
    }

    /**
     * Tests the getParent and setParent methods of the BSTreeNode.
     */
    @Test
    public void testGetAndSetParent() {
        BSTreeNode<Integer> parentNode = new BSTreeNode<>(10, null);
        BSTreeNode<Integer> childNode = new BSTreeNode<>(20, parentNode);
        assertEquals(parentNode, childNode.getParent());
        BSTreeNode<Integer> newParentNode = new BSTreeNode<>(30, null);
        childNode.setParent(newParentNode);
        assertEquals(newParentNode, childNode.getParent());
    }

    /**
     * Tests the search method.
     */
    @Test
    public void testSearch() {
        assertNull(bstree.search(10));
        bstree.add(10);
        BSTreeNode<Integer> node = bstree.search(10);
        assertNotNull(node);
        assertEquals(10, node.getElement());
    }

    /**
     * Tests the add method.
     */
    @Test
    public void testAdd() {
        assertTrue(bstree.add(10));
        assertFalse(bstree.add(10));
    }

    /**
     * Tests the clear method.
     */
    @Test
    public void testClear() {
        bstree.add(10);
        assertFalse(bstree.isEmpty());
        bstree.clear();
        assertTrue(bstree.isEmpty());
    }

    /**
     * Tests the inorderIterator.
     */
    @Test
    public void testInorderIterator() {
        bstree.add(20);
        bstree.add(10);
        bstree.add(30);
        Iterator<Integer> iterator = bstree.inorderIterator();
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(30, iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Tests the preorderIterator.
     */
    @Test
    public void testPreorderIterator() {
        bstree.add(20);
        bstree.add(10);
        bstree.add(30);
        Iterator<Integer> iterator = bstree.preorderIterator();
        assertTrue(iterator.hasNext());
        assertEquals(20, iterator.next());
        assertEquals(10, iterator.next());
        assertEquals(30, iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Tests the postorderIterator.
     */
    @Test
    public void testPostorderIterator() {
        bstree.add(20);
        bstree.add(10);
        bstree.add(30);
        Iterator<Integer> iterator = bstree.postorderIterator();
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(20, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
