import javax.swing.JOptionPane;
import java.util.Arrays;

public class Biblioteca {
    private static final int MAX_LIBROS = 100;  // Máximo número de libros
    private static final int ATRIBUTOS = 5;     // Número de atributos por libro
    private static String[][] libros = new String[MAX_LIBROS][ATRIBUTOS];
    private static int contadorLibros = 0;

    public static void main(String[] args) {
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1 -> registrarLibro();
                case 2 -> actualizarLibro();
                case 3 -> eliminarLibro();
                case 4 -> buscarLibro();
                case 5 -> ordenarLibros();
                case 6 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                default -> JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private static int mostrarMenu() {
        String menu = """
                Seleccione una opción:
                1. Registrar nuevo libro
                2. Actualizar libro
                3. Eliminar libro
                4. Buscar libro
                5. Ordenar libros
                6. Salir
                """;
        return Integer.parseInt(JOptionPane.showInputDialog(menu));
    }

    private static void registrarLibro() {
        if (contadorLibros >= MAX_LIBROS) {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más libros.");
            return;
        }
        String codigo = JOptionPane.showInputDialog("Ingrese el código del libro:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del libro:");
        String autor = JOptionPane.showInputDialog("Ingrese el autor del libro:");
        String materia = JOptionPane.showInputDialog("Ingrese la materia del libro:");
        String numPaginas = JOptionPane.showInputDialog("Ingrese el número de páginas del libro:");

        libros[contadorLibros][0] = codigo;
        libros[contadorLibros][1] = nombre;
        libros[contadorLibros][2] = autor;
        libros[contadorLibros][3] = materia;
        libros[contadorLibros][4] = numPaginas;
        contadorLibros++;

        JOptionPane.showMessageDialog(null, "Libro registrado exitosamente.");
    }

    private static void actualizarLibro() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código del libro a actualizar:");
        int indice = buscarIndicePorCodigo(codigo);
        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
            return;
        }
        libros[indice][1] = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
        libros[indice][2] = JOptionPane.showInputDialog("Ingrese el nuevo autor:");
        libros[indice][3] = JOptionPane.showInputDialog("Ingrese la nueva materia:");
        libros[indice][4] = JOptionPane.showInputDialog("Ingrese el nuevo número de páginas:");
        JOptionPane.showMessageDialog(null, "Libro actualizado exitosamente.");
    }

    private static void eliminarLibro() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código del libro a eliminar:");
        int indice = buscarIndicePorCodigo(codigo);
        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
            return;
        }
        for (int i = indice; i < contadorLibros - 1; i++) {
            libros[i] = libros[i + 1];
        }
        libros[contadorLibros - 1] = new String[ATRIBUTOS];
        contadorLibros--;
        JOptionPane.showMessageDialog(null, "Libro eliminado exitosamente.");
    }

    private static void buscarLibro() {
        String tipoBusqueda = JOptionPane.showInputDialog("Seleccione tipo de búsqueda:\n1. Secuencial\n2. Binaria");
        String codigo = JOptionPane.showInputDialog("Ingrese el código del libro a buscar:");
        int indice = -1;

        if (tipoBusqueda.equals("1")) {
            indice = buscarSecuencial(codigo);
        } else if (tipoBusqueda.equals("2")) {
            ordenarPorCodigoBurbuja();;  // Necesario para búsqueda binaria
            indice = buscarBinaria(codigo);
        }

        if (indice != -1) {
            JOptionPane.showMessageDialog(null, "Libro encontrado: " + Arrays.toString(libros[indice]));
        } else {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
        }
    }

    private static int buscarSecuencial(String codigo) {
        for (int i = 0; i < contadorLibros; i++) {
            if (libros[i][0].equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    private static int buscarBinaria(String codigo) {
        int inicio = 0;
        int fin = contadorLibros - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int comparacion = libros[medio][0].compareTo(codigo);
            if (comparacion == 0) {
                return medio;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1;
    }

    private static void ordenarLibros() {
        String campo = JOptionPane.showInputDialog("Seleccione campo para ordenar:\n1. Código\n2. Autor");
        String metodo = JOptionPane.showInputDialog("Seleccione método de ordenamiento:\n1. Burbuja\n2. Selección");

        if (campo.equals("1")) {
            if (metodo.equals("1")) {
                ordenarPorCodigoBurbuja();
            } else {
                ordenarPorCodigoSeleccion();
            }
        } else if (campo.equals("2")) {
            if (metodo.equals("1")) {
                ordenarPorAutorBurbuja();
            } else {
                ordenarPorAutorSeleccion();
            }
        }
    }

    private static void ordenarPorCodigoBurbuja() {
        for (int i = 0; i < contadorLibros - 1; i++) {
            for (int j = 0; j < contadorLibros - i - 1; j++) {
                if (libros[j][0].compareTo(libros[j + 1][0]) > 0) {
                    String[] temp = libros[j];
                    libros[j] = libros[j + 1];
                    libros[j + 1] = temp;
                }
            }
        }
    }

    private static void ordenarPorCodigoSeleccion() {
        for (int i = 0; i < contadorLibros - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < contadorLibros; j++) {
                if (libros[j][0].compareTo(libros[minIndex][0]) < 0) {
                    minIndex = j;
                }
            }
            String[] temp = libros[minIndex];
            libros[minIndex] = libros[i];
            libros[i] = temp;
        }
    }

    private static void ordenarPorAutorBurbuja() {
        for (int i = 0; i < contadorLibros - 1; i++) {
            for (int j = 0; j < contadorLibros - i - 1; j++) {
                if (libros[j][2].compareTo(libros[j + 1][2]) > 0) {
                    String[] temp = libros[j];
                    libros[j] = libros[j + 1];
                    libros[j + 1] = temp;
                }
            }
        }
    }

    private static void ordenarPorAutorSeleccion() {
        for (int i = 0; i < contadorLibros - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < contadorLibros; j++) {
                if (libros[j][2].compareTo(libros[minIndex][2]) < 0) {
                    minIndex = j;
                }
            }
            String[] temp = libros[minIndex];
            libros[minIndex] = libros[i];
            libros[i] = temp;
        }
    }

    private static int buscarIndicePorCodigo(String codigo) {
        for (int i = 0; i < contadorLibros; i++) {
            if (libros[i][0].equals(codigo)) {
                return i;
            }
        }
        return -1;
    }
}
