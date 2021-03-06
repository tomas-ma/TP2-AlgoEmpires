package modelo.unidad.armaDeAsedio;

import modelo.ataque.Ataque;
import modelo.edificio.Edificio;
import modelo.jugador.Jugador;
import modelo.mapa.Posicion;
import modelo.unidad.AtacandoAUnAliadoError;
import modelo.unidad.MovimientosPorTurnoExcedidosError;
import modelo.unidad.Unidad;

public class ArmaDeAsedio extends Unidad {

	private int alcance = 5;
	private EstadoArmaDeAsedio estado;
	private int danio = 75;
	
	public ArmaDeAsedio() {
		this.vida = 150;
		this.costo = 200;
		this.ataque = new Ataque (75,0, this.alcance);
		this.estado = new ArmaDeAsedioDesmontada ();
		this.simbolo = "AS";
		
	}
	
	public ArmaDeAsedio(int fila, int columna) {
		
		super (fila, columna);
		this.vida = 150;
		this.costo = 200;
		this.ataque = new Ataque(this.danio,0, this.alcance);
		this.estado = new ArmaDeAsedioDesmontada ();
		this.cantidadDeMovimientos = 0;
		this.movimientosPermitidos = 1;
		this.simbolo = "AS";
		
	}
	
	public ArmaDeAsedio(int fila, int columna, Jugador jugadorDado) {
		
		super (fila, columna);
		this.vida = 150;
		this.costo = 200;
		this.ataque = new Ataque(this.danio,0, this.alcance);
		this.estado = new ArmaDeAsedioDesmontada ();
		this.cantidadDeMovimientos = 0;
		this.movimientosPermitidos = 1;
		this.propietario = jugadorDado;
		this.simbolo = "AS";
		
	}
	
	public ArmaDeAsedio(Jugador jugador) {
		
		this.vida = 150;
		this.costo = 200;
		this.ataque = new Ataque(this.danio,0, this.alcance);
		this.estado = new ArmaDeAsedioDesmontada ();
		this.cantidadDeMovimientos = 0;
		this.movimientosPermitidos = 1;
		this.propietario = jugador;
		this.simbolo = "AS";
	}

	@Override
	public void desplazarHasta(Posicion hasta) {
		if (this.cantidadDeMovimientos >= this.movimientosPermitidos) {
			throw new MovimientosPorTurnoExcedidosError ();	
		}
		this.posicion = estado.desplazarPosicionHasta(hasta, this.posicion);
		this.cantidadDeMovimientos++;
	}
	
	public void atacar(Edificio edificio, Posicion posicionAtacado) {
		if  (posicionableEstaEnPropietario(edificio)) {
			throw new AtacandoAUnAliadoError ();
		}
		estado.atacar(edificio, posicionAtacado, this.posicion, this.ataque);
	}

	public void atacar(Unidad unidad, Posicion posicionAtacado) {
		if  (posicionableEstaEnPropietario(unidad)) {
			throw new AtacandoAUnAliadoError ();
		}
		estado.atacar(unidad, posicionAtacado, this.posicion, this.ataque);		
	}
		
	public void montar() {	
		this.estado = new ArmaDeAsedioMontandose();
		
	}
	
	public void desarmar () {
		this.estado = new ArmaDeAsedioDesmontandose();
	}
	
	public int avanzarTurno () {	
		this.estado = this.estado.avanzarTurno();
		this.cantidadDeMovimientos = 0;
		this.cantidadDeAtaques = 0;
		return 0;
	}
	
	@Override
	protected Ataque getAtaque() {
		return this.ataque;
	}	

}
