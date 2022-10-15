package model;

public class Menor extends Ser {
	//especifico de menor
	private float factorDeDesarrollo = 0;
	
	public Menor() {
		super();
	}

	public Menor(int vida,int necesidadVital) {
		super(vida,necesidadVital);
	}

	@Override
	protected void envejecer() {
		super.envejecer();
		if (this.edad == 18) {
			if (this.factorDeDesarrollo < 55) {
				this.matar();
			}
		}
	}
	
	@Override
	protected void alimentar(int cantidad) {
		alimentarMenor(cantidad);
	}
	private void alimentarMenor(int cantidad) {
		float porcentaje = (float) cantidad * 100 / necesidadVital;
		this.factorDeDesarrollo += 5.5f * porcentaje / 100;
	};
	
	private void matar() {
		edad = (int) (esperanzaVida + 1);
	};

}
