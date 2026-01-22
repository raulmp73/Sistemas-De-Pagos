package Metodos_De_Pagos;

public abstract class Metodos_Pago {
	
	protected float importe;
	
	public Metodos_Pago(float importe) {
		this.importe = importe;
	}
	
	public abstract void pagar();
}
