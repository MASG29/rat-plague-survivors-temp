package ratplaguesurvivors.output;

import java.io.*;

public class ScoreWriter {

    private final File file;

    public ScoreWriter() {
        file = new File("scoreboard/highscore.txt");
    }

    public void write(String name, int score, int kills, int xp) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                writer.write(name + "-" + score + "-" + kills + "-" + xp);
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to write highscore file: " + e.getMessage());
        }
    }
}
