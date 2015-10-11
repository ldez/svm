package at.stefl.commons.swing;

import java.awt.Rectangle;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import at.stefl.commons.util.iterator.EnumerationIterable;
import at.stefl.commons.util.primitive.IntegerReference;

public class JTreeUtil {

    public static void setVisableRow(final JTree tree, final int row) {
        final Rectangle rect = tree.getRowBounds(row);
        tree.scrollRectToVisible(rect);
    }

    public static void collapseAll(final JTree tree) {
        final TreeNode root = (TreeNode) tree.getModel().getRoot();
        if (root == null) {
            return;
        }

        collapseAll(tree, new TreePath(root));
    }

    @SuppressWarnings("unchecked")
    private static void collapseAll(final JTree tree, final TreePath path) {
        final TreeNode node = (TreeNode) path.getLastPathComponent();

        for (final TreeNode child : new EnumerationIterable<TreeNode>(node.children())) {
            final TreePath childPath = path.pathByAddingChild(child);
            collapseAll(tree, childPath);
        }

        tree.collapsePath(path);
    }

    public static void expandAll(final JTree tree) {
        final TreeNode root = (TreeNode) tree.getModel().getRoot();
        if (root == null) {
            return;
        }

        expandAll(tree, new TreePath(root));
    }

    @SuppressWarnings("unchecked")
    private static boolean expandAll(final JTree tree, final TreePath path) {
        final TreeNode node = (TreeNode) path.getLastPathComponent();
        boolean expanded = false;

        for (final TreeNode child : new EnumerationIterable<TreeNode>(node.children())) {
            final TreePath childPath = path.pathByAddingChild(child);
            expanded |= expandAll(tree, childPath);
        }

        if (expanded) {
            return true;
        }
        if (node.isLeaf()) {
            return false;
        }

        tree.expandPath(path);
        return true;
    }

    public static TreePath findNode(final JTree tree, final Object matcher) {
        return findNode(tree, matcher, null);
    }

    public static TreePath findNode(final JTree tree, final Object matcher, final TreePath offset) {
        final TreeNode root = (TreeNode) tree.getModel().getRoot();
        if (root == null) {
            return null;
        }

        return findNode(tree, matcher, offset, new IntegerReference(), new TreePath(root));
    }

    @SuppressWarnings("unchecked")
    private static TreePath findNode(final JTree tree, final Object matcher, final TreePath offset, final IntegerReference offsetReference, final TreePath path) {
        final TreeNode node = (TreeNode) path.getLastPathComponent();

        if ((offset != null) && (offset.getPathCount() > offsetReference.value)) {
            final Object offsetComponent = offset.getPathComponent(offsetReference.value);
            if (node != offsetComponent) {
                return null;
            }

            offsetReference.value++;
        } else {
            if (matcher.equals(node)) {
                return path;
            }
        }

        for (final TreeNode child : new EnumerationIterable<TreeNode>(node.children())) {
            final TreePath childPath = path.pathByAddingChild(child);
            final TreePath result = findNode(tree, matcher, offset, offsetReference, childPath);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private JTreeUtil() {}

}