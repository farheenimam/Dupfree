package pdf;

import java.io.File;
import java.io.IOException;


public class PDDocument {
    private File file; // To hold the reference to the PDF file

    // Load method simulating PDF loading
    public static PDDocument load(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("     File not found: " + file.getAbsolutePath());
        }
        PDDocument document = new PDDocument();
        document.file = file;
        return document;
    }

    // Close method simulating closing the PDF
    public void close() {
        if (this.file != null) {
            this.file = null; // Clear the reference
        } else {
            System.out.println("     No PDF file is open.");
        }
    }

}
