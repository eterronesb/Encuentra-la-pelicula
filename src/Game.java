import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
//creamos el objeto juego
public class Game {
    private String movieTitle;
    private StringBuilder hiddenTitle;
    private Set<Character> guessedLetters;
    private Set<Character> incorrectLetters;
    private int attempts;
    private int score;
    //usamos StringBuilder, para poder modificar el contenido del mismo objeto
    public Game() {
        this.attempts = 10;
        this.score = 0;
        this.guessedLetters = new HashSet<>();
        this.incorrectLetters = new HashSet<>();
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
            return movies[ThreadLocalRandom.current().nextInt(movies.length)];
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
    //Iniciaremos el titulo oculto con * para las letras
    private void    initializeHiddenTitle() {
        for (int i = 0; i < movieTitle.length(); i++) {
            char c = movieTitle.charAt(i);
            if (Character.isLetter(c)) {
                hiddenTitle.append('*');
            } else {
                hiddenTitle.append(c);
            }
        }
    }

    //mostrar el estado actual del titulo
    public void displayHiddenTitle() {
        System.out.println("Película: " + hiddenTitle);
        System.out.println("Letras incorrectas: " + incorrectLetters);
        System.out.println("Intentos restantes: " + attempts);
        System.out.println("Puntuación: " + score);
    }
    //adivina una letra
    public void guessLetter(char letter) {
        if (guessedLetters.contains(letter)) {
            System.out.println("Ya has adivinado esa letra.");
            return;
        }
        guessedLetters.add(letter);

        if (movieTitle.toLowerCase().indexOf(Character.toLowerCase(letter)) != -1) {
            updateHiddenTitle(letter);
            score += 10;
            System.out.println("Has acertado.");
        } else {
            incorrectLetters.add(letter);
            score -= 10;
            System.out.println("Letra incorrecta.");
        }

        attempts--;
    }
    //Adivinar el titulo completo
    public void guessMovieTitle(String title){
        if (title.equalsIgnoreCase(movieTitle)) {
            score += 20;
            System.out.println("Lo conseguiste, el titulo es: " + movieTitle);
        }else {
            score -= 20;
            System.out.println("Perdiste, el titulo era: " + movieTitle);
        }
        attempts = 0;
    }
    //Actualizar el titulo oculto con las letras correctas
    private void updateHiddenTitle(char letter) {
        for (int i = 0; i < movieTitle.length(); i++) {
            if (Character.toLowerCase(movieTitle.charAt(i)) == Character.toLowerCase(letter)) {
                hiddenTitle.setCharAt(i, movieTitle.charAt(i));
            }
        }
    }
    //Comprobamos si el juego a terminado
    public boolean isGameOver() {
        return attempts <= 0 || hiddenTitle.toString().equals(movieTitle);
    }
    //Obtenemos el puntuaje final
    public int getScore() {
        return score;
    }
}
