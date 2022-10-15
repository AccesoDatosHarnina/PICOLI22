package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Sector {
	protected LinkedList<Ser> seres;

	public LinkedList<Ser> getSeres() {
		return seres;
	}

	protected int necesidadVital;
	private PresupuestoCalculable presupuestoCalculable;

	public Sector(int necesidadVital, PresupuestoCalculable presupuestoCalculable) {
		super();
		this.seres = new LinkedList<>();
		this.necesidadVital = necesidadVital;
		this.presupuestoCalculable = presupuestoCalculable;
	}

	public long calcularPresupuesto() {
		return seres.size() * necesidadVital;
	}

	public void envejecer() {
		seres.forEach((ser) -> {
			ser.envejecer();
		});

	}

	public int size() {
		return seres.size();
	}

	public boolean addSer() {
		return seres.add(new Ser(necesidadVital));
	}

	public int quitarFallecidos() {
		// tiene estado por lo tanto se puede usar iterador
		int cantidad = 0;
		Iterator<Ser> iterator = seres.iterator();
		while (iterator.hasNext()) {
			if (!iterator.next().isAlive()) {
				iterator.remove();
				cantidad++;
			}
		}
		return cantidad;
	}

	public List<Ser> extractSeres(int criterioEdad) {
		List<Ser> collect = seres.stream().filter((ser) -> {
			return ser.getEdad() >= criterioEdad;
		}).collect(Collectors.toList());
		seres.removeAll(collect);
		return collect;
	}

	public void addAllSeres(List<Ser> seres) {
		seres.addAll(seres);

	}

	
}
