package modelo.unidad;

import modelo.ataque.Ataque;
import modelo.jugador.PlazaCentralCrearAldeanoException;
import modelo.mapa.Posicion;

public abstract class Posicionable {
	
	protected Posicion posicion;
	
	public Posicionable () {
		
		this.posicion = new Posicion ();
	}
 
    public Posicionable (int fila, int columna) {
		this.posicion = new Posicion(fila, columna);
	}
	
	public Posicionable(Posicion posicion) {
		this.posicion = posicion;
	}

	public void posicionarEnFilaColumna(int fila, int columna) {
		
		this.posicion.posicionarEnFilaColumna (fila, columna);
		
	}
	
	public void posicionarEnPosicion (Posicion otraPosicion) {
		this.posicion = otraPosicion;
	}


	public abstract int avanzarTurno();

	public abstract int getVida();

	public abstract void atacado(Ataque ataque);

	public Posicion getPosicion(){
		return this.posicion;
	}
	
	public abstract boolean estaOcupado();
	
    public Unidad crearAldeano() {
    	throw new PlazaCentralCrearAldeanoException();
    }

	public abstract boolean estaEnRangoDePosicion (Posicion posicion, int alcanceEnFila, int alcanceEnColumna);

	public abstract void desplazarHasta(Posicion hasta);

	public abstract void recibirPosicionable();


	public abstract void recibirDanio(int ataque);

}
