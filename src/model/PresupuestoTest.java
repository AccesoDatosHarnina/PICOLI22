package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Estado;
import model.Presupuesto;

class PresupuestoTest {

	@Test
	void test() {
		Estado estado=new Estado();
		//10+10+10
		int personasPorSector=10;
		int necesidadVitalMenores=100;
		int necesidadVitalAdultos=100;
		int necesidadVitalTrabajadores=100*2;
		int necesidadVitalAncianos=50;
		long total=calcula(personasPorSector, necesidadVitalMenores, necesidadVitalAdultos, necesidadVitalTrabajadores,
				necesidadVitalAncianos);
		Presupuesto presupuesto=new Presupuesto(estado.getSectors());
		assertEquals(total,presupuesto.getCantidadNecesaria());
		//total con dinero para el 60 por cien de los menores
		int deficitMenores = 60;
		total=calcula(personasPorSector, deficitMenores, necesidadVitalAdultos, necesidadVitalTrabajadores,
				necesidadVitalAncianos);
		presupuesto=new Presupuesto(total, estado.getSectors());
		assertEquals(total,presupuesto.getCantidadNecesaria());
		deficitMenores = 50;
		total=calcula(personasPorSector, deficitMenores, necesidadVitalAdultos, necesidadVitalTrabajadores,
				necesidadVitalAncianos);
		presupuesto=new Presupuesto(total, estado.getSectors());
		assertTrue(total==presupuesto.getCantidadNecesaria());
		deficitMenores = 50;
		int deficitAncianos=10;		
		total=calcula(personasPorSector, deficitMenores, necesidadVitalAdultos, necesidadVitalTrabajadores, deficitAncianos);
		presupuesto=new Presupuesto(total, estado.getSectors());
		long deficit=100;
		assertEquals(total,presupuesto.getCantidadNecesaria()-deficit);
	}

	private int calcula(int personasPorSector, int necesidadVitalMenores, int necesidadVitalAdultos,
			int necesidadVitalTrabajadores, int necesidadVitalAncianos) {
		return necesidadVitalMenores*personasPorSector+necesidadVitalAdultos*personasPorSector
				+necesidadVitalAncianos*personasPorSector+necesidadVitalTrabajadores*personasPorSector;
	}

}
