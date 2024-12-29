import java.io.*;
import java.util.*;
//creamos el objeto juego
public class Game {
    private String movieTitle;
    private StringBuilder hiddenTitle;
    private Set<Character> guessedLetters;
    private Set<Character> icorrectLetters;
    private int attempts;
    private int score;
    //usamos StringBuilder, para poder modificar el contenido del mismo objeto
    public Game() {
        this.attempts = 10;
        this.score = 0;
        this.guessedLetters = new HashSet<>();
        this.icorrectLetters = new HashSet<>();
        this.hiddenTitle = new StringBuilder();
        this.movieTitle = loadRandomMovie();
        initializeHiddenTitle();
    //Usamos HashSet para evitar duplicados y para que los elementos no tengan un orden especifico
    }

    //Cargar una pelicula aleatoria desde el archivo binario
    private String loadRandomMovie() {
        //Si el archivo movies.dat no existe creara uno
        File movieFile = new File("movies.dat");
        if (!movieFile.exists()) {
            createMoviesFile();
        }
//Usaremos ois para abrebiar ObjectInputStream y para que no nos de fallos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("movies.dat"))) {
            String[] movies = (String[]) ois.readObject();
            Random rand = new Random();
            return movies[rand.nextInt(movies.length)];
        } catch (Exception e) {
            System.out.println("No se pudo cargar las peliculas, verifique si existe el archivo"+ movies.dat);
            return "";
        }
    }
//creamos el archivo movies.dat con una lista de peliculas




}
