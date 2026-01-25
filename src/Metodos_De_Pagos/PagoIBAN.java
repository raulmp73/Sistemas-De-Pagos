package Metodos_De_Pagos;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class PagoIBAN extends Metodos_Pago {

    private String Iban;
    String nombreTitular;
	public PagoIBAN (String Iban,String nombreTitular) {
		
		this.Iban=pedirCuentaIban(getSc());
		this.nombreTitular=pedirNombreTitular(getSc());
		if(Iban==null) {
			throw new IllegalArgumentException("El IBAN no puede estar vacío");
		}
		if(!ValidacionCuentaIban(Iban.replace(" ", ""))) {
			throw new IllegalArgumentException("El IBAN introducido no es válido");
		}
		
		if(nombreTitular==null) {
			throw new IllegalArgumentException("El nombre no puede estar vacío");
		}
		if(!validacionNombreTitular(nombreTitular)) {
			throw new IllegalArgumentException("El nombre introducido no es válido");
		}
		
	}
		/**
		 * Funcion para pedir el nombre del titular y comprobar que son letras todo
		 * @return true es correcto falso no
		 */
		public boolean validacionNombreTitular(String nombreTitular) {
			nombreTitular=nombreTitular.replace(" ","");
			return (nombreTitular.chars()).allMatch(Character::isLetter);
		}
		/**
		 * Funcion para comprobar formato del iban
		 * @return true si es correcto falso si no
		 */
		public static boolean ValidacionCuentaIban(String Iban) {
			    String regex = "^[A-Z]{2}\\d{2}[A-Z0-9]{10,30}$";
			    return Iban.toUpperCase().matches(regex);
		}
		private String pedirNombreTitular(Scanner sc) {
			System.out.println("introduzca el nombre del titular");
			return sc.next();
		}
		private String pedirCuentaIban(Scanner sc) {
			System.out.println("introduzca la cuenta IBAN");
			return sc.next();
		}
		
		@Override
		protected void pagarConMetodo() {
			// TODO Auto-generated method stub
			String fecha = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    System.out.println("✅ Pago por transferencia IBAN realizado");
    System.out.println("Titular: " + nombreTitular);
    System.out.println("IBAN: " + Iban);
    System.out.println("Importe: " + getImporte() + "€");
    System.out.println("Fecha: " + fecha);
    
		}
		

}

