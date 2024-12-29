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
            System.out.println("No se pudo cargar las peliculas, verifique si existe el archivo");
            return "";
        }
    }
//creamos el archivo movies.dat con una lista de peliculas
    private  void   createMoviesFile() {
        String[] movies = {
                "Star wars",
                "Señor de los anillos",
                "El Padrino",
                "Pulp Fiction",
                "Gladiator",
                "Avatar",
                "Pesadilla antes de navidad",
                "Harry Potter"
        };

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("movies.dat"))) {
            oos.writeObject(movies);
            System.out.println("Se ha creado correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear el archivo");
        }
    }




}
