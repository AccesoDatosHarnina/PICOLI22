package model;

import java.util.HashMap;
import java.util.Map;

public class EstadoOM {
	public void crearEstado(Map<TiposSector, Sector> sectores, int cantidadMenores, int cantidadAdultos,
			int cantidadAncianos) {
		asignarSeres(sectores.get(TiposSector.menores), cantidadMenores, 0);
		asignarSeres(sectores.get(TiposSector.parados), cantidadAdultos, 18);
		asignarSeres(sectores.get(TiposSector.ancianos), cantidadAncianos, 65);
	}

	public void crearEstado(Map<TiposSector, Sector> sectores, int cantidadMenores, int cantidadParados,
			int cantidadAncianos, int cantidadTrabajores) {
		crearEstado(sectores, cantidadMenores, cantidadParados, cantidadAncianos);
		asignarSeres(sectores.get(TiposSector.trabajadores), cantidadTrabajores, 18);
	}

	private void asignarSeres(Sector sector, int pobladores, int edad) {
		crearSeres(sector, pobladores);
		envejecerSector(sector, edad);

	}

	private void crearSeres(Sector sector, int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			sector.addSer();
		}
	}

	private void envejecerSector(Sector sector, int edad) {
		for (int i = 0; i < edad; i++) {
			sector.envejecer();
		}
	}

}
