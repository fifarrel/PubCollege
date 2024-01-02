package csu22011_a3;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 14/11/23 07:34:13
 *
 *  @author  Finn Farrell
 */

@RunWith(JUnit4.class)
public class BSTTest
{

    //TODO write more tests here.


    /** <p>Test {@link BST#prettyPrintKeys()}.</p> */

    @Test
    public void testPrintInOrder()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(3, 3);
        String str = bst.printKeysInOrder();
        assertEquals(str, "(()1(()2(()3())))");
    }

    @Test
    public void testMedian(){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(1,1);
        bst.put(2,2);
        bst.put(3,3);
        assertEquals("Median of 3",2,(int)bst.median());

        bst.put(4,4);
        bst.put(5,5);
        bst.put(6,6);
        assertEquals("Median of 6",3,(int)bst.median());
    }

    @Test
    public void testHeight(){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Empty Tree",-1,bst.height());

        bst.put(1,1);
        assertEquals("Single Node",0,bst.height());

        bst.put(2,2);
        bst.put(3,3);
        assertEquals("Height 2",2,bst.height());
    }

    @Test
    public void testPrettyPrint() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking pretty printing of empty tree",
                "-null\n", bst.prettyPrintKeys());

        //  -7
        //   |-3
        //   | |-1
        //   | | |-null
        bst.put(7, 7);       //   | |  -2
        bst.put(8, 8);       //   | |   |-null
        bst.put(3, 3);       //   | |    -null
        bst.put(1, 1);       //   |  -6
        bst.put(2, 2);       //   |   |-4
        bst.put(6, 6);       //   |   | |-null
        bst.put(4, 4);       //   |   |  -5
        bst.put(5, 5);       //   |   |   |-null
        //   |   |    -null
        //   |    -null
        //    -8
        //     |-null
        //      -null

        String result =
                "-7\n" +
                        " |-3\n" +
                        " | |-1\n" +
                        " | | |-null\n" +
                        " | |  -2\n" +
                        " | |   |-null\n" +
                        " | |    -null\n" +
                        " |  -6\n" +
                        " |   |-4\n" +
                        " |   | |-null\n" +
                        " |   |  -5\n" +
                        " |   |   |-null\n" +
                        " |   |    -null\n" +
                        " |    -null\n" +
                        "  -8\n" +
                        "   |-null\n" +
                        "    -null\n";
        assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
    }


    /** <p>Test {@link BST#delete(Comparable)}.</p> */
    @Test
    public void testDelete() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.delete(1);
        assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
        //         5

        assertEquals("Checking order of constructed tree",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(9);
        assertEquals("Deleting non-existent key",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(8);
        assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

        bst.delete(6);
        assertEquals("Deleting node with single child",
                "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

        bst.delete(3);
        assertEquals("Deleting node with two children",
                "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
    }


    @Test
    public void testisBSTPreOrder() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();

        Integer[] keys = new Integer[0];
        assertTrue("Empty array should be considered a valid pre-order traversal", bst.isBSTPreOrder(keys));

        keys = new Integer[] { 1 };
        assertTrue("Array with one element should be considered a valid pre-order traversal", bst.isBSTPreOrder(keys));

        keys = new Integer[] { 10, 5, 1, 7, 40, 60 };
        assertTrue("This array represents a valid pre-order traversal of a BST", bst.isBSTPreOrder(keys));

        keys = new Integer[] { 10, 50, 1, 7, 40, 60 };
        assertFalse("This array does not represent a valid pre-order traversal of a BST", bst.isBSTPreOrder(keys));
    }
}



