package at.stefl.commons.test;

import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

import org.junit.Test;

import at.stefl.commons.lwxml.LWXMLUtil;
import at.stefl.commons.lwxml.path.LWXMLPath;

public class LWXMLUtilTest {

    @Test
    public void should_testName() throws Exception {
        final JFileChooser fileChooser = new JFileChooser();
        final int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.CANCEL_OPTION) {
            return;
        }

        final File file = fileChooser.getSelectedFile();
        final FileReader reader = new FileReader(file);

        System.out.println(LWXMLUtil.parseAttributeValue(reader, new LWXMLPath("/office:document-meta/office:meta/meta:document-statistic"), "meta:table-count"));
    }

}