package Metodos_De_Pagos;

import java.util.Scanner;

public class PagoIBAN extends Metodos_Pago {

    private String iban;


    private String pedirIBAN() {
        while (true) {
            System.out.println("Introduce el IBAN:");
            String texto = getSc().nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("El IBAN no puede estar vacío.");
                continue;
            }
            return texto.toUpperCase();
        }
    }

    @Override
    protected void pagarConMetodo() {
    	//super( );
        iban = pedirIBAN();

        // IMPORTANTE: aquí YA tienes el importe porque lo guardó el padre
        System.out.println("Procesando pago por IBAN...");
        System.out.println("IBAN: " + iban);
        System.out.println("Importe (heredado): " + getImporte() + "€");
        System.out.println("Pago realizado ✅");
    }
}

