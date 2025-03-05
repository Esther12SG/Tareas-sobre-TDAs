import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Reservacion {
    String nombre;
    int asiento;

    public Reservacion(String nombre, int asiento) {
        this.nombre = nombre;
        this.asiento = asiento;
    }

    public String toString() {
        return "Cliente: " + nombre + " - Asiento #" + asiento;
    }
}

public class Gestor_Boletos {
    private Queue<Reservacion> reservaciones = new LinkedList<>();
    private int totalAsientos;
    private static Scanner scanner = new Scanner(System.in);

    public Gestor_Boletos(int totalAsientos) {
        this.totalAsientos = totalAsientos;
    }

    public void registrar_la_Reservacion(String nombre, Integer asiento) {
        if (asiento == null) {
            asiento = asignarMejorAsiento();
            if (asiento == -1) {
                System.out.println("Lo siento, no hay asientos disponibles.");
                return;
            }
        } else if (verificar_Asiento(asiento)) {
            System.out.println("Lo siento, el asiento #" + asiento + " ya está ocupado.");
            return;
        }
        reservaciones.add(new Reservacion(nombre, asiento));
        System.out.println("La reservación ha sido correctamente agregada: " + nombre + " - Asiento #" + asiento);
    }

    public void cancelación_Reservacion(int asiento) {
        boolean encontrada = reservaciones.removeIf(r -> r.asiento == asiento);
        System.out.println(encontrada ? "La reservación ha sido correctamente cancelada." : "No se encontró el asiento #" + asiento);
    }

    public boolean verificar_Asiento(int asiento) {
        for (Reservacion r : reservaciones) {
            if (r.asiento == asiento) return true;
        }
        return false;
    }

    private int asignarMejorAsiento() {
        for (int i = 1; i <= totalAsientos; i++) {
            if (!verificar_Asiento(i)) {
                return i;
            }
        }
        return -1; // No hay asientos disponibles
    }

    public void mostrar_Reservaciones() {
        if (reservaciones.isEmpty()) {
            System.out.println("Lo siento, no hay reservaciones.");
        } else {
            System.out.println("Lista de reservaciones en el debido orden de llegada:");
            for (Reservacion r : reservaciones) {
                System.out.println(r);
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Ingrese el número total de asientos disponibles: ");
        int totalAsientos = scanner.nextInt();
        Gestor_Boletos gestor = new Gestor_Boletos(totalAsientos);

        int opcion;
        while (true) {
            System.out.println("\n=== Gestor de Reservaciones ===");
            System.out.println("1. Registrar tu reservación (elegir asiento)");
            System.out.println("2. Registrar tu reservación (asignación automática)");
            System.out.println("3. Cancelar reservación");
            System.out.println("4. Verificar tu asiento");
            System.out.println("5. Mostrar las reservaciones");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            if (opcion == 1) {
                System.out.print("Nombre del cliente: ");
                String nombre = scanner.nextLine();
                System.out.print("Su número de asiento: ");
                int asiento = scanner.nextInt();
                gestor.registrar_la_Reservacion(nombre, asiento);
            } else if (opcion == 2) {
                System.out.print("Nombre del cliente: ");
                String nombre = scanner.nextLine();
                gestor.registrar_la_Reservacion(nombre, null);
            } else if (opcion == 3) {
                System.out.print("Número de asiento que desea cancelar: ");
                int asiento = scanner.nextInt();
                gestor.cancelación_Reservacion(asiento);
            } else if (opcion == 4) {
                System.out.print("Número de asiento que desea verificar: ");
                int asiento = scanner.nextInt();
                System.out.println(gestor.verificar_Asiento(asiento) ? "Ocupado." : "Disponible.");
            } else if (opcion == 5) {
                gestor.mostrar_Reservaciones();
            } else if (opcion == 6) {
                System.out.println("Muchas gracias por elegir nuestro sistema de reservaciones.");
                break;
            } else {
                System.out.println("Lo siento, opción inválida.");
            }
        }
    }
}