package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Estado;

class EstadoTestOm {

	@Test
	void test() {
		Estado estado=new Estado();
		// 30 personas 10+10+10
		int personasPorSector=10;
		int necesidadVitalMenores=100;
		int necesidadVitalAdultos=100;
		int necesidadVitalAncianos=50;
		long total=necesidadVitalMenores*personasPorSector+necesidadVitalAdultos*personasPorSector
				+necesidadVitalAncianos*personasPorSector;
//		assertEquals(total, estado.calcularPresupuesto());			
		
	}

}
