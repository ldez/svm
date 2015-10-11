package at.stefl.commons.swing;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class CloseableTabbedPane extends JTabbedPane {

    private static final long serialVersionUID = -5464687282253704817L;

    private static final Dimension CLOSE_BUTTON_DIMENSION = new Dimension(15, 15);

    private static class CloseButton extends JButton {

        private static final long serialVersionUID = -5676070536771878602L;

        public CloseButton() {
            this.setContentAreaFilled(false);
            this.setFocusable(false);
            this.setBorder(BorderFactory.createEtchedBorder());
            this.setBorderPainted(false);
            this.setRolloverEnabled(true);

            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(final MouseEvent e) {
                    final Component component = e.getComponent();
                    if (component instanceof AbstractButton) {
                        final AbstractButton button = (AbstractButton) component;
                        button.setBorderPainted(true);
                    }
                }

                @Override
                public void mouseExited(final MouseEvent e) {
                    final Component component = e.getComponent();
                    if (component instanceof AbstractButton) {
                        final AbstractButton button = (AbstractButton) component;
                        button.setBorderPainted(false);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final Graphics2D g2 = (Graphics2D) g.create();

            if (this.getModel().isPressed()) {
                g2.translate(1, 1);
            }

            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);

            if (this.getModel().isRollover()) {
                g2.setColor(Color.GRAY);
            }

            final int delta = (int) Math.sqrt(this.getWidth() + 2);
            g2.drawLine(delta, delta, this.getWidth() - delta - 1, this.getHeight() - delta - 1);
            g2.drawLine(this.getWidth() - delta - 1, delta, delta, this.getHeight() - delta - 1);
            g2.dispose();
        }
    }

    private class TabComponent extends JComponent implements ActionListener {

        private static final long serialVersionUID = 5794456221719406818L;

        public TabComponent(final String title) {
            this.setLayout(new BorderLayout());
            this.setName(title);
            this.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));

            final JLabel label = new JLabel(title);
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
            final CloseButton close = new CloseButton();
            close.setPreferredSize(CLOSE_BUTTON_DIMENSION);

            this.add(label, BorderLayout.CENTER);
            this.add(close, BorderLayout.EAST);

            close.addActionListener(this);
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            final int index = CloseableTabbedPane.this.indexOfTabComponent(this);
            if (index != -1) {
                CloseableTabbedPane.this.remove(index);
            }
        }
    }

    @Override
    public void insertTab(final String title, final Icon icon, final Component component, final String tip, final int index) {
        super.insertTab(null, icon, component, tip, index);

        this.setTabComponentAt(index, new TabComponent(title));
    }

    @Override
    public String getTitleAt(final int index) {
        final Component component = this.getTabComponentAt(index);

        if (component == null) {
            return null;
        }

        return this.getTabComponentAt(index).getName();
    }

}