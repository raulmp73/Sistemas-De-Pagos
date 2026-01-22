package Main;

import java.awt.Menu;
//tu abuela bro
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu;
		float importe;
		do {
			menu= MostarMenu(); //metodo void para hacer el menu y nos devuelve un string
			
            switch (menu) {
            case 1: {
                importe = pedirImporte();
                //MetodoPago pago = new PagoEfectivo(sc); // polimorfismo
                //pago.pagar(importe);
                break;
            }
            case 2: {
                importe = pedirImporte();
                //MetodoPago pago = new PagoTarjeta(sc);
                //pago.pagar(importe);
                break;
            }
            case 3: {
                importe = pedirImporte();
                //MetodoPago pago = new PagoIBAN(sc);
                //pago.pagar(importe);
                break;
            }
			case 4: //opcion de salida
				System.out.println("Has salido del programa");
			default:
				ErrorMensaje();
			}
		} while (menu!=4);
			
	}
	/**
	 * 
	 * Aparece en todos los tipos de pago
	 * @return Devuelve un float (Importe total de la operacion) 
	 */
	public static float pedirImporte() {//lo hago en un metodo para no tener que hacerlo 3 veces
		boolean TotalCuenta=true;		
		float ImporteTotal=1.11f;		
		do {
			System.out.println("Introduce el importe total :");
			if (sc.hasNextFloat()) {
				ImporteTotal= sc.nextFloat();
				if (ImporteTotal>=0) {
					TotalCuenta=false;	
				} else {
					ErrorMensaje();
				}
			} else {
				ErrorMensaje();
				sc.next();
			}
		} while (TotalCuenta);
		return ImporteTotal;
	}

	/**
	 * Funcion que muestra el menu
	 * @return devuelve la opcion del menu
	 */
	public static int MostarMenu() {
			System.out.println("==================");
			System.out.println("=TIPO DE OPERAIÓN=");
			System.out.println("==================");
			System.out.println("1. Pago en efectivo");
			System.out.println("2. Pago con targeta");
			System.out.println("3. Transferencia bancaria");
			System.out.println("4. Salir del programa");
			System.out.println("Elije: ");
			return readInt(); 	
	}
	/**
	 * @return devuelve un numero entero
	 */
	public static int readInt() {
		
		while (!sc.hasNextInt()) {
			System.out.println("Intentalo de Nuevo");
			sc.next();
		}
		return sc.nextInt();
	}

	/**
	 * Metodo que devuelve un Mensaje de error si Falla
	 */
	public static void ErrorMensaje() {
		System.out.println("ERROR: Intentalo otra vez");
	}
}