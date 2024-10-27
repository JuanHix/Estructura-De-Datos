import java.util.Scanner;

public class PrimeraPila {
    int tope = -1, maximo, npar = 0, nimpar = 0;

    public void generar(int cantidadElementos, int totalPila, int[] vectorElementos) {
        maximo = totalPila;
        for (int i = 0; i < cantidadElementos; i++) {
            tope++;
            vectorElementos[tope] = (int) Math.floor(Math.random() * 100);

            if (vectorElementos[tope] % 2 == 0) {
                npar++;
            } else {
                nimpar++;
            }
        }
    }

    public void imprimir(int[] vectorImpresion) {
        System.out.println("Elementos que están en la Pila:");
        for (int i = 0; i <= tope; i++) {
            System.out.println("Posición: " + i + " con valor: " + vectorImpresion[i]);
        }
        System.out.println("El tope de la pila es " + tope + " y el máximo de la pila es " + maximo);
    }

    public void mostrar() {
        System.out.println("Cantidad de números pares: " + npar);
        System.out.println("Cantidad de números impares: " + nimpar);
    }

    // a. Método para buscar un elemento indicando su posición.
    public void buscar(int[] vectorElementos, int posicion) {
        if (posicion <= tope && posicion >= 0) {
            System.out.println("Elemento en posición " + posicion + ": " + vectorElementos[posicion]);
        } else {
            System.out.println("Posición fuera de rango.");
        }
    }

    // b. Método para insertar un nuevo elemento en la pila cuando haya espacio.
    public void insertar(int[] vectorElementos, int nuevoElemento) {
        if (tope + 1 < maximo) {
            tope++;
            vectorElementos[tope] = nuevoElemento;
            if (nuevoElemento % 2 == 0) {
                npar++;
            } else {
                nimpar++;
            }
            System.out.println("Elemento " + nuevoElemento + " insertado en la pila.");
        } else {
            System.out.println("La pila está llena, no se puede insertar el elemento.");
        }
    }

    // c. Método para actualizar un elemento indicando su posición.
    public void actualizar(int[] vectorElementos, int posicion, int nuevoValor) {
        if (posicion <= tope && posicion >= 0) {
            // Update par/impar counts
            if (vectorElementos[posicion] % 2 == 0) npar--;
            else nimpar--;

            vectorElementos[posicion] = nuevoValor;

            if (nuevoValor % 2 == 0) npar++;
            else nimpar++;

            System.out.println("Elemento en posición " + posicion + " actualizado a " + nuevoValor);
        } else {
            System.out.println("Posición fuera de rango.");
        }
    }

    
    // d. Método para eliminar un elemento de la pila en una posición específica.
    public void eliminar(int[] vectorElementos, int posicion) {
        if (posicion <= tope && posicion >= 0) {
            // Adjust even/odd count
            if (vectorElementos[posicion] % 2 == 0) npar--;
            else nimpar--;

            // Shift elements to fill the gap
            for (int i = posicion; i < tope; i++) {
                vectorElementos[i] = vectorElementos[i + 1];
            }

            // Clear the last element and adjust `tope`
            vectorElementos[tope] = 0;  // Optional: clear removed position
            tope--;

            System.out.println("Elemento en posición " + posicion + " eliminado de la pila.");
        } else {
            System.out.println("Posición fuera de rango.");
        }
    }

    public static void main(String[] args) {
        PrimeraPila pila1 = new PrimeraPila();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese el tamaño del vector: ");
        int maximoVector = teclado.nextInt();

        System.out.println("Ingrese la cantidad de elementos a registrar: ");
        int cantElementos = teclado.nextInt();

        if (cantElementos <= maximoVector) {
            int[] vector = new int[maximoVector];
            pila1.generar(cantElementos, maximoVector, vector);
            pila1.imprimir(vector);
            pila1.mostrar();

            // Ejemplo de uso de los nuevos métodos
            System.out.println("\nIngrese una posición para buscar: ");
            int posicion = teclado.nextInt();
            pila1.buscar(vector, posicion);

            System.out.println("\nIngrese un nuevo elemento para insertar en la pila: ");
            int nuevoElemento = teclado.nextInt();
            pila1.insertar(vector, nuevoElemento);

            System.out.println("\nIngrese una posición para actualizar: ");
            posicion = teclado.nextInt();
            System.out.println("\nIngrese el nuevo valor: ");
            int nuevoValor = teclado.nextInt();
            pila1.actualizar(vector, posicion, nuevoValor);

            System.out.println("\nIngrese una posición para eliminar de la pila: ");
            int posicionEliminar = teclado.nextInt();
            pila1.eliminar(vector, posicionEliminar);

            pila1.imprimir(vector);
            pila1.mostrar();
        } else {
            System.out.println("El vector no puede ser menor que la cantidad de elementos a guardar.");
        }

        teclado.close();
    }
}
