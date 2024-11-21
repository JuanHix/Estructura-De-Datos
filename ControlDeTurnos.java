import javax.swing.*;

public class ControlDeTurnos {

    static class NodoTurno {
        String turno;
        NodoTurno siguiente;

        public NodoTurno(String turno) {
            this.turno = turno;
            this.siguiente = null;
        }
    }

    static class ListaCircular {
        private NodoTurno inicio;
        private NodoTurno ultimo;
        private int tamano;

        public ListaCircular() {
            inicio = null;
            ultimo = null;
            tamano = 0;
        }

        public boolean estaVacia() {
            return inicio == null;
        }

        public int obtenerTamano() {
            return tamano;
        }

        public void agregarTurno(String turno) {
            NodoTurno nuevo = new NodoTurno(turno);
            if (estaVacia()) {
                inicio = nuevo;
                ultimo = nuevo;
                ultimo.siguiente = inicio;
            } else {
                ultimo.siguiente = nuevo;
                nuevo.siguiente = inicio;
                ultimo = nuevo;
            }
            tamano++;
        }

        public String asignarTurno() {
            if (estaVacia()) {
                return null;
            } else {
                return inicio.turno;
            }
        }

        public String eliminarTurno() {
            if (estaVacia()) {
                return null;
            } else {
                String turnoAtendido = inicio.turno;
                if (inicio == ultimo) {
                    inicio = null;
                    ultimo = null;
                } else {
                    inicio = inicio.siguiente;
                    ultimo.siguiente = inicio;
                }
                tamano--;
                return turnoAtendido;
            }
        }

        public String mostrarTurnos() {
            if (estaVacia()) {
                return "No hay turnos en la lista.";
            } else {
                StringBuilder turnos = new StringBuilder();
                NodoTurno actual = inicio;
                do {
                    turnos.append(actual.turno).append(" -> ");
                    actual = actual.siguiente;
                } while (actual != inicio);
                return turnos.toString();
            }
        }
    }

    public static void main(String[] args) {
        ListaCircular turnos = new ListaCircular();
        String[] operarios = {"Operario 1", "Operario 2", "Operario 3"};
        int codigoTurno = 1;
        boolean continuar = true;

        while (continuar) {
            String opcion = JOptionPane.showInputDialog(null,
                    "****  CONTROL DE TURNOS  ****\n" +
                            "\n" +
                            "Seleccione una opción:\n" +
                            "1. Solicitar nuevo turno\n" +
                            "2. Asignar turno a un operario\n" +
                            "3. Eliminar turno atendido\n" +
                            "4. Mostrar lista de turnos\n" +
                            "5. Salir");

            if (opcion == null) {
                break;
            }

            switch (opcion) {
                case "1":
                    String nuevoTurno = "T-" + codigoTurno;
                    turnos.agregarTurno(nuevoTurno);
                    JOptionPane.showMessageDialog(null, "Nuevo turno generado: " + nuevoTurno);
                    codigoTurno++;
                    break;

                case "2":
                    if (turnos.estaVacia()) {
                        JOptionPane.showMessageDialog(null, "No hay turnos disponibles.");
                    } else {
                        String operarioSeleccionado = (String) JOptionPane.showInputDialog(
                                null,
                                "Seleccione un operario para asignar el turno:",
                                "Asignar Turno",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                operarios,
                                operarios[0]);
                        if (operarioSeleccionado != null) {
                            JOptionPane.showMessageDialog(null, "Turno " + turnos.asignarTurno() +
                                    " asignado a " + operarioSeleccionado);
                        }
                    }
                    break;

                case "3":
                    String turnoEliminado = turnos.eliminarTurno();
                    if (turnoEliminado == null) {
                        JOptionPane.showMessageDialog(null, "No hay turnos por eliminar.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Turno atendido y eliminado: " + turnoEliminado);
                    }
                    break;

                case "4":
                    JOptionPane.showMessageDialog(null, "Lista de turnos:\n" + turnos.mostrarTurnos());
                    break;

                case "5":
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema de turnos.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
}