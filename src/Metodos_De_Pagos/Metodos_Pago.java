package Metodos_De_Pagos;

import java.util.Scanner;

public abstract class Metodos_Pago {

    private float importe;
    private Scanner sc = new Scanner(System.in);

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
    }
    public Metodos_Pago(Scanner scanner, float Importe) {
    		sc = scanner;
    		importe = Importe;
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
