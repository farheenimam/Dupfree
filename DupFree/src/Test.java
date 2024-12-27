//import pdf.PDDocument;
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Scanner;
//import java.text.DecimalFormat;
//
//public class Test {
//
//    static Scanner s = new Scanner(System.in);
//    static final String STOPWORDS_FILE = "stopwords.txt";
//
//    public static void main(String[] args) {
//        try {
//            showMenu();
//        } catch (Exception e) {
//            System.out.println("     Error: " + e.getMessage());
//        }
//    }
//
//    static void showMenu() throws Exception {
//        System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
//        System.out.println("|--------------------------------------------- WELCOME TO DUPFREE ---------------------------------------------------|");
//        System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
//        System.out.println();
//        System.out.println("                   Our Dupfree tool checks the plagiarism from the give document and also       \n" +
//                "                   analysis the text to tell whether it is formulatic text (AI generated)\n" +
//                "                   or human written text.\n" +
//                "                   Feel free to verify your pdf document, txt file or manually inputted text\n" +
//                "                         ---WARNING: Only upload files with .txt or .pdf extensions!--");
//        System.out.println();
//        opt();
//    }
//    static void opt() throws Exception {
//        System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
//        System.out.println("        Choose any one of the options by entering the specified index number");
//        System.out.println("     1. Input text manually");
//        System.out.println("     2. Input TXT document to check for plagiarism");
//        System.out.println("     3. Input PDF document to check for plagiarism");
//        System.out.println("     4. Exit");
//        System.out.print("     Enter your choice: ");
//        int choice = s.nextInt();
//        choice(choice);
//    }
//    static void choice(int choice) throws Exception{
//        boolean input = true;
//            switch (choice) {
//                case 1:
//                    processTextInput();
//                    break;
//                case 2:
//                    processFileInput();
//                    break;
//                case 3:
//                    processPdfInput();
//                    break;
//                case 4:
//                    System.out.println("     ~~~~~~~~~ Thank you for using DupFree! ~~~~~~~~~");
//                    input = false;
//                    break;
//                default:
//                    System.out.println("     Invalid choice!. Please try again.");
//            }
//    }
//    static void exit() throws Exception{
//        System.out.print("\n     Do you want to continue (yes or no): ");
//        String ans = s.next();
//        ans = ans.toLowerCase().trim();
//        if (ans.equals("yes") || ans.equals("y"))
//            opt();
//        else if (ans.equals("no") || ans.equals("n")){
//            choice(4);
//        }
//        else {
//            System.out.println("     Invalid Input!");
//            exit();
//        }
//    }
//
//    static void processTextInput() throws Exception {
//        System.out.println("\n        ------------- PROCESSING TEXT ----------------");
//        System.out.print("     Enter the text: ");
//        s.nextLine();
//        String text = s.nextLine();
//        // Returns filtered text
//        text = cleanText(text);
//        processTextPlag(text, 2);
//    }
//
//    static void processFileInput() throws Exception {
//        System.out.println("\n        ------------- PROCESSING TXT FILE ----------------");
//        System.out.print("     Enter the file name (without .txt): ");
//        String fileName = s.next();
//        String filePath = fileName + ".txt";
//
//        if (!Files.exists(Paths.get(filePath))) {
//            System.out.println("     File not found: " + filePath);
//            System.out.print("     Do you want to try another file? (y/n): ");
//            char choice = s.next().charAt(0);
//            if (choice == 'y' || choice == 'Y') {
//                processFileInput();
//            } else {
//                System.out.println("     No plagiarism detected, exiting.");
//                return;
//            }
//        } else {
//            String content = readFile(filePath);
//            processTextPlag(content, 2);
//        }
//    }
//
//    static void processPdfInput() throws Exception {
//        System.out.println("\n     ------------- PROCESSING PDF ----------------");
//        System.out.print("     Enter the PDF file path: ");
//        s.nextLine();
//        String pdfPath = s.nextLine();
//
//        if (!Files.exists(Paths.get(pdfPath))) {
//            System.out.println("     File not found: " + pdfPath);
//            return;
//        }
//
//        String pdfContent = readPdf(pdfPath);
//        processTextPlag(pdfContent, 3);
//    }
//    static String readPdf(String filePath) throws Exception {
//        PDDocument document = null;
//        try {
//            document = PDDocument.load(new File(filePath));
//            StringBuilder content = new StringBuilder();
//
//            // Mock implementation: you can use a real PDF-to-text extractor library like PDFBox
//            content.append("     Mocked content from ").append(filePath);
//
//            return cleanText(content.toString());
//        } finally {
//            if (document != null) {
//                document.close();
//            }
//        }
//    }
//    static void processTextPlag(String userContent, int opt) throws Exception {
//        String filePath= "", content = "";
//        if (opt == 2) {
//            System.out.print("     Number of TXT files to compare: ");
//        }
//        if (opt == 3) {
//            System.out.print("     Number of PDF files to compare: ");
//        }
//        int n = s.nextInt();
//        s.nextLine();
//
//        // To generate report
//        StringBuilder report = new StringBuilder("     ~~~~~~~~~~~~~~~~~~~~ PLAGIARISM REPORT ~~~~~~~~~~~~~~~~~\n");
//        report.append("     ---------------------------------------------------\n");
//        report.append(String.format("     %-20s | %-15s | %-15s\n", "File", "Similarity", "Severity"));
//        report.append("     ---------------------------------------------------\n");
//
//        int no_Files_Compared = 0;
//        int no_plag_files = 0;
//        double ttl_Similarity = 0;
//        boolean file_not_found = false;
//
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            if (opt == 2) {
//                System.out.print("     Enter file name " + (i + 1) + " (without .txt): ");
//                String fileName = s.next();
//                filePath = fileName + ".txt";
//                content = readFile(filePath);
//            }
//            if (opt == 3){
//                System.out.print("     Enter PDF file path " + (i + 1) + ": ");
//                filePath = s.nextLine();
//                content = readPdf(filePath);
//            }
//            if (!Files.exists(Paths.get(filePath))) {
//                System.out.println("     File not found: " + filePath);
//            file_not_found = true;
//            continue;
//        }
//
//        double similarity = calJaccard(userContent, content) * 100;
//            String severity = plagLvl(similarity);
//            if((int)similarity == 0 || (int)similarity < 0 ){
//                report.append(String.format("     %-20s | %-15s | %-15s\n", filePath, "None", severity));
//            }
//            else
//                report.append(String.format("     %-20s | %-15.2f | %-15s\n", filePath, similarity, severity));
//
//            no_Files_Compared++;
//            if ((int)similarity > 0) {
//                no_plag_files++;
//                ttl_Similarity += similarity;
//            }
//
//        }
//
//
//        if (file_not_found) {
//            System.out.println("     No plagiarism detected due to missing files.");
//        } else {
//            report.append("     ---------------------------------------------------\n");
//            report.append(String.format("     Total Files Compared: %d\n", no_Files_Compared));
//            report.append(String.format("     Total Plagiarized Files: %d\n", no_plag_files));
//            if (no_plag_files > 0) {
//                report.append(String.format("     Average Similarity: %.2f%%\n", ttl_Similarity / no_plag_files));
//            }
//
//            System.out.println();
//            // To analysis the text and give output based on the text analysis
//            double ttr = textanalysis.calcTTR(userContent);
//            double avgSentLen = textanalysis.calAvgSentLength(userContent);
//            double readability = textanalysis.calReadability(userContent);
//            double aiScore = textanalysis.calAIPlag(ttr, avgSentLen, readability);
//
//            // To generate the output of the table according to the scores of eac field
//            String sentOutput = textanalysis.statementSent(avgSentLen);
//            String readabilityOutput = textanalysis.statementReading(readability);
//            String aiOutput = textanalysis.statementAI(aiScore);
//
//            // Call text analysis functions
//            DecimalFormat df = new DecimalFormat("#.##");
//            report.append("\n");
//            report.append("     --------------------------------------------------------------------------\n");
//            report.append("               Text Analysis Results\n");
//            report.append("     --------------------------------------------------------------------------\n");
//            report.append(String.format("     Sentence Length (Avg):   |    %s (%s)\n", df.format(avgSentLen),sentOutput));
//            report.append(String.format("     Reading Ease (Flesch):   |    %s (%s)\n", df.format(readability),readabilityOutput));
//            report.append(String.format("     AI Likelihood Score:     |    %s (%s)\n", df.format(aiScore),aiOutput));
//            report.append("     --------------------------------------------------------------------------\n");
//
//            saveReport(report.toString());
//            System.out.println(report);
//            exit();
//        }
//    }
//
//    // This function cleans the text
//    static String cleanText(String text) throws Exception {
//        // This converts all char to lowercase
//        text = text.toLowerCase().trim(); // Trim removes the leading and ending spaces
//        // This removes the whitespaces, tabs, newlines, and multiple space
//        text = text.replaceAll("\\s+", " ");
//        // Using '//s+' that matches with any whitespace character if found split the text e.g: "hello\t\tworld" -> hello world
//        String[] words = text.split("\\s+");
//        CustomHashSet stopWords = stopWords();
//        StringBuilder cleanTxt = new StringBuilder();
//        for (String word : words) {
//            if (!stopWords.contains(word)) {
//                cleanTxt.append(word).append(" ");
//            }
//        }
//
//        return cleanTxt.toString().trim();
//    }
//
//    // Function to load the stop words
//    static CustomHashSet stopWords() throws IOException {
//        CustomHashSet stopWords = new CustomHashSet(100);
//        if (!Files.exists(Paths.get(STOPWORDS_FILE))) {
//            System.out.println("     Stopwords file not found. Using default stopwords.");
//            return stopWords;
//        }
//
//        for (String line : Files.readAllLines(Paths.get(STOPWORDS_FILE))) {
//            stopWords.add(line.trim().toLowerCase());
//        }
//
//        return stopWords;
//    }
//
//    static String readFile(String filePath) throws Exception {
//        String content = Files.readString(Paths.get(filePath));
//        return cleanText(content);
//    }
//
//    // This function calculates the Jaccard
//    static double calJaccard(String userText, String compareText) {
//        int shingleSize = Math.max(3, Math.min(6, userText.length() / 5));
//        CustomHashSet userShingles = genShingles(userText, shingleSize);
//        CustomHashSet compareShingles = genShingles(compareText, shingleSize);
//
//        CustomHashSet intersection = userShingles.intersection(compareShingles);
//        CustomHashSet union = userShingles.union(compareShingles);
//
//        return (double) intersection.size() / union.size();
//    }
//    // Functions to generate shingles
//    static CustomHashSet genShingles(String text, int size) {
//        CustomHashSet shingles = new CustomHashSet(100);
//        for (int i = 0; i <= text.length() - size; i++) {
//            String shingle = text.substring(i, i + size);
//            shingles.add(shingle);
//        }
//        return shingles;
//    }
//    // To  tell the severity of the plagiarism based on the scores
//    static String plagLvl(double similarity) {
//        if (similarity >= 75) return "High";
//        if (similarity >= 50) return "Moderate";
//        return "Low";
//    }
//    // To save the report in the TXT extension file
//    static void saveReport(String reportContent) {
//        // Change the timestamp format to remove the underscore
//        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String fileName = "plagiarism_report_" + timestamp + ".txt";
//
//        try (FileWriter writer = new FileWriter(fileName)) {
//            writer.write(reportContent);
//            System.out.println("\n     Plagiarism report saved as '" + fileName + "'.");
//        } catch (IOException e) {
//            System.out.println("     Error saving report: " + e.getMessage());
//        }
//    }
//
//}