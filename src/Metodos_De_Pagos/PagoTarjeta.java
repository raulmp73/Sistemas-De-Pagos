package Metodos_De_Pagos;

import java.util.Scanner;

public class PagoTarjeta extends Metodos_Pago {

    // Constructores
    public PagoTarjeta() {
        super();
    }

    public PagoTarjeta(Scanner sc, float importe, String codigoOperacion, String fechaOperacion) {
        super(sc, importe, codigoOperacion, fechaOperacion);
    }

    // Implementación del método abstracto
    @Override
    protected void pagarConMetodo() {
        boolean ok = false;

        while (!ok) {
            System.out.println("Introduce el número de la tarjeta:");
            String tarjeta = getSc().nextLine().replace(" ", ""); // Scanner heredado del padre

            if (validarTarjeta(tarjeta)) {
                String tipoTarjeta = obtenerTipoTarjeta(tarjeta);

                if (tipoTarjeta != null) {
                    // Generamos código y fecha usando los métodos del padre
                    codigoOperacion();
                    devuelveFecha();

                    // Mostramos la operación
                    System.out.println("Pago realizado con éxito");
                    System.out.println("Total: " + getImporte());
                    System.out.println("Tipo de tarjeta: " + tipoTarjeta);
                    System.out.println("Código: " + getCodigoOperacion());
                    System.out.println("Fecha: " + getFechaOperacion());

                    ok = true;
                } else {
                    System.out.println("ERROR: tipo de tarjeta no válido, inténtalo otra vez");
                }
            } else {
                System.out.println("ERROR: número de tarjeta no válido, inténtalo otra vez");
            }
        }
    }

    // Valida que la tarjeta tenga solo dígitos y longitud 15 o 16
    private boolean validarTarjeta(String tarjeta) {
    	if (tarjeta == null) return false;
        if (tarjeta.length() != 15 && tarjeta.length() != 16) return false;
        return tarjeta.chars().allMatch(Character::isDigit);
    }

    // Determina el tipo de tarjeta según el primer dígito y longitud.
    private String obtenerTipoTarjeta(String tarjeta) {
        int primerNumero = Character.getNumericValue(tarjeta.charAt(0));

        if (tarjeta.length() == 15 && primerNumero == 3) return "American Express";
        if (tarjeta.length() == 16) {
            if (primerNumero == 4) return "VISA";
            if (primerNumero == 5) return "MasterCard";
        }
        return null;
    }
}
