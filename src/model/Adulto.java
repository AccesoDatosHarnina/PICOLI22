package model;

public class Adulto extends Ser {
	// especifico de adulto
	private long ahorros = 0;
	
	public Adulto(Ser ser) {
		super(ser,ser.necesidadVital);
	}

	@Override
	protected void cobrar(int cantidad) {
		///y liskov
		this.calcularCobroAdulto(cantidad);
	}
	
	@Override
	protected void alimentar(int cantidad) {
		alimentarAdultos(cantidad);
	}
	private void alimentarAdultos(int cantidad) {
		alimentarPersona(new CondicionesAlimentacion(
				cantidad, 99, 50, 0.5f, 1f));
	}

	long getAhorros() {
		return ahorros;
	}

	private void calcularCobroAdulto(int cantidad) {
		int sobrante = cantidad - necesidadVital;
		if (sobrante >= 0) {
			this.alimentar(cantidad);
			this.ahorros += sobrante;
		} else {
			calcularAportacionAhorro(cantidad, sobrante);
		}
	}
	private void calcularAportacionAhorro(int cantidad, int sobrante) {
		this.ahorros += sobrante;
		// tengo ahorros suficientes
		if (ahorros >= 0) {
			this.alimentar(cantidad);
		} else {
			this.alimentar(cantidad - (int) ahorros);
			this.ahorros = 0;
		}
	}
	public int calcularNecesidadSegunAhorros(int necesidadVital) {
		long saldo = getAhorros() - necesidadVital;
		long retorno = 0;
		if (saldo < 0) {
			retorno = Math.abs(saldo);
		}
		return (int) retorno;
	}
}
