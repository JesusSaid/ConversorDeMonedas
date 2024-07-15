import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletionService;

public class Principal {
    public static void main(String[] args) {

        List<String> monedas = new ArrayList<>();
        monedas.add("ARS");
        monedas.add("BOB");
        monedas.add("BRL");
        monedas.add("CLP");
        monedas.add("COP");
        monedas.add("USD");


        Scanner lectura = new Scanner(System.in);
        while (true){
            System.out.println("***********************************");
            System.out.println("Bienvenido al conversor de monedas");
            System.out.println("1. Dolar -> Peso Argentino");
            System.out.println("2. Peso Argentino -> Dolar");
            System.out.println("3. Dolar -> Real Brasileno");
            System.out.println("4. Real Brasileno -> Dolar");
            System.out.println("5. Dolar -> Peso Colombiano");
            System.out.println("6. Peso Colombiano -> Dolar");
            System.out.println("7. Salir");
            System.out.println("\n***********************************");
            System.out.print("Seleccione una opción valida: ");
            int opcion = lectura.nextInt();
            double cantidad ;

            switch (opcion) {
                case 1:
                    imprimir(lectura, monedas, 5, 0);
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = lectura.nextDouble();
                    System.out.println(cantidad + "equivale a " + convertirMonedas(cantidad, monedas.get(0), monedas.get(5)));
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = lectura.nextDouble();
                    System.out.println(cantidad + " equivale a " + convertirMonedas(cantidad, monedas.get(5), monedas.get(2)));
                    break;
                case 4:
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = lectura.nextDouble();
                    System.out.println(cantidad + " equivale a " + convertirMonedas(cantidad, monedas.get(2), monedas.get(5)));
                    break;
                case 5:
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = lectura.nextDouble();
                    System.out.println(cantidad + " equivale a " + convertirMonedas(cantidad, monedas.get(5), monedas.get(4)));
                    break;
                case 6:
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = lectura.nextDouble();
                    System.out.println(cantidad + " equivale a " + convertirMonedas(cantidad, monedas.get(4), monedas.get(5)));
                    break;
                case 7:
                    System.out.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }

    }

    private static void imprimir(Scanner scanner, List<String> monedas, int monedaOrigenIndex, int monedaDestinoIndex) {
        System.out.print("Ingrese la cantidad: ");
        double cantidad = scanner.nextDouble();
        System.out.println(cantidad +" en " +monedas.get(monedaOrigenIndex)+" equivale a " + convertirMonedas(cantidad, monedas.get(monedaOrigenIndex), monedas.get(monedaDestinoIndex)));
        System.out.println(" en "+monedas.get(monedaDestinoIndex));
    }


    private static double convertirMonedas(double cantidad,String monedaOriginal, String monedaCambio){

        //llamar a la calse consultaConversor para poder hacer uso de la API
        ConsultarMoneda consultaConversor = new ConsultarMoneda();
        JsonObject responseJson = consultaConversor.buscarMoneda(monedaOriginal);

        // Obtener la tasa de conversión para la moenda cambio
        double conversionRateEUR = responseJson.getAsJsonObject("conversion_rates").get(monedaCambio).getAsDouble();

        return  conversionRateEUR * cantidad;
    }
}
