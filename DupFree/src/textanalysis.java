import java.text.BreakIterator;
import java.util.Locale;

public class textanalysis {

    public static double calcTTR(String text) {
        String[] words = text.split("\\s+");
        CustomHashSet uniqueWords = new CustomHashSet(1000); // Use CustomHashSet instead of HashSet

        for (String word : words) {
            word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Remove punctuation
            if (!word.isEmpty()) {
                uniqueWords.add(word);
            }
        }

        return (double) uniqueWords.size() / words.length;
    }
    // Calculates the avg length of the sentence
    public static double calAvgSentLength(String text) {
        BreakIterator sentenceItr = BreakIterator.getSentenceInstance(Locale.US);
        sentenceItr.setText(text);

        int sentCount = 0;
        int wordCount = text.split("\\s+").length;

        for (int end = sentenceItr.next(); end != BreakIterator.DONE; end = sentenceItr.next()) {
            sentCount++;
        }

        return (double) wordCount / sentCount;
    }
    // This calculates the readability of the text
    public static double calReadability(String text) {
        BreakIterator sentItr = BreakIterator.getSentenceInstance(Locale.US);
        sentItr.setText(text);

        int sentCount = 0;
        for (int end = sentItr.next(); end != BreakIterator.DONE; end = sentItr.next()) {
            sentCount++;
        }

        int wordCount = text.split("\\s+").length;
        int syllableCount = cntSyllables(text);

        double score = 206.835 - (1.015 * ((double) wordCount / sentCount)) - (84.6 * ((double) syllableCount / wordCount));
        return score;
    }
    // This counts the syllables
    private static int cntSyllables(String text) {
        int syllables = 0;
        String[] words = text.split("\\s+");
        for (String word : words) {
            syllables += cntSyllablesWord(word);
        }
        return syllables;
    }
    // This counts the syllables in words
    private static int cntSyllablesWord(String word) {
        int count = 0;
        boolean isVowel = false;
        word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");

        for (int i = 0; i < word.length(); i++) {
            if ("aeiouy".indexOf(word.charAt(i)) >= 0) {
                if (!isVowel) {
                    count++;
                    isVowel = true;
                }
            } else {
                isVowel = false;
            }
        }

        if (word.endsWith("e") && count > 1) {
            count--;
        }
        return Math.max(count, 1);
    }
    // This calculates the score of the AI Likelihood based on the 3 metrices
    public static double calAIPlag(double ttr, double avgSentLength, double readability) {
        double ttrLimit = 0.6;
        double sentLimit = 15.0;
        double readLimit = 60.0;

        double ttrScore;
        if (ttr < ttrLimit) {
            ttrScore = 0.5;
        } else {
            ttrScore = 1.0;
        }

        double sentScore;
        if (avgSentLength > sentLimit) {
            sentScore = 0.6;
        } else {
            sentScore = 0.4;
        }

        double readScore;
        if (readability < readLimit) {
            readScore = 0.6;
        } else {
            readScore = 0.4;
        }

        double ttrWeight = 0.3;
        double sentWeight = 0.3;
        double readWeight = 0.4;

        double aiScore = (ttrScore * ttrWeight) + (sentScore * sentWeight) + (readScore * readWeight);

        return aiScore;
    }
    // This determines the statement based on the score of the length of the sentence
    public static String statementSent(double avgLen) {
        if (avgLen < 15) {
            return "Short sentences - Easy to read.";
        } else {
            return "Long sentences - Complex text.";
        }
    }
    // This determines readability based on the score
    public static String statementReading(double score) {
        if (score > 70) {
            return "Very easy to read.";
        } else if (score > 50) {
            return "Moderately readable.";
        } else {
            return "Difficult to read.";
        }
    }
    // This determines AI plagiarism based on the score
    public static String statementAI(double score) {
        if (score > 0.7) {
            return "Likely AI-generated.";
        } else if (score > 0.6) {
            return "Uncertain - Could be AI or human.";
        } else {
            return "Likely human-written.";
        }
    }
}
