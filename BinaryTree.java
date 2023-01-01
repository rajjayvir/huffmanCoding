//This class contains the methods for the BinaryTree class.
import java.util.ArrayList;

public class BinaryTree<T> {
    private T data;
    private BinaryTree<T> parent;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree() {
        parent = left = right = null;
        data = null;
    }

    public static <T> void preorder(BinaryTree<T> t) {
        if (t != null) {
            System.out.print(t.getData() + "\t");
            preorder(t.getLeft());
            preorder(t.getRight());
        }
    }

    public static <T> void inorder(BinaryTree<T> t) {
        if (t != null) {
            inorder(t.getLeft());
            System.out.print(t.getData() + "\t");
            inorder(t.getRight());
        }
    }

    public static <T> void postorder(BinaryTree<T> t) {
        if (t != null) {
            postorder(t.getLeft());
            postorder(t.getRight());
            System.out.print(t.getData() + "\t");
        }
    }

    public static <T> int nodes(BinaryTree<T> t) {
        // TODO

        if (t == null) {
            return 0;
        }
        return 1 + nodes(t.getLeft()) + nodes(t.getRight());

    }

    public static <T> void levelOrder(BinaryTree<T> t) {

        // TODO
        ArrayList<BinaryTree<T>> q = new ArrayList<>();

        q.add(t.root());

        while (!q.isEmpty()) {

            BinaryTree<T> curr = q.remove(0);

            System.out.print((curr.data) + "  ");
            if (curr.left != null) {
                q.add(curr.getLeft());
            }
            if (curr.right != null) {
                q.add(curr.getRight());
            }
        }
    }

    public void makeRoot(T data) {
        if (!isEmpty()) {
            System.out.println("Can't make root. Already exists");
        } else
            this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTree<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTree<T> tree) {
        parent = tree;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<T> tree) {
        left = tree;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public void setRight(BinaryTree<T> tree) {
        right = tree;
    }

    public void attachLeft(BinaryTree<T> tree) {
        if (tree == null)
            return;
        else if (left != null || tree.getParent() != null) {
            System.out.println("Can't attach");
            return;
        } else {
            tree.setParent(this);
            this.setLeft(tree);
        }
    }

    public void attachRight(BinaryTree<T> tree) {
        if (tree == null)
            return;
        else if (right != null || tree.getParent() != null) {
            System.out.println("Can't attach");
            return;
        } else {
            tree.setParent(this);
            this.setRight(tree);
        }
    }

    public BinaryTree<T> detachLeft() {
        if (this.isEmpty())
            return null;
        BinaryTree<T> retLeft = left;
        left = null;
        if (retLeft != null)
            retLeft.setParent(null);
        return retLeft;
    }

    public BinaryTree<T> detachRight() {
        if (this.isEmpty())
            return null;
        BinaryTree<T> retRight = right;
        right = null;
        if (retRight != null)
            retRight.setParent(null);
        return retRight;
    }

    public boolean isEmpty() {
        if (data == null)
            return true;
        else
            return false;
    }

    public void clear() {
        left = right = parent = null;
        data = null;
    }

    public BinaryTree<T> root() {
        if (parent == null)
            return this;
        else {
            BinaryTree<T> next = parent;
            while (next.getParent() != null)
                next = next.getParent();
            return next;
        }
    }
//	public void makeRoot(char a, double v) {
//	}
}
