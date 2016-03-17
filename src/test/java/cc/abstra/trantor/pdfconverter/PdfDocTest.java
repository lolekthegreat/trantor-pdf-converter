package cc.abstra.trantor.pdfconverter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class PdfDocTest {

    static List<File> conformingPdfFiles;
    static List<File> nonConformingPdfFiles;

    @Before
    public void setUp() throws Exception {
        //TODO: resource files should be downloaded (mvn wagon plugin) from
        // http://www.pdfa.org/2011/08/download-isartor-test-suite/ and
        // http://www.pdflib.com/knowledge-base/pdfa/validation-report/
        // b/c the their license does not allow redistribution
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        URL conformingUrl = this.getClass().getResource("/pdf-files/PDFA-1b/conforming");
        URL nonConformingUrl = this.getClass().getResource("/pdf-files/PDFA-1b/nonconforming");

        conformingPdfFiles = Arrays.asList(new File(conformingUrl.toURI()).listFiles());
        nonConformingPdfFiles = Arrays.asList(new File(nonConformingUrl.toURI()).listFiles());
    }

    @Test
    public void testPDFA1bCompliance() throws Exception {

        for (File f : nonConformingPdfFiles) {
            Assert.assertEquals("Nonconforming file Name: " + f.getAbsolutePath(), false, PdfDoc.isPDFA1bCompliant(f.getAbsolutePath()));
        }

        for (File f : conformingPdfFiles) {
            Assert.assertEquals("Conforming file Name: " + f.getAbsolutePath(), true, PdfDoc.isPDFA1bCompliant(f.getAbsolutePath()));
        }

    }
}
