import java.util.*;

class Cancion {
    String titulo;
    String artista;
    int duracion; 

    public Cancion(String titulo, String artista, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    public String toString() {
        return "cancion " + titulo + " - " + artista + " (" + duracion + "s)";
    }
}

public class Playlist {
    private List<Cancion> canciones = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void agregarCancion(String titulo, String artista, int duracion) {
        canciones.add(new Cancion(titulo, artista, duracion));
        System.out.println("La Canción fue agregada: " + titulo);
    }

    public void eliminar_Cancion(String titulo) {
        boolean encontrada = canciones.removeIf(c -> c.titulo.equalsIgnoreCase(titulo));
        System.out.println(encontrada ? "La Canción ha sido eliminada." : "Esta No ha sido encontrada.");
    }

    public void reproducir_la_Siguiente() {
        if (!canciones.isEmpty()) {
            System.out.println("Reproduciendo: " + canciones.remove(0));
        } else {
            System.out.println("Lo lamento No hay canciones en la playlist.");
        }
    }

    public void ordenar_Por_Duracion() {
        canciones.sort(Comparator.comparingInt(c -> c.duracion));
        System.out.println("Playlist correctamente ordenada por duración.");
    }

    public void ordenar_Por_Artista() {
        canciones.sort(Comparator.comparing(c -> c.artista));
        System.out.println("La Playlist ha sido ordenada por artista.");
    }

    public void mostrar_la_Playlist() {
        if (canciones.isEmpty()) {
            System.out.println("Lo siento la playlist está vacía.");
        } else {
            System.out.println("Playlist actual:");
            canciones.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        int opcion;

        while (true) {
            System.out.println("\n=== 🎧 Playlist ===");
            System.out.println("1. Agregar canción");
            System.out.println("2. Eliminar canción");
            System.out.println("3. Reproducir la siguiente");
            System.out.println("4. Ordenar por duración");
            System.out.println("5. Ordenar por artista");
            System.out.println("6. Mostrar playlist");
            System.out.println("7. Salir");
            System.out.print("Elige la opción que desees: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
                System.out.print("Título: ");
                String titulo = scanner.nextLine();
                System.out.print("Artista: ");
                String artista = scanner.nextLine();
                System.out.print("Duración (segundos): ");
                int duracion = scanner.nextInt();
                playlist.agregarCancion(titulo, artista, duracion);
            } else if (opcion == 2) {
                System.out.print("Título de la canción a eliminar: ");
                String titulo = scanner.nextLine();
                playlist.eliminar_Cancion(titulo);
            } else if (opcion == 3) {
                playlist.reproducir_la_Siguiente();
            } else if (opcion == 4) {
                playlist.ordenar_Por_Duracion();
            } else if (opcion == 5) {
                playlist.ordenar_Por_Artista();
            } else if (opcion == 6) {
                playlist.mostrar_la_Playlist();
            } else if (opcion == 7) {
                System.out.println("Esta reproduccion termino, nos vemos");
                break;
            } else {
                System.out.println("Lo siento, Opción inválida.");
            }
        }
    }
}