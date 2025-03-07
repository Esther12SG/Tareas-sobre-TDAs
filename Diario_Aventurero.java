import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//clase sonde estara el registro de las misisones creadas
public class Diario_Aventurero {
    private List<String> misiones = new ArrayList<>();
    private static final String ARCHIVO = "misiones.txt";

    public Diario_Aventurero() {
        cargarMisiones();
    }
//contructor que permite que se ingrese una nueva lista y esta se agregue
    public void registro_de_Mision(String mision) {
        misiones.add(mision);
        System.out.println("Misión agregada: " + mision);
        guardarMisiones();
    }
//Metodo para poder eliminar alguna de las misiones ya registradas
    public void eliminar_la_Ultima_Mision() {
        if (!misiones.isEmpty()) {
            System.out.println("Eliminando: " + misiones.remove(misiones.size() - 1));
            guardarMisiones();
        } else {
            System.out.println("No hay misiones.");
        }
    }
//Metodo que mostrara todas las misiones agregadas
    public void mostrar_las_Misiones() {
        if (misiones.isEmpty()) {
            System.out.println("No hay misiones.");
        } else {
            System.out.println("Misiones completadas:");
            for (String m : misiones) {
                System.out.println("- " + m);
            }
        }
    }
//Metodo que permite buscar la mision que el usuario necesite
    public boolean buscarMision(String mision) {
        return misiones.contains(mision);
    }
//Metodo para poder guardar las misiones en un archivo de texto 
    private void guardarMisiones() {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (String m : misiones) escritor.println(m);
        } catch (IOException e) {
            System.out.println("No se pudo guardar.");
        }
    }
//Metodo que permite cargar esas msisiones en el archivo de texto
    private void cargarMisiones() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = lector.readLine()) != null) misiones.add(linea);
            } catch (IOException e) {
                System.out.println("No se pudo cargar.");
            }
        }
    }
//Este es el metodo principal que hace al programa interactivo
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Diario_Aventurero diario = new Diario_Aventurero();
        int opcion;
//Menu
            while (true) {
            System.out.println("\n=== Diario Aventurero ===");
            System.out.println("1. Agregar tu misión");
            System.out.println("2. Eliminar la última misión registrada");
            System.out.println("3. Ver las misiones registradas");
            System.out.println("4. Buscar la misión que desee");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
                System.out.print("Nombre de la misión: ");
                diario.registro_de_Mision(scanner.nextLine());
            } else if (opcion == 2) {
                diario.eliminar_la_Ultima_Mision();
            } else if (opcion == 3) {
                diario.mostrar_las_Misiones();
            } else if (opcion == 4) {
                System.out.print("Misión a buscar: ");
                String nombre = scanner.nextLine();
                System.out.println(diario.buscarMision(nombre) ? "Misión encontrada." : " Esta no a sido completada.");
            } else if (opcion == 5) {
                System.out.println("Nos vemos");
                break;
            } else {
                System.out.println("Opción inválida.");
            }
        }

        scanner.close();//cerramos el scanner
    }
}