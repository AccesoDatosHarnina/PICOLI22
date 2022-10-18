package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Hacienda {
	private long presupuestoNecesario = 0;
	private long capital = 0;
	private HashMap<TiposSector, Sector> sectors;
	private Map<TiposSector, Float> porcentajesPorSector;

	public Hacienda(HashMap<TiposSector, Sector> sectors) {
		super();
		this.sectors = sectors;
		porcentajesPorSector = Arrays.asList(TiposSector.values()).stream().collect(Collectors.toMap((k) -> {
			return k;
		}, (v) -> {
			return 1f;
		}));
	}

	void pagar() {
		presupuestoNecesario = calcularPresupuesto(sectors);
		if (isDeficit())
			reducirPresupuestoSectores(sectors);
	}

	private boolean isDeficit() {
		return capital < presupuestoNecesario;
	}

	private long getDeficit() {
		return Math.abs(capital - presupuestoNecesario);
	}

	private void reducirPresupuestoSectores(HashMap<TiposSector, Sector> sectors) {
		TiposSector[] valuesOrdenados = { TiposSector.menores, TiposSector.ancianos, TiposSector.trabajadores };
		int index = 0;
		while (isDeficit() && index < valuesOrdenados.length) {
			reducirPresupuestoUnSector(valuesOrdenados[index], sectors.get(valuesOrdenados[index++]));
		}
	}

	private void reducirPresupuestoUnSector(TiposSector tipoSector, Sector sector) {
		long calcularPresupuesto = sector.calcularPresupuesto();
		presupuestoNecesario -= calcularReduccionSector(calcularPresupuesto, tipoSector);
	}

	private long calcularReduccionSector(long calcularPresupuesto, TiposSector tipoSector) {
		float reduccionPorcentajeSegunDeficit = ((float) calcularPresupuesto - getDeficit()) / calcularPresupuesto;
		if (reduccionPorcentajeSegunDeficit < tipoSector.getTopesReduccion())
			reduccionPorcentajeSegunDeficit = tipoSector.getTopesReduccion();
		porcentajesPorSector.put(tipoSector, reduccionPorcentajeSegunDeficit);
		return (long) (calcularPresupuesto - calcularPresupuesto * reduccionPorcentajeSegunDeficit);
	}

	private long calcularPresupuesto(HashMap<TiposSector, Sector> sectors) {
		// Esta forma se hace despues
		return sectors.values().stream().mapToLong((sector) -> {
			return sector.calcularPresupuesto();
		}).sum();
	}

	private Long getPrespuestoNecesario() {
		return this.presupuestoNecesario;
	}

	void incrementarCapital(long calcularProduccionTotal) {
		this.capital += calcularProduccionTotal;
	}

	private void setCapital(long capital2) {
		this.capital = capital2;
	}

}
