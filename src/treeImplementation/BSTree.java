package treeImplementation;

import utilities.BSTreeADT;
import utilities.Iterator;

import java.util.NoSuchElementException;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {
    private BSTreeNode<E> root;
    private int size;

    public BSTree() {
        root = null;
        size = 0;
    }

    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("Tree is empty");
        }
        return root;
    }

    @Override
    public int getHeight() {
        return getHeightHelper(root);
    }

    private int getHeightHelper(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeightHelper(node.getLeft());
        int rightHeight = getHeightHelper(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Entry cannot be null");
        }
        return search(entry) != null;
    }

    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Entry cannot be null");
        }
        return searchHelper(root, entry);
    }

    private BSTreeNode<E> searchHelper(BSTreeNode<E> node, E entry) {
        if (node == null || entry.compareTo(node.getData()) == 0) {
            return node;
        }
        if (entry.compareTo(node.getData()) < 0) {
            return searchHelper(node.getLeft(), entry);
        } else {
            return searchHelper(node.getRight(), entry);
        }
    }

    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) {
            throw new NullPointerException("Entry cannot be null");
        }
        boolean wasAdded = addHelper(root, newEntry);
        if (wasAdded) {
            size++;
        }
        return wasAdded;
    }
    
    private boolean addHelper(BSTreeNode<E> node, E newEntry) {
        if (node == null) {
            root = new BSTreeNode<>(newEntry);
            return true;
        } else if (newEntry.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTreeNode<>(newEntry));
                return true;
            } else {
                return addHelper(node.getLeft(), newEntry);
            }
        } else if (newEntry.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTreeNode<>(newEntry));
                return true;
            } else {
                return addHelper(node.getRight(), newEntry);
            }
        } else {
            return false;
        }
    }
    @Override
    public BSTreeNode<E> removeMin() {
        if (isEmpty()) {
            return null;
        }
        BSTreeNode<E> min = findMin();
        root = removeMinHelper(root);
        size--;
        return min;
    }
    
    private BSTreeNode<E> removeMinHelper(BSTreeNode<E> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }
        node.setLeft(removeMinHelper(node.getLeft()));
        return node;
    }

    @Override
    public BSTreeNode<E> removeMax() {
        if (isEmpty()) {
            return null;
        }
        BSTreeNode<E> max = findMax();
        root = removeMaxHelper(root);
        size--;
        return max;
    }
    
    private BSTreeNode<E> removeMaxHelper(BSTreeNode<E> node) {
        if (node.getRight() == null) {
            return node.getLeft();
        }
        node.setRight(removeMaxHelper(node.getRight()));
        return node;
    }
    
    @Override
    public Iterator<E> inorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<E> preorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<E> postorderIterator() {
        return new PostorderIterator();
    }

    private BSTreeNode<E> findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty");
        }
        return findMinHelper(root);
    }

    private BSTreeNode<E> findMinHelper(BSTreeNode<E> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return findMinHelper(node.getLeft());
    }

    private BSTreeNode<E> findMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty");
        }
        return findMaxHelper(root);
    }

    private BSTreeNode<E> findMaxHelper(BSTreeNode<E> node) {
        if (node.getRight() == null) {
            return node;
        }
        return findMaxHelper(node.getRight());
    }

    private class InorderIterator implements Iterator<E> {
        private java.util.Stack<BSTreeNode<E>> stack;

        public InorderIterator() {
            stack = new java.util.Stack<>();
            pushLeft(root);
        }

        private void pushLeft(BSTreeNode<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTreeNode<E> node = stack.pop();
            pushLeft(node.getRight());
            return node.getData();
        }
    }

    private class PreorderIterator implements Iterator<E> {
        private java.util.Stack<BSTreeNode<E>> stack;

        public PreorderIterator() {
            stack = new java.util.Stack<>();
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTreeNode<E> node = stack.pop();
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
            return node.getData();
        }
    }

    private class PostorderIterator implements Iterator<E> {
        private java.util.Stack<BSTreeNode<E>> stack;

        public PostorderIterator() {
            stack = new java.util.Stack<>();
            pushLeft(root);
        }

        private void pushLeft(BSTreeNode<E> node) {
            while (node != null) {
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
                }
                BSTreeNode<E> node = stack.pop();
                if (!stack.isEmpty() && node.getRight() == stack.peek()) {
                stack.pop();
                pushLeft(node.getRight());
                }
                return node.getData();
                }
                }
                }