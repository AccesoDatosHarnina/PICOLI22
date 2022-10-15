package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ControlSer;
import model.Menor;
import model.Ser;

class SerTest {
	Ser ser;
	@BeforeEach
	void beforeEach() {
		ser=ControlSer.crearSer(50,100);
	}

	@Test
	void testMenorFallido() {
		int cantidadInsuficienteLimite=55;
		int periodos=18;
		do {
			ser.vivir(cantidadInsuficienteLimite);
			ser=ControlSer.controlarEtapas(ser,100);
		}while(periodos-->0);
		assertTrue(!ser.isAlive());
	}
	
	@Test
	void testMenorExitoso() {
		int cantidadSuficienteLimite=56;
		int periodos=18;
		do {
			ser.vivir(cantidadSuficienteLimite);
			ser=ControlSer.controlarEtapas(ser,100);
		}while(periodos-->0);
		assertTrue(ser.isAlive());
	}
	
	@Test
	void testAdultoTrabajador() {
		Ser ser=new Ser(65);
		int cantidadSuficienteLimite=56;
		int periodos=18;
		do {
			ser.vivir(cantidadSuficienteLimite);
		}while(periodos-->0);
		//ahora es adulto
		//tres casos, lo alimentamos bien y mal
		
	}

}
