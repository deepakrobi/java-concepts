package tree;

import java.util.concurrent.Callable;

/**
 * Problem Statement
 *
 * Given the roots of two binary trees 'p' and 'q', write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they met following two conditions:
 *
 * Both tree are structurally identical.
 * Each corresponding node on both the trees have the same value.

 */
public class SameTree {

    public static boolean  isSame(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        if (p.value != q.value) {
            return false;
        }
        return isSame(p.left, q.left) &&
                isSame(p.right, q.right);

    }

    public static boolean isSameMultiThreaded(Node p, Node q) {

         Runnable runnableTask = () ->{

         };

        Callable<Boolean> callableTask = () ->{

            return false;
        };

        return false;
    }
}
