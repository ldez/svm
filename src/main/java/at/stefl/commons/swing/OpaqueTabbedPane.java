package at.stefl.commons.swing;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class OpaqueTabbedPane extends JTabbedPane {

    private static final long serialVersionUID = 3155045858089276751L;

    private static final Color OPAQUE = new Color(0, 0, 0, 1);

    public OpaqueTabbedPane() {}

    public OpaqueTabbedPane(final int tabPlacement) {
        super(tabPlacement);
    }

    public OpaqueTabbedPane(final int tabPlacement, final int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
    }

    @Override
    public void insertTab(final String title, final Icon icon, final Component component, final String tip, final int index) {
        super.insertTab(title, icon, component, tip, index);

        if (component instanceof JComponent) {
            ((JComponent) component).setOpaque(true);
        } else {
            component.setBackground(OPAQUE);
        }
    }

}