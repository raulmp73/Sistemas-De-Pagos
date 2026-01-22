package Cambio_Arrays;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Calcular_Cambio {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		boolean HacerMenu= true;
		do {
			String menu= MostarMenu(); //metodo void para hacer el menu y nos devuelve un string
			
			switch (menu) {
			case "1": 
				PagoEfectivo();
				break;
			case "2":
				PagoTargeta();
				break;
			case "3":
				PagoIBAN();
				break;
			case "4": //opcion de salida
				System.out.println("Has salido del programa");
				HacerMenu=false; 
			default:
				ErrorMensaje();
			}
		} while (HacerMenu);
			
	}
	/**
	 * 
	 * Aparece en todos los tipos de pago
	 * @return Devuelve un float (Importe total de la operacion) 
	 */
	public static float PedirImporte() {//lo hago en un metodo para no tener que hacerlo 3 veces
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
	 * Metodo que hace pago en efectivo
	 */
	public static void PagoEfectivo() {
		boolean Bucle=true;
		float ImporteTotal, ImporteEfectivo=1.11f;
		ImporteTotal=PedirImporte();
		do {
			System.out.println("Introduce el Dinero en Efectivo con el que valla a pagar");
			if (sc.hasNextFloat()) {
				ImporteEfectivo= sc.nextFloat();
				if (ImporteEfectivo==ImporteTotal) {
					System.out.println("Pago realizado con exito");	
					Bucle = false;
				} else if (ImporteEfectivo>ImporteTotal) {
					CalcularCambio(ImporteEfectivo - ImporteTotal); //resto el importe total el efectivo dado y en la funcion solo usa lo que devuelve
					Bucle=false;
				} else {
					ErrorMensaje();
				}
			} else {
				ErrorMensaje();
				sc.next();
			}
		} while (Bucle);	
	}
	/**
	 * Funcion que muestra el menu
	 * @return devuelve la opcion del menu
	 */
	public static String MostarMenu() {
			System.out.println("==================");
			System.out.println("=TIPO DE OPERAIÓN=");
			System.out.println("==================");
			System.out.println("1. Pago en efectivo");
			System.out.println("2. Pago con targeta");
			System.out.println("3. Transferencia bancaria");
			System.out.println("4. Salir del programa");
			System.out.println("Elije: ");
			return sc.next(); 	
	}
	/**
	 * Esta funcion calcula el cambio que el programa le debe al usuario 
	 * @param Cent Es el total de los que se debe de devolver el programa en centimos
	 * @return Devuelve el cambio
	 */
	public static void CalcularCambio(Float ImporteTotal) {//TODO: Cambiar variable
		System.out.println("El cambio es de: ");
		//convertimos a centimos para no complicarnos la vida
		int centimos = (int) Math.round(ImporteTotal*100), billete=10000, cambio;
		
		int[] Billetes= {10000, 5000, 2000, 1000, 500};//Almacena todos los billetes
		int[] Monedas= {200, 100, 50, 20, 10, 5, 2, 1};//Almacena todas las monedas
		
		for (int i=0;i<Billetes.length;i++) {
			cambio = centimos/Billetes[i];
			if (cambio==1) {
				System.out.println("1 billete de "+Billetes[i]/100+"€");
			} else if (cambio>1) {
				System.out.println(cambio+" billetes de "+Billetes[i]/100+"€");
			}
			centimos -= cambio*Billetes[i];
		}
		for (int i=0;i<Monedas.length;i++) {
			cambio = centimos/Monedas[i];
			if (cambio==1) {
				System.out.println("1 Moneda de "+Monedas[i]/100+"€");
			} else if (cambio>1) {
				System.out.println(cambio+" monedas de "+Monedas[i]/100+"€");
			}
			centimos -= cambio*Monedas[i];
		}
	}
	/**
	 * Sirve para realizar el pago con tarjeta
	 * @param PrimerNumeroChar recojemos en caracter 1 de la tarjeta
	 * @param PrimerNumero con el puedo saber el tipo de la tarjeta y si esta mal 
	 */
	public static void PagoTargeta() {
		int PrimerNumero;
		String Tarjeta, TipoTarjeta;
		boolean Tarjetatrue=true, EsTarjeta=true;
		char PrimerNumChar;
		float ImporteTotal=PedirImporte();
		
		sc.nextLine();
		do {
			System.out.println("Escribe tu tarjeta");
			Tarjeta = sc.nextLine();
			Tarjeta = Tarjeta.replace(" ", "");//elimina espacios 
			int NumTarjeta = Tarjeta.length();
			PrimerNumChar=Tarjeta.charAt(0);
			PrimerNumero = Integer.parseInt(String.valueOf(PrimerNumChar));
			if (ValidarDigitos(Tarjeta)) {
				switch (NumTarjeta){
				case 15: //
					if (PrimerNumero==3) { 
						TipoTarjeta="American Express";
						OperacionRealizada(TipoTarjeta, ImporteTotal, CodigoOperacion(), DevuelveFecha(), EsTarjeta, "");
						Tarjetatrue=false;
						break;
					} else {
						ErrorMensaje();
					}
					break;
				case 16: //
					if (PrimerNumero==4) {
						TipoTarjeta="VISA";
						OperacionRealizada(TipoTarjeta, ImporteTotal, CodigoOperacion(), DevuelveFecha(), EsTarjeta, "");
						Tarjetatrue=false;
						break;
					}  else if (PrimerNumero==5) {
						TipoTarjeta="MasterCard";
						OperacionRealizada(TipoTarjeta, ImporteTotal, CodigoOperacion(), DevuelveFecha(), EsTarjeta, "");
						Tarjetatrue=false;
						break;
					}
					else {
						ErrorMensaje();
					}	
				default:
					ErrorMensaje();
				}	
			} else { 
				ErrorMensaje();
			}
		} while (Tarjetatrue);		
	}
	/**
	 * Validar que todo todos son dogitos
	 * @param Tarjetas guarda la tarjeta introducida
	 * @return si la tarjeta es correcta o no (booleano)
	 */
	public static boolean ValidarDigitos(String Tarjetas) {  
			if (Tarjetas== null || Tarjetas.isEmpty()) return false;
		    return Tarjetas.chars().allMatch(Character::isDigit);
	}
	/**
	 * Metodo con el que hacemos el pago con Iban
	 */
	public static void PagoIBAN() {
		
		String Iban="", nombre;
		boolean Bucle=true, EsIBAN=false;
		float TotalImporte=PedirImporte();
		
		sc.nextLine();
		do {
			System.out.println("Escribe el nombre del titular: ");
			nombre = sc.nextLine();
			System.out.println("Escribe tu IBAN: ");
			Iban=sc.nextLine();
			Iban = Iban.replace(" ", "");	
			if (ValidarIBAN(Iban, nombre)) {
				OperacionRealizada(Iban, TotalImporte, CodigoOperacion(), DevuelveFecha(), EsIBAN, nombre);
				Bucle=false;
			} else {
				ErrorMensaje();
			}
		} while (Bucle);
	}
	/**
	 * Valida el IBAN
	 * @param Iban valor del Iban 
	 * @return Si el Iban en correcto o no
	 */
	public static boolean ValidarIBAN(String Iban, String Nombre) {
		if (Iban== null || Iban.isEmpty()) return false;
		return (Iban.length()==24 && Iban.substring(0, 2).chars().allMatch(Character::isLetter) && Iban.substring(2, 24).chars().allMatch(Character::isDigit) && Nombre.chars().filter(c -> c != ' ').allMatch(Character::isLetter)); 
	}
	/**
	 * @return La fecha de la maquina
	 */
	public static String DevuelveFecha() {
		LocalDateTime date=LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy - HH:mm:ss");
	    String fecha = date.format(formato);
	    return fecha;
	}
	/**
	 * 
	 * @param Tarjeta puede ser el iban o El tipo de tarjeta
	 * @param Total Total de la operacion solo pone 2 decimales
	 * @param codigo Codigo random gerenrado por la funcion
	 * @param fecha Fecha del sistema en el momento de la operacion
	 * @param tipoOperacion Con este booleano se si es una tarjeta o un IBAN
	 * @return Toda la informacion de la operacion
	 */
	public static void OperacionRealizada(String Tarjeta,Float Total, String codigo, String fecha, boolean tipoOperacion, String titular){
		codigo=CodigoOperacion();
		fecha=DevuelveFecha();
		System.out.println("Pago Realizado con exito.");
		System.out.println("Total de la Operacion: "+ Math.round(Total * 100f) / 100f);
		System.out.println(tipoOperacion ? "Tipo de tarjeta: " + Tarjeta : "Titular: " +titular+ "\nIBAN: " + Tarjeta.toUpperCase());
		System.out.println("Codigo de operacion: "+codigo);
		System.out.println("Fecha y hora de la Operacion: "+fecha);
	}
	/**
	 * @return un codigo aleatorio 3 letras y 4 numeros
	 */
	public static String  CodigoOperacion() {
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
	/**
	 * Metodo que devuelve un Mensaje de error si Falla
	 */
	public static void ErrorMensaje() {
		System.out.println("ERROR: Intentalo otra vez");
	}
}