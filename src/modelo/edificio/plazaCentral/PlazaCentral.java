package modelo.edificio.plazaCentral;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.edificio.Edificio;
import modelo.jugador.Jugador;
import modelo.mapa.Posicion;
import modelo.unidad.Posicionable;
import modelo.unidad.Unidad;

public class PlazaCentral extends Edificio {

		   public PlazaCentral()

		   {
		        this.vida = 450;
                this.costo = 100 ;
		        this.tamanio = 4;
		        this.velocidadReparacion = 25;
		        this.vidaFull = 450;
		    }

		    //PARA QUE FUNCIONE ATAUQE

		public PlazaCentral(Jugador jugador)

		{
			this.vida = 450;
			this.costo = 100 ;
			this.tamanio = 4;
			this.velocidadReparacion = 25;
			this.vidaFull = 450;
		}


	public PlazaCentral(int desdeX, int desdeY, int hastaX, int hastaY) {
		    	this.posicionDesde = new Posicion (desdeX, desdeY);
		    	this.posicionHasta = new Posicion (hastaX, hastaY);
		        this.vida = 450;
                this.costo = 100 ;
		        this.tamanio = 4;
		        this.velocidadReparacion = 25;
		        this.vidaFull = 450;
		    	
		    }


			public PlazaCentral(int desdeX, int desdeY, int hastaX, int hastaY, Jugador jugadorDado) {
				this.posicionDesde = new Posicion (desdeX, desdeY);
				this.posicionHasta = new Posicion (hastaX, hastaY);
				this.vida = 450;
				this.costo = 100 ;
				this.tamanio = 4;
				this.velocidadReparacion = 25;
				this.vidaFull = 450;
				this.propietario = jugadorDado;

			}

		   public PlazaCentral(int turnos) {
			   super(turnos);
			   this.vida = 450;
			   this.costo = 100;
			   this.tamanio = 4;
			   this.velocidadReparacion = 25;
			   this.vidaFull = 450;
		   }


			public PlazaCentral(int turnos, Jugador jugadorDado) {
				super(turnos);
				this.vida = 450;
				this.costo = 100;
				this.tamanio = 4;
				this.velocidadReparacion = 25;
				this.vidaFull = 450;
				this.propietario = jugadorDado;

			}
		   
		   public void atacar (Posicionable posicionable) {
			   throw new PlazaCentralNoPuedeAtacarError ();
		   }
		   
		   public void atacar (Unidad unidad) {
			   throw new PlazaCentralNoPuedeAtacarError ();		   
		   }
		   
		   public void atacar (Edificio edificion) {
			   throw new PlazaCentralNoPuedeAtacarError ();
		   }
		   
		    public Unidad crearAldeano() {
		    	return estado.crearAldeano();
		    }


			public Unidad crearAldeanoconJugador() {
		return estado.crearAldeanoDeJugador(this.propietario);
	}


			public Unidad crearUnidad(char tipo) {
				return estado.crearAldeano();
			}

			public Unidad crearUnidadPropia(char tipo, Jugador jugador) {
				return estado.crearAldeanoDeJugador(this.propietario);
			}


	public boolean estaEnRangoDePosicion (Posicion posicion, int alcanceEnFila, int alcanceEnColumna) {
				
				ArrayList <Posicion> posicionesQueOcupaEdificio = new ArrayList <Posicion> ();
				for (int i = this.posicionDesde.getFila(); i < this.posicionHasta.getFila(); i++) {
					for (int j = this.posicionDesde.getColumna(); j < this.posicionHasta.getColumna(); j++) {
						Posicion posicionActual = new Posicion (i, j);
						posicionesQueOcupaEdificio.add(posicionActual);
					}
				}
				
				Iterator<Posicion> iterador = posicionesQueOcupaEdificio.iterator();
				while (iterador.hasNext ()) {
					if (iterador.next().perteneceALaCuadricula(posicion, alcanceEnFila, alcanceEnColumna)) {
						return true;
					}
				}
				
				return false;
				
			}

}
