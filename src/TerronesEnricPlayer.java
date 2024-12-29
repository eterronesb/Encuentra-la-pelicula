import java.io.*;
import java.util.*;

public class TerronesEnricPlayer {
    private String nickname;
    private int score;

    public TerronesEnricPlayer(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }
    public int getScore() {
        return score;
    }

    public void saveRanking() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ranking.dat"))) {
            List<TerronesEnricPlayer> ranking = ((List<TerronesEnricPlayer>) ois.readObject());
            ranking.add(this);
            ranking.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        //Mantendremos solo los 5 mejores
            if (ranking.size() > 5) {
                ranking = ranking.subList(0, 5);
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ranking.dat"))) {
                oos.writeObject(ranking);
            }
        }catch (Exception e) {
            System.out.println("Error al guardar el ranking.");
        }
    }
}
