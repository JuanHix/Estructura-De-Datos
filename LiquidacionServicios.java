import java.util.Scanner;

public class LiquidacionServicios {
    public static void main(String[] args) {
        Scanner captura = new Scanner(System.in);
        System.out.println("***************************************************");
        System.out.println("***         Subsidios Servicios Publicos        ***");
        System.out.println("***************************************************");

        System.out.print("Ingrese la cantidad de familias a liquidar: ");
        int familias = captura.nextInt();

        double[] agua = new double[familias];
        double[] luz = new double[familias];
        double[] gas = new double[familias];
        int[] estrato = new int[familias];

        /* Ciclo que itera sobre los datos ingresados */
        for (int i = 0; i < familias; i++) {
            System.out.println("**********************************************************");
            System.out.println("**** Ingrese los datos correspondientes a la familia " + (i + 1) + " ***" );
            System.out.println("**********************************************************");

            while (true) {
                System.out.print("1. Ingrese su estrato actual (1, 2 o 3): ");
                estrato[i] = captura.nextInt();
                if (estrato[i] == 1 || estrato[i] == 2 || estrato[i] == 3) {
                    break; // Salir del bucle si el estrato es solid
                } else {
                    System.out.println("Error: El estrato ingresado no es válido. Por favor, ingrese 1, 2 o 3.");
                }
            }

            System.out.print("2. Ingrese el valor del recibo del agua: $ ");
            agua[i] = captura.nextDouble();
            System.out.print("3. Ingrese el valor del recibo de luz: $ ");
            luz[i] = captura.nextDouble();
            System.out.print("4. Ingrese el valor del recibo de gas: $ ");
            gas[i] = captura.nextDouble();
        }

        /* Logica realizando el calculo de los recibos */
        for (int i = 0; i < familias; i++) {
            double descuento;

            if (estrato[i] == 1) {
                descuento = 0.20;
            } else if (estrato[i] == 2) {
                descuento = 0.15;
            } else {
                descuento = 0.09;
            }

            double totalAgua = agua[i] * (1 - descuento);
            double totalLuz = luz[i] * (1 - descuento);
            double totalGas = gas[i] * (1 - descuento);
            double totalServicios = totalAgua + totalLuz + totalGas;

            /* Presenta el calculo de los recibos */
            System.out.println("**** Liquidación familia " + (i + 1) + " ***" );
            System.out.printf("Recibo Agua: $ %.2f\n", totalAgua);
            System.out.printf("Recibo Luz: $ %.2f\n", totalLuz);
            System.out.printf("Recibo Gas: $ %.2f\n", totalGas);
            System.out.printf("El total de los servicios es: $ %.2f\n", totalServicios);
        }

        captura.close();
    }
}

