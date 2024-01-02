/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 14/11/23 07:33:39
 *
 *  @author Finn Farrell
 *          Gemma Devally (for explaining the funcionality of printKeysInOrder)
 *
 *************************************************************************/
package csu22011_a3;

import java.util.ArrayList;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }

        public Node findMax(Node node){
            if(node == null){
                return null;
            }
            else{
                while(node.right != null){
                    node = node.right;
                }
                return node;
            }

        }

    }
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: TODO
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */

    public int height() {
        if(size() == 0){
            return -1;
        }
        else if (size() == 1){
            return 0;
        }
        else {
            return (height(root)-1);
        }
        // Run time theta(N)
    }
    private int height(Node node){
        // Comparison statement theta(N)
        if(node == null)
        {
            return 0;
        }
        else{
            // Variable declaration x2 theta(1)
            int depthLeft = height(node.left);
            int depthRight = height(node.right);

            // If else statement theta(1)
            if(depthLeft > depthRight){
                return depthLeft+1;
            }
            else {
                return depthRight+1;
            }
        }
        // Run time Theta(N)
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
        // If statement theta(N) where N is the number of possible arguments isEmpty() may check
        if (isEmpty()) {
            return null;
        }
        // Return statement theta(1)
        else{
          return median(root);
        }
        // Run Time theta(h) relative to the size of the tree
    }

    private Key median(Node node){
        // Variable declaration and computation theta(h), where h is the worst possible height of Binary Search Tree.
        int desiredK = ((size()-1)/2);
        // Variable return theta(1)
        return select(desiredK);
    }

    // Select taken from Lecture Slides wk13
    public Key select(int n) {
        if (n < 0 || n >= size()) return null;
        Node x = select(root, n);
        return x.key;
    }
    private Node select(Node x, int n) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > n) return select(x.left, n);
        else if (t < n) return select(x.right, n-t-1);
        else return x;
    }

    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */

    String output = "";
    public String printKeysInOrder() {
        output = "";
        if (isEmpty()) {
            return "()";
        }
        else{
            printKeysInOrder(root);
            return output;
        }
    }
    private void printKeysInOrder(Node node){
        output = output+"(";

        if(node != null){
            // Recursively checks left subtree.
            printKeysInOrder(node.left);

            // adds current nodes key to output.
            output += node.key;

            // Recursively checks right subtree.
            printKeysInOrder(node.right);
        }

        output = output+")";
    }

    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     * 
     */

    //  -7
    //   |-3
    //   | |-1
    //   | | |-null
    //   | |  -2
    //   | |   |-null
    //   | |    -null
    //   |  -6
    //   |   |-4
    //   |   | |-null
    //   |   |  -5
    //   |   |   |-null
    public String prettyPrintKeys() {
        // Base Case Empty Tree
        if (isEmpty()) {
            return "-null\n";
        } else {
            return prettyPrint(root, "");
        }
    }

    private String prettyPrint(Node node, String prefix) {
        if (node == null) {
            return prefix + "-null\n";
        }
        // Enter node > leftSubtree > RightSubtree
        else {

            String output = prefix + "-" + node.key + "\n" +

                    prettyPrint((node.left), prefix + " |") +

                    prettyPrint((node.right), prefix + "  ");

            return output;
        }
    }


    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
        // Call the private delete method starting from the root
        root = delete(root, key);
    }
    private Node delete(Node node, Key key){
        // Base Case , return null as there is nothing to delete.
        if(node == null){
            return null;
        }

        // Find node
        // If the key to be deleted is smaller, it lies in the left subtree
        if(key.compareTo(node.key) < 0){
            // Recursively call delete on the left subtree
            node.left = delete(node.left, key);
        }

        // If the key to be deleted is larger, it lies in the right subtree
        else if (key.compareTo(node.key) > 0){
            // Recursively call delete on the right subtree
            node.right = delete(node.right, key);
        }

        // If the key to be deleted is equal to the current node's key
        else{
            // Case 1: Node with only one child or no child
            if(node.left == null){
                return node.right;
            }
            else if(node.right == null){
                return node.left;
            }

            // Case 2: Node with two children
            if(node != null){
                // Find predecessor, max in left subtree.
                node.key = node.findMax(node.left).key;
                node.val = node.findMax(node.left).val;
                // Delete the predecessor
                node.left = delete(node.left, node.key);
            }
        }
        return node;
    }

    /**
     * This method takes an array of Key objects and determines whether the
     * given sequence can represent the pre-order traversal of a BST.
     *
     * @param keys an array of keys
     */

  
    ArrayList<Key> keys ;
    public boolean isBSTPreOrder(Key[] keys){
        // Add keys into BST from L -> R
        BST<Key, Key> bst = new BST<Key, Key>();
        // Puts keys into key parameters
        for(Key key: keys){ // og for loop
            bst.put(key, key);
        }

        // Call recursive preorder method
        ArrayList<Key> preOrder = bst.getPreOrder();


        // Compare Arraylist to BST with Keys
        if(preOrder.size() <= 1){
            return true;
        }
        for(int index = 0; index < keys.length; index++){
            if(preOrder.get(index).compareTo(keys[index]) != 0){
                return  false;
            }
        }
    return true;

    }

public ArrayList<Key> getPreOrder(){
        ArrayList<Key> preOrder = new ArrayList<>();
        return getPreOrder(preOrder, root);
}

private ArrayList<Key> getPreOrder ( ArrayList<Key> preOrder, Node node){
        // Base Case
        if(node == null){
            return preOrder;
        }
        // Builds preOrder ArrayList
        else{
            preOrder.add(node.key);
            getPreOrder(preOrder, node.left);
            getPreOrder(preOrder, node.right);
        }
        return preOrder;
    }

}
