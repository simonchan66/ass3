package BSTreeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import treeImplementation.BSTree;
import treeImplementation.BSTreeNode;
import utilities.BSTreeADT;
import utilities.Iterator;


import static org.junit.Assert.*;


public class bsTreeTest {
    // Attributes
    private BSTreeADT<Integer> bsTree;
    private Integer one;
    private Integer two;
    private Integer three;
    private Integer four;
    private Integer five;
    
    
    /**
     * Sets up the test environment before each test method.
     */
    @Before
    public void setUp() {
        bsTree = new BSTree<>(); 
        one = Integer.valueOf(1);
        two = Integer.valueOf(2);
        three = Integer.valueOf(3);
        four = Integer.valueOf(4);
        five = Integer.valueOf(5);
    }

	
    /**
     * Tears down the test environment after each test method.
     */
    @After
    public void tearDown() {
        bsTree = null;
        one = null;
        two = null;
        three = null;
        four = null;
        five = null;
    }

	  /**
     * Test method for {@link BSTree#getRoot()} with an empty tree.
     */
    @Test(expected = NullPointerException.class)
    public void testGetRoot_EmptyTree() {
        bsTree.getRoot();
    }

    /**
     * Test method for {@link BSTree#getRoot()} with a non-empty tree.
     */
    @Test
    public void testGetRoot_NonEmptyTree() {
        bsTree.add(three);
        BSTreeNode<Integer> root = bsTree.getRoot();
        assertEquals("Root element is not correct", three, root.getData());
    }

	/**
     * Test method for {@link BSTree#getHeight()} with an empty tree.
     */
    @Test
    public void testGetHeight_EmptyTree() {
        assertEquals("Height of an empty tree should be 0", 0, bsTree.getHeight());
    }

    /**
     * Test method for {@link BSTree#getHeight()} with a non-empty tree.
     */
    @Test
    public void testGetHeight_NonEmptyTree() {
        bsTree.add(three);
        bsTree.add(one);
        bsTree.add(five);
        assertEquals("Height of the tree is not correct", 2, bsTree.getHeight());
    }

	/**
     * Test method for {@link BSTree#size()} with an empty tree.
     */
    @Test
    public void testSize_EmptyTree() {
        assertEquals("Size of an empty tree should be 0", 0, bsTree.size());
    }

    /**
     * Test method for {@link BSTree#size()} with a non-empty tree.
     */
    @Test
    public void testSize_NonEmptyTree() {
        bsTree.add(three);
        bsTree.add(one);
        bsTree.add(five);
        assertEquals("Size of the tree is not correct", 3, bsTree.size());
    }

    /**
     * Test method for {@link BSTree#isEmpty()} with an empty tree.
     */
    @Test
    public void testIsEmpty_EmptyTree() {
        assertTrue("Empty tree should return true for isEmpty", bsTree.isEmpty());
    }

    /**
     * Test method for {@link BSTree#isEmpty()} with a non-empty tree.
     */
    @Test
    public void testIsEmpty_NonEmptyTree() {
        bsTree.add(three);
        assertFalse("Non-empty tree should return false for isEmpty", bsTree.isEmpty());
    }

	/**
     * Test method for {@link BSTree#clear()}.
     */
    @Test
    public void testClear() {
        bsTree.add(three);
        bsTree.add(one);
        bsTree.add(five);
        bsTree.clear();
        assertTrue("Tree should be empty after clear", bsTree.isEmpty());
        assertEquals("Size should be 0 after clear", 0, bsTree.size());
    }

    /**
     * Test method for {@link BSTree#contains(Comparable)} with an element present in the tree.
     */
    @Test
    public void testContains_ElementExists() {
        bsTree.add(three);
        bsTree.add(one);
        bsTree.add(five);
        assertTrue("Tree should contain the specified element", bsTree.contains(one));
    }

    /**
     * Test method for {@link BSTree#contains(Comparable)} with an element not present in the tree.
     */
    @Test
    public void testContains_ElementNotExists() {
        bsTree.add(three);
        bsTree.add(one);
        bsTree.add(five);
        assertFalse("Tree should not contain the specified element", bsTree.contains(two));
    }

    /**
     * Test method for {@link BSTree#contains(Comparable)} with a null element.
     */
    @Test(expected = NullPointerException.class)
    public void testContains_NullElement() {
        bsTree.contains(null);
    }

    /**
     * Test method for {@link BSTree#search(Comparable)} with an element present in the tree.
     */
    @Test
    public void testSearch_ElementExists() {
        bsTree.add(three);
        bsTree.add(one);
        bsTree.add(five);
        BSTreeNode<Integer> node = bsTree.search(one);
        assertNotNull("Search should return a non-null node", node);
        assertEquals("Search should return the correct node", one, node.getData());
    }

    /**
     * Test method for {@link BSTree#search(Comparable)} with an element not present in the tree.
     */
    @Test
    public void testSearch_ElementNotExists() {
        bsTree.add(three);
        bsTree.add(one);
        bsTree.add(five);
        BSTreeNode<Integer> node = bsTree.search(two);
        assertNull("Search should return null for a non-existent element", node);
    }

    /**
     * Test method for {@link BSTree#search(Comparable)} with a null element.
     */
    @Test(expected = NullPointerException.class)
    public void testSearch_NullElement() {
        bsTree.search(null);
    }

	
/**
     * Test method for {@link BSTree#add(Comparable)} with a new element.
     */
    @Test
    public void testAdd_NewElement() {
        assertTrue("Adding a new element should return true", bsTree.add(three));
        assertTrue("Tree should contain the added element", bsTree.contains(three));
    }

    /**
     * Test method for {@link BSTree#add(Comparable)} with a duplicate element.
     */
    @Test
    public void testAdd_DuplicateElement() {
        bsTree.add(three);
        assertFalse("Adding a duplicate element should return false", bsTree.add(three));
    }

    /**
     * Test method for {@link BSTree#add(Comparable)} with a null element.
     */
    @Test(expected = NullPointerException.class)
    public void testAdd_NullElement() {
        bsTree.add(null);
    }

    /**
     * Test method for {@link BSTree#removeMin()} with an empty tree.
     */
    @Test
    public void testRemoveMin_EmptyTree() {
        assertNull("Removing the minimum element from an empty tree should return null", bsTree.removeMin());
    }

    /**
     * Test method for {@link BSTree#removeMin()} with a non-empty tree.
     */
	@Test
	public void testRemoveMin_NonEmptyTree() {
		bsTree.add(three);
		bsTree.add(one);
		bsTree.add(five);
		BSTreeNode<Integer> min = bsTree.removeMin();
		assertEquals("Removing the minimum element should return the correct value", one, min.getData());
		assertFalse("Tree should not contain the removed element", bsTree.contains(one));
	}

    /**
     * Test method for {@link BSTree#removeMax()} with an empty tree.
     */
    @Test
    public void testRemoveMax_EmptyTree() {
        assertNull("Removing the maximum element from an empty tree should return null", bsTree.removeMax());
    }

    /**
     * Test method for {@link BSTree#removeMax()} with a non-empty tree.
     */
	@Test
	public void testRemoveMax_NonEmptyTree() {
		bsTree.add(three);
		bsTree.add(one);
		bsTree.add(five);
		BSTreeNode<Integer> max = bsTree.removeMax();
		assertEquals("Removing the maximum element should return the correct value", five, max.getData());
		assertFalse("Tree should not contain the removed element", bsTree.contains(five));
	}

	

}

