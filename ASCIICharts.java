package aplinyjavaapp;

import java.util.Random;

/**
 * ASCIICharts
 *
 * Utilities for generating ASCII charts—with a Pliny twist!
 */
public class ASCIICharts {

    /**
     * Returns a simple ASCII bar chart given a label and percentage.
     * e.g., 40% -> "Item [####      ] (40%)"
     */
    public static String barChart(String label, int percentage) {
        int totalBars = 10;
        int filled = (int) Math.round((percentage / 100.0) * totalBars);
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(" [");
        for (int i = 0; i < totalBars; i++) {
            sb.append(i < filled ? "#" : " ");
        }
        sb.append("] (").append(percentage).append("%)\n");
        return sb.toString();
    }

    /**
     * NEW & FUN multi-bar chart with extra flair.
     */
    public static String generateMultiBarChart() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("=== PLINY'S REVAMPED MULTI-BAR CHART ===\n");
        sb.append("Behold the new wave of fun & data synergy!\n\n");

        // Generate 5 random bars
        for (int i = 1; i <= 5; i++) {
            int pct = rand.nextInt(101);
            sb.append(barChart("Item " + i, pct));
            // Just a silly flourish line after each item
            sb.append("   ~ Spice up the data ~\n");
        }

        sb.append("\nPliny says: \"Numbers are the spice of digital life!\"\n");
        return sb.toString();
    }

    /**
     * Returns a simple ASCII pie chart representation given a label and percentage.
     */
    public static String pieChart(String label, int percentage) {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(" (Pie) : ").append(percentage).append("%\n");
        sb.append("Imagine a circle, segmented by the light of wisdom...\n");
        return sb.toString();
    }

    /**
     * Advanced bar chart with Pliny flair.
     */
    public static String advancedBarChart(String label, int percentage) {
        int totalBars = 20;
        int filled = (int) Math.round((percentage / 100.0) * totalBars);
        StringBuilder sb = new StringBuilder();
        sb.append("Pliny's ").append(label).append(" Chart:\n");
        sb.append("+");
        for (int i = 0; i < totalBars; i++) {
            sb.append("-");
        }
        sb.append("+\n|");
        for (int i = 0; i < filled; i++) {
            sb.append("#");
        }
        for (int i = filled; i < totalBars; i++) {
            sb.append(" ");
        }
        sb.append("|\n+");
        for (int i = 0; i < totalBars; i++) {
            sb.append("-");
        }
        sb.append("+\n(").append(percentage).append("%)\n");
        sb.append("In Pliny's words: 'Veritas in Numeris' (Truth in Numbers)\n");
        return sb.toString();
    }

    /**
     * Vertical bar chart (ASCII columns).
     */
    public static String verticalBarChart(String label, int percentage) {
        int totalRows = 10;
        int filledRows = (int) Math.round((percentage / 100.0) * totalRows);
        StringBuilder sb = new StringBuilder();
        sb.append("Pliny's Vertical Insight - ").append(label).append("\n");
        for (int i = totalRows; i > 0; i--) {
            if (i <= filledRows) {
                sb.append("   | ### |\n");
            } else {
                sb.append("   |     |\n");
            }
        }
        sb.append("   +-----+\n");
        sb.append("   (").append(percentage).append("%)\n");
        sb.append("May these vertical bars elevate your understanding.\n");
        return sb.toString();
    }

    /**
     * Stacked bar chart that accepts arrays.
     */
    public static String stackedBarChart(String[] labels, int[] percentages) {
        if (labels.length != percentages.length) {
            return "Error: Labels and percentages count mismatch.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Pliny's Stacked Wisdom Chart:\n");
        for (int i = 0; i < labels.length; i++) {
            sb.append(barChart(labels[i], percentages[i]));
        }
        sb.append("Each stack reveals a fragment of the greater truth.\n");
        return sb.toString();
    }

    /**
     * Comprehensive summary chart combining multiple styles.
     */
    public static String generatePlinySummaryChart() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================\n");
        sb.append("Welcome to Pliny's Hall of Charts\n");
        sb.append("====================================\n\n");
        sb.append(advancedBarChart("Liberation", 75)).append("\n");
        sb.append(pieChart("Enlightenment", 40)).append("\n");
        sb.append(verticalBarChart("Revelation", 55)).append("\n");
        sb.append("====================================\n");
        sb.append("Let data be thy guide, as per Pliny's eternal wisdom.\n");
        return sb.toString();
    }
}