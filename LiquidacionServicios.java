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

        for (int i = 0; i < familias; i++) {
            System.out.println("**** Ingrese los datos correspondientes a la familia " + (i + 1) + " ***" );

            System.out.print("* Ingrese su estrato actual 1, 2, o 3: ");
            estrato[i] = captura.nextInt();

            System.out.print("* Ingrese el valor del recibo del agua: ");
            agua[i] = captura.nextDouble();
            System.out.print("* Ingrese el valor del recibo del luz: ");
            luz[i] = captura.nextDouble();
            System.out.print("* Ingrese el valor del recibo del gas: ");
            gas[i] = captura.nextDouble();
        }

        for (int i = 0; i < familias; i++) {
            double  totalAgua = 0;
            double  totalLuz = 0;
            double  totalGas = 0;

            if (estrato[i] == 1){
                totalAgua = agua[i] - (0.20 * agua[i]);
                totalLuz = luz[i] - (0.20 * luz[i]);
                totalGas = gas[i] - (0.20 * gas[i]);
            } else if (estrato[i] == 2) {
                totalAgua = agua[i]- ( 0.15 * agua[i]);
                totalLuz = luz[i] - ( 0.15 * luz[i]);
                totalGas = gas[i] - (0.15 * gas[i]);
            } else if (estrato[i] == 3) {
                totalAgua = agua[i] - (0.09 * agua[i]);
                totalLuz = luz[i] - (0.09 * luz[i]);
                totalGas = gas[i] - (0.09 * gas[i]);
            }

            System.out.println(" **** Liquidacion familia " + (i + 1) + " ***" );
            System.out.println("Recibo Agua: " + totalAgua);
            System.out.println("Recibo Luz: " + totalLuz);
            System.out.println("Recibo Gas: " + totalGas);
            System.out.println(("El total de los servicios es : ") + (totalAgua + totalLuz + totalGas));
        }
    }
}