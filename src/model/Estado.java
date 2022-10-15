package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Estado {
	// propiedades
	private Sector trabajadores;
	private Sector parados;
	private Sector menores;
	private Sector ancianos;
	private HashMap<TiposSector, Sector> sectors = new HashMap<TiposSector, Sector>();
	private final int produccionPorTrabajador = 400;
	private int necesidadVitalBase = 100;
	private long muertosEnPeriodo = 0;
	private long capital = 0;
	private long presupuestoNecesario = 0;
	Map<TiposSector, Float> porcentajesPorSector;

	public Estado() {
		super();
		crearSectores();
		new EstadoOM().crearEstado(sectors, 10, 10, 10, 10);
		porcentajesPorSector = Arrays.asList(TiposSector.values()).stream().collect(Collectors.toMap((k) -> {
			return k;
		}, (v) -> {
			return 1f;
		}));
	}

	// funciones a realizar
	public void gestionarPeriodo(float incremento) {
		cerrarPeriodoAnterior();
		abrirPeriodoActual(incremento);
	}

	private void cerrarPeriodoAnterior() {
		incrementarCapital(calcularProduccionTotal());
		pagar();
		envejecerPoblacion();
		muertosEnPeriodo = enterrarMuertos();
	}

	private void abrirPeriodoActual(float incremento) {
		long demandaProxima = calculaDemanda(incremento);
		long diferencia = demandaProxima - calcularProduccionTotal();
		gestionEmpleados(diferencia);
		gestionNacimientos();
	}

	public void incrementarCapital(long calcularProduccionTotal) {
		this.capital += calcularProduccionTotal;
	}

	void pagar() {
		presupuestoNecesario = calcularPresupuesto(sectors);
		if (isDeficit())
			reducirPresupuestoSectores(sectors);
	}

	private long calcularPresupuesto(HashMap<TiposSector, Sector> sectors) {
		// Esta forma se hace despues
		return sectors.values().stream().mapToLong((sector) -> {
			return sector.calcularPresupuesto();
		}).sum();
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

	private boolean isDeficit() {
		return capital < presupuestoNecesario;
	}

	private long getDeficit() {
		return Math.abs(capital - presupuestoNecesario);
	}

	private void crearSectores() {
		PresupuestoCalculable base = (seres, necesidadVitalBase) -> {
			return seres.size() * necesidadVitalBase;
		};
		sectors.put(TiposSector.menores, menores = new Sector(necesidadVitalBase, base));
		sectors.put(TiposSector.ancianos, ancianos = new Sector(necesidadVitalBase / 2, base));
		sectors.put(TiposSector.trabajadores,
				trabajadores = new Sector(necesidadVitalBase * 2, (seres, necesidadVitalBase) -> {
					return seres.size() * necesidadVitalBase * 2;
				}));
		sectors.put(TiposSector.parados, parados = new Sector(necesidadVitalBase, (seres, necesidadVitalBase) -> {
			return seres.stream().mapToInt((parado) -> {
				return ((Adulto) parado).calcularNecesidadSegunAhorros(necesidadVitalBase);
			}).sum();
		}));
	}

	private void gestionNacimientos() {

	}

	private void gestionEmpleados(long diferencia) {

	}

	private long calculaDemanda(float incremento) {
		// TODO Auto-generated method stub
		return 0;
	}

	private long enterrarMuertos() {
		return sectors.entrySet().stream().mapToLong((sector) -> {
			return sector.getValue().quitarFallecidos();
		}).sum();

	}

	private void envejecerPoblacion() {
		// Por sector
		sectors.values().stream().peek((sector) -> {
			sector.envejecer();
		});
		pasarAAdulto();
		jubilar();
	}

	private void jubilar() {
		sectors.get(TiposSector.ancianos).addAllSeres(
				sectors.get(TiposSector.trabajadores).extractSeres(TiposSector.trabajadores.getEdadComienzo()));
		sectors.get(TiposSector.ancianos)
				.addAllSeres(sectors.get(TiposSector.parados).extractSeres(TiposSector.parados.getEdadComienzo()));

	}

	private void pasarAAdulto() {
		sectors.get(TiposSector.parados)
				.addAllSeres(sectors.get(TiposSector.menores).extractSeres(TiposSector.menores.getEdadComienzo()));

	}

	private long calcularProduccionTotal() {
		return trabajadores.size() * produccionPorTrabajador;
	}

	public Map<TiposSector, Sector> getSectors() {
		return sectors;
	}

	public Long getPrespuestoNecesario() {
		return this.presupuestoNecesario;
	}

	public void setCapital(long capital2) {
		this.capital=capital2;
	}

}
