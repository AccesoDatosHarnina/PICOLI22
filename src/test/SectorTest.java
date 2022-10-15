package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import model.Estado;
import model.Sector;
import model.TiposSector;

class SectorTest {

	@Test
	void testMenores() {
		Estado estado = new Estado();
		Map<TiposSector, Sector> sectors = estado.getSectors();
		sectors.entrySet().stream().forEach((entry)->{
			System.out.print(entry.getKey().toString()+" ");
			System.out.println(entry.getValue().size());
		});
		
		assertTrue(sectors.get(TiposSector.parados).getSeres().stream()
				.filter((ser)->{return ser.getEdad()<18;}).count()<1);
	}

}
