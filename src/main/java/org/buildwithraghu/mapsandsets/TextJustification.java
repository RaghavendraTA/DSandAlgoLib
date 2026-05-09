package org.buildwithraghu.mapsandsets;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        int i = 0;
        List<String> ans = new ArrayList<>();
        while(i < words.length) {
            List<String> currentLine = new ArrayList<>();
            int lineChars = 0;
            while (i < words.length && lineChars + words[i].length() + currentLine.size() <= maxWidth) {
                lineChars += words[i].length();
                currentLine.add(words[i]);
                i++;
            }
            boolean isLastLine = (i == words.length);
            ans.add(processLine(lineChars, maxWidth, isLastLine, currentLine));
        }
        return ans;
    }

    private String processLine(int lineChars, int maxWidth, boolean isLastLine, List<String> currentLine) {
        StringBuilder sb = new StringBuilder();
        int numWords = currentLine.size();

        // Case 1: Last line or only one word in the line (Left Justified)
        if (isLastLine || numWords == 1) {
            for (int j = 0; j < numWords; j++) {
                sb.append(currentLine.get(j));
                if (j < numWords - 1) sb.append(" ");
            }
            // Fill remaining width with spaces
            while (sb.length() < maxWidth) sb.append(" ");
        }
        // Case 2: Fully justified
        else {
            int totalSpaces = maxWidth - lineChars;
            int gaps = numWords - 1;
            int spacesPerGap = totalSpaces / gaps;
            int extraSpaces = totalSpaces % gaps;

            for (int j = 0; j < numWords; j++) {
                sb.append(currentLine.get(j));
                if (j < gaps) {
                    // Add base spaces
                    for (int s = 0; s < spacesPerGap; s++) sb.append(" ");
                    // Distribute extra spaces from left to right
                    if (j < extraSpaces) sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        TextJustification tj = new TextJustification();
        System.out.println(tj.fullJustify(words, 16));
    }
}
