package Metodos_De_Pagos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class PagoTarjeta extends Metodos_Pago {
	
	
	public PagoTarjeta(Scanner sc) {
		super(sc);
	}

	@Override
	public void procesarPago() {
		boolean ok = false;

        while (!ok) {
            System.out.println("Introduce el número de la tarjeta:");
            String tarjeta = sc.nextLine().replace(" ", "");

            // Validamos que la tarjeta sea numérica y tenga longitud correcta
            if (validarTarjeta(tarjeta)) {

                // Detectamos el tipo de tarjeta
                String tipoTarjeta = obtenerTipoTarjeta(tarjeta);

                // Si el tipo es válido, finalizamos la operación
                if (tipoTarjeta != null) {
                    operacionRealizada(tipoTarjeta);
                    ok = true;
                } else {
                    errorMensaje();
                }
            } else {
                errorMensaje();
            }
        }
	}
	
	 /*
     * Comprueba que la tarjeta tenga solo dígitos
     * y longitud válida (15 o 16)
     */
    private boolean validarTarjeta(String tarjeta) {
        return tarjeta != null && tarjeta.matches("\\d{15,16}");
    }

    /*
     * Determina el tipo de tarjeta según el primer dígito y la longitud
     */
    private String obtenerTipoTarjeta(String tarjeta) {
        int primerNumero = Character.getNumericValue(tarjeta.charAt(0));

        // American Express
        if (tarjeta.length() == 15 && primerNumero == 3) {
            return "American Express";
        }

        // VISA o MasterCard
        if (tarjeta.length() == 16) {
            if (primerNumero == 4) return "VISA";
            if (primerNumero == 5) return "MasterCard";
        }

        // Tarjeta no válida
        return null;
   }
    
    private void operacionRealizada(String Tarjeta){
		String codigo = codigoOperacion();
		String fecha =devuelveFecha();
		System.out.println("Pago Realizado con exito.");
		System.out.println("Total de la Operacion: "+ Math.round(importe * 100f) / 100f);
		System.out.println("Tipo de tarjeta: " + Tarjeta);
		System.out.println("Codigo de operacion: "+ codigo);
		System.out.println("Fecha y hora de la Operacion: "+ fecha);
	}
    
    public static String codigoOperacion() {
		Random random = new Random();
		String codigo = "";
		for (int i = 0; i < 3; i++) {
			 codigo += (char) ('A' + random.nextInt(26));
		}
		 for(int i = 0; i < 4; i++) { 
	           codigo += random.nextInt(10);
	        }	 
		 return codigo;
	}
    
    public static String devuelveFecha() {
		LocalDateTime date=LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy - HH:mm:ss");
	    String fecha = date.format(formato);
	    return fecha;
	}
 
}
