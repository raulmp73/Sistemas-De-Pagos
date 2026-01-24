package Metodos_De_Pagos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public abstract class Metodos_Pago {

    private float importe;
    private Scanner sc = new Scanner(System.in);
    private String codigoOperacion, fechaOperacion;
    
    /**
	 * @return the importe
	 */
	public float getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(float importe) {
		this.importe = importe;
	}
	/**
	 * @return the sc
	 */
	public Scanner getSc() {
		return sc;
	}
	/**
	 * @param sc the sc to set
	 */
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	public Metodos_Pago() {
        this.sc = new Scanner(System.in);
        this.importe = 0;
        this.codigoOperacion = "";
        this.fechaOperacion = "";
    }
    public Metodos_Pago(Scanner scanner, float Importe,String CodigoOperacion, String FechaOperacion) {
    		sc = scanner;
    		importe = Importe;
    		codigoOperacion = CodigoOperacion;
    		fechaOperacion = FechaOperacion;
    }
    public void devuelveFecha() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy - HH:mm:ss");
        fechaOperacion = date.format(formato);
    }
    /**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	/**
	 * @return the fechaOperacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	/**
	 * @param fechaOperacion the fechaOperacion to set
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	protected void codigoOperacion() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 3; i++) codigo.append((char) ('A' + random.nextInt(26)));
        for (int i = 0; i < 4; i++) codigo.append(random.nextInt(10));
        codigoOperacion = codigo.toString();
    }

    // El padre pide y GUARDA el importe
    protected void pedirYGuardarImporte() {
        while (true) {
            System.out.println("Introduce el importe:");

            if (!sc.hasNextFloat()) {
                System.out.println("No has escrito un número");
                sc.next();
                continue;
            }

            float imp = sc.nextFloat();

            if (imp <= 0) {
                System.out.println("El importe debe ser mayor que 0");
                continue;
            }

            sc.nextLine(); // limpiar salto de línea
            this.importe = imp; // AQUÍ se guarda en el padre
            return;
        }
    }

    // Método plantilla: siempre hace lo mismo para todos
    public final void pagar() {
        pedirYGuardarImporte();   // 1) lo hace el padre
        pagarConMetodo();         // 2) lo hace la hija
    }

    // Cada hija implementa SU parte
    protected abstract void pagarConMetodo();

    //public float getImporte() {
      //  return importe;
    //}
}
