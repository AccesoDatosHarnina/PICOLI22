package model;

public enum TiposSector {
	menores(.55f,0),ancianos(.3f,65),trabajadores(1f,18),parados(0,18);
	public int getEdadComienzo() {
		return edadComienzo;
	}


	private float topesReduccion;
	private int edadComienzo;

	
	private TiposSector(float topesReduccion, int edadComienzo) {
		this.topesReduccion = topesReduccion;
		this.edadComienzo = edadComienzo;
	}


	public float getTopesReduccion() {
		return topesReduccion;
	}

	
	
}
