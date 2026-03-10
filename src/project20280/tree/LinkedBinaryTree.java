package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());

        bt.createRandomTrees();
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        // TODO
        if (!isEmpty()) {
            throw new IllegalStateException("tree is not empty");
        }
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e) {
        // TODO

    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        return null;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> parent = validate(p);
        if(parent.getLeft() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> parent = validate(p);
        if(parent.getRight() != null) {
            throw new IllegalArgumentException("p already has a right child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // TODO
        Node<E> node = validate(p);
        if (isInternal(node)) {
            throw new IllegalArgumentException("p must be a leaf");
        }
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        // TODO
        Node<E> node = validate(p);
        if (numChildren(p) == 2) {
            throw new  IllegalArgumentException("p has two children");
        }

        Node<E> child = (node.getLeft() != null ?  node.getLeft() : node.getRight());

        if (child != null) {
            child.setParent(node.getParent());
        }
        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(null);

        return temp;
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        // TODO
        root = createLevelOrderHelper(l, root, 0);
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        // TODO
        if (i < l.size()) {
            Node<E> n = createNode(l.get(i), p,null, null);
            n.left = createLevelOrderHelper(l, n.left, 2 * i + 1);
            n.right = createLevelOrderHelper(l, n.right, 2 * i + 2);
            ++size;
            return n;
        }
        return p;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        // TODO
        if (i < arr.length) {
            Node<E> n = createNode(arr[i], p, null, null);
            n.left = createLevelOrderHelper(arr, n.left, 2 * i + 1);
            n.right = createLevelOrderHelper(arr, n.right, 2 * i + 2);
            ++size;
            return n;
        }
        return p;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    public void construct(E[] inorder, E[] preorder) {
        if (inorder == null || preorder == null || inorder.length == 0 || preorder.length != inorder.length) {
            return;
        }
        // set root of tree by recursively building the structure
        root = constructRecursive(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
        size = inorder.length;
    }

    // helper function to recursively build the tree

    private Node<E> constructRecursive(E[] inorder, int inStart, int inEnd, E[] preorder, int preStart, int preEnd) {
        // base case: if the range is invalid, return null
        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }

        // first element in preorder is always the root of current subtree
        E rootValue = preorder[preStart];
        Node<E> node = createNode(rootValue, null, null, null);

        // find index of the root in inorder array to split into subtrees
        int index = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i].equals(rootValue)) {
                index = i;
                break;
            }
        }

        // calculate number of nodes in the left subtree
        int leftSubtreeSize = index - inStart;

        // recursively build left and right subtrees
        node.setLeft(constructRecursive(inorder, inStart, index - 1,
                preorder, preStart + 1, preStart + leftSubtreeSize));

        node.setRight(constructRecursive(inorder, index + 1, inEnd,
                preorder, preStart + leftSubtreeSize + 1, preEnd));

        return node;
    }

    // returns a list of all root-to-leaf paths in the tree
    // each path is represented as a list of node elements

    public List<List<E>> rootToLeafPaths() {
        List<List<E>> paths = new ArrayList<>();
        if (root != null) {
            findPathsRecursive(root, new ArrayList<>(), paths);
        }
        return paths;
    }

    // helper function to collect paths
    private void findPathsRecursive(Node<E> node, List<E> currentPath, List<List<E>> allPaths) {
        if (node == null) {
            return;
        }

        // add current node to the path
        currentPath.add(node.getElement());

        // if it's a leaf node, add a copy of the current path to the results
        if (node.getLeft() == null && node.getRight() == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            // continue exploring children
            findPathsRecursive(node.getLeft(), currentPath, allPaths);
            findPathsRecursive(node.getRight(), currentPath, allPaths);
        }

        // remove current node before returning to parent
        currentPath.remove(currentPath.size() - 1);
    }

    private int diameter;

    public int diameter() {
        diameter = 0;
        if (!isEmpty()) {
            computeHeight(root());
        }
        return diameter;
    }

    private int computeHeight(Position<E> p) {
        if (p == null)
            return 0;

        int leftHeight = 0;
        int rightHeight = 0;

        if (left(p) != null)
            leftHeight = computeHeight(left(p));

        if (right(p) != null)
            rightHeight = computeHeight(right(p));

        // update diameter (longest path through this node)
        diameter = Math.max(diameter, leftHeight + rightHeight + 1);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void createRandomTrees() {
        int start = 50;
        int end = 5000;
        int step = 50;
        int totalNum = 100;

        System.out.println("n,AverageHeight:");

        for (int n = start; n <= end; n += step) {
            double totalHeight = 0;

            for (int i = 0; i < totalNum; i++) {
                LinkedBinaryTree<Integer> tree = LinkedBinaryTree.makeRandom(n);    // create random tree
                totalHeight += tree.computeHeight(tree.root());                     // compute height of each tree
            }

            double averageHeight = totalHeight / totalNum;
            System.out.println(n + "," + averageHeight);
        }
    }


    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}
