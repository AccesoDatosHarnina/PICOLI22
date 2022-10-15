package model;

public class ControlSer {

	public static Ser crearSer() {
		return new Menor();
	}

	public static Ser crearSer(int esperanza,int necesidadVital) {
		return new Menor(esperanza,necesidadVital);
	}

	public static Ser controlarEtapas(Ser ser,int necesidadVital) {
		if (ser.getEdad() == 18)
			return new Adulto(ser);
		if (ser.getEdad() == 65)
			return new Ser(ser,necesidadVital);
		return ser;
	}
}
