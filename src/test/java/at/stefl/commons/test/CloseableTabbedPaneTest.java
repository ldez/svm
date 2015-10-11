package at.stefl.commons.test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import org.junit.Test;

import at.stefl.commons.swing.CloseableTabbedPane;

public class CloseableTabbedPaneTest {

    @Test
    public void should_testName() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        final JTabbedPane tabbedPane = new CloseableTabbedPane();
        tabbedPane.add("asdf", new JLabel("asdf"));
        tabbedPane.add("fdsa", new JLabel("fdsa"));

        final JFrame frame = new JFrame();
        frame.add(tabbedPane);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}