package aplinyjavaapp;

/**
 * ASCIIFlowDiagram
 *
 * A revamped flow diagram generator with a Pliny twist!
 */
public class ASCIIFlowDiagram {

    public static String generateFlow(String sequence) {
        String[] parts = sequence.split("->");
        StringBuilder sb = new StringBuilder();

        sb.append("~*~ Pliny's Path of Enlightenment ~*~\n");
        for (int i = 0; i < parts.length; i++) {
            String step = parts[i].trim();
            sb.append("[").append(step).append("]");
            if (i < parts.length - 1) {
                sb.append(" ==> ");
            }
        }
        sb.append("\n");
        sb.append("~*~ End of the Journey ~*~\n");
        return sb.toString();
    }

    public static String generateFancyFlow(String sequence) {
        String base = generateFlow(sequence);
        String fancy = """
            .-^-.
           /     \\   In the spirit of Naturalis Historia
          |  O O  |  Witness the cascade of wisdom
          |   U   |  As nature reveals its timeless secrets
           \\     /
            `-.-'

            """;
        return fancy + base;
    }
}