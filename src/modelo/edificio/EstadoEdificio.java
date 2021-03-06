package modelo.edificio;

import modelo.jugador.Jugador;
import modelo.unidad.Unidad;

public interface EstadoEdificio {

	//Los crearArqueroDeJugador o crearAldeadnodeJugador son para el ataque, para darles la instancia de Jugador
	//la cual es su propietaria

	
	public Unidad crearArqueroDeJugador(Jugador jugador);

	public Unidad crearAldeanoDeJugador(Jugador jugador);

	public Unidad crearArmaDeAsedioDeJugador (Jugador jugador);

	public Unidad crearEspadachinDeJugador(Jugador jugador);
	
	public EstadoEdificio avanzarTurno(Edificio edificio);

	public EstadoEdificio reparar(Edificio edificio);
	
	public int getTurnosOcupado();


}