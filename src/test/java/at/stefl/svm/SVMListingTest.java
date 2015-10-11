package at.stefl.svm;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Set;

import javax.swing.JFileChooser;

import org.junit.Test;

import at.stefl.svm.enumeration.ActionType;
import at.stefl.svm.io.SVMReader;
import at.stefl.svm.object.SVMHeader;
import at.stefl.svm.object.action.SVMAction;
import at.stefl.svm.object.action.UnsupportedAction;

public class SVMListingTest {

    @Test
    public void should_testName() throws Exception {
        final JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        final InputStream in = new FileInputStream(chooser.getSelectedFile());

        // InputStream in =
        // SVMListingTest.class.getResourceAsStream("test.svm");

        final SVMReader reader = new SVMReader(in);

        final SVMHeader header = reader.readHeader();
        System.out.println(header);
        System.out.println();

        final Set<ActionType> supportedActions = EnumSet.noneOf(ActionType.class);
        final Set<ActionType> unsupportedActions = EnumSet.noneOf(ActionType.class);

        for (int i = 0; i < header.getActionCount(); i++) {
            final SVMAction actionObject = reader.readAction();

            if (actionObject instanceof UnsupportedAction) {
                unsupportedActions.add(actionObject.getActionType());
            } else {
                supportedActions.add(actionObject.getActionType());
            }

            System.out.println(actionObject);
        }

        System.out.println();
        System.out.println();

        System.out.println("supported actions: " + supportedActions);
        System.out.println("unsupported actions: " + unsupportedActions);
    }

}