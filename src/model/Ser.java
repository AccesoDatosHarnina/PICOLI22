package model;

import utiles.Utiles;

public class Ser {
	// schema de Ser
	private static final int vidaMax = 120;
	private static final int vidaMin = 0;
	protected float esperanzaVida;
	protected int edad = 0;
	protected int necesidadVital;

	public Ser() {
		super();
		this.esperanzaVida = this.calculaEsperanzaVida(vidaMin, vidaMax);
	}

	public Ser(Ser ser,int necesidadVital) {
		this.esperanzaVida = ser.esperanzaVida;
		this.edad=ser.edad;
		this.necesidadVital=necesidadVital;
	}
	

	public Ser(float esperanzaVida,int necesidadVital) {
		super();
		this.esperanzaVida = esperanzaVida;
		this.necesidadVital=necesidadVital;
	}

	public Ser(int necesidadVital) {
		this();
		this.necesidadVital=necesidadVital;
	}

	// comportamiento igual para todos
	public boolean vivir(int cantidad) {
		if (isAlive()) {
			cobrar(cantidad);
			envejecer();
		}
		return isAlive();
	}

	public boolean isAlive() {
		return this.edad < this.esperanzaVida;
	}

	protected void envejecer() {
		this.edad++;
	}

	protected void cobrar(int cantidad) {
		this.alimentar(cantidad);
	}

	protected void alimentar(int cantidad) {
		alimentarBase(cantidad);
	}

	//alimentarAnciano
	protected void alimentarPersona(CondicionesAlimentacion condicionesAlimentacion) {
		float porcentaje = condicionesAlimentacion.getCantidad() * 100 / necesidadVital;
		if (porcentaje <= condicionesAlimentacion.getPorcenMax() && porcentaje >= condicionesAlimentacion.getPorcenMin()) {
			this.esperanzaVida -= condicionesAlimentacion.getPeriodoMin();
		} else if (porcentaje <= condicionesAlimentacion.getPeriodoMin() - 1) {
			this.esperanzaVida -= condicionesAlimentacion.getPeriodoMax();
		}
	}

	private void alimentarBase(int cantidad) {
		alimentarPersona(new CondicionesAlimentacion(cantidad, 99, 33, 1f, 2f));
	}

	private int calculaEsperanzaVida(int minimo, int maximo) {
		return Utiles.dameNumero(maximo);
	}

	public int getEdad() {
		return edad;
	}

}
