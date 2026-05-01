package ratplaguesurvivors.input;

import java.io.*;

public class ScoreLoader {

    private String name;
    private int score;
    private int xp;
    private int kills;

    public ScoreLoader() {

        File file = new File("scoreboard/highscore.txt");

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create highscore file: " + e.getMessage());
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && line.contains("-")) {
                String[] parts = line.split("-", 4);
                if(parts.length < 4){
                    file.delete();
                    reader.close();
                    return;
                }
                name = parts[0];
                score = Integer.parseInt(parts[1]);
                kills = Integer.parseInt(parts[2]);
                xp = Integer.parseInt(parts[3]);
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to read highscore file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Malformed score value in highscore file: " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getXp() {
        return xp;
    }

    public int getKills() {
        return kills;
    }
    
}
