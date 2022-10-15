package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EstadoTest {

	@Test
	void test() {
		Estado estado=new Estado();
		long capital=4500;
		//10+10+10
		int personasPorSector=10;
		int necesidadVitalMenores=100;
		int necesidadVitalAdultos=100;
		int necesidadVitalTrabajadores=100*2;
		int necesidadVitalAncianos=50;
		long total=calcula(personasPorSector, necesidadVitalMenores, necesidadVitalAdultos, necesidadVitalTrabajadores,
				necesidadVitalAncianos);
		estado.setCapital(capital);
		estado.pagar();
		assertEquals(total,estado.getPrespuestoNecesario());
		//total con dinero para el 60 por cien de los menores
		int deficitMenores = 60;
		capital=4100;
		estado.setCapital(capital);
		estado.pagar();
		total=calcula(personasPorSector, deficitMenores, necesidadVitalAdultos, necesidadVitalTrabajadores,
				necesidadVitalAncianos);
		assertEquals(total,estado.getPrespuestoNecesario());
		deficitMenores = 50;
		total=calcula(personasPorSector, deficitMenores, necesidadVitalAdultos, necesidadVitalTrabajadores,
				necesidadVitalAncianos);
		capital=4000;
		estado.setCapital(capital);
		estado.pagar();
		assertEquals(total,estado.getPrespuestoNecesario());
		deficitMenores = 50;
		int deficitAncianos=10;		
		total=calcula(personasPorSector, deficitMenores, necesidadVitalAdultos, necesidadVitalTrabajadores, deficitAncianos);
		long deficit=100;
		capital=3600;
		estado.setCapital(capital);
		estado.pagar();
		assertEquals(total,estado.getPrespuestoNecesario()-deficit);
	}

	private int calcula(int personasPorSector, int necesidadVitalMenores, int necesidadVitalAdultos,
			int necesidadVitalTrabajadores, int necesidadVitalAncianos) {
		return necesidadVitalMenores*personasPorSector+necesidadVitalAdultos*personasPorSector
				+necesidadVitalAncianos*personasPorSector+necesidadVitalTrabajadores*personasPorSector;
	}

}
