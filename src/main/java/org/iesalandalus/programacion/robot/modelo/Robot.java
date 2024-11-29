package org.iesalandalus.programacion.robot.modelo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Robot {

    private Zona zona;
    private Orientacion orientacion;
    private Coordenada coordenada;

    public Robot() {
        zona = new Zona();
        orientacion = Orientacion.NORTE;
        coordenada = zona.getCentro();
    }

    public Robot(Zona zona) {
        setZona(zona);
        orientacion = Orientacion.NORTE;
        coordenada = zona.getCentro();
    }

    public Robot(Zona zona, Orientacion orientacion) {
        setZona(zona);
        setOrientacion(orientacion);
        coordenada = zona.getCentro();
    }

    public Robot(Zona zona, Orientacion orientacion, Coordenada coordenada) {
        setZona(zona);
        setOrientacion(orientacion);
        setCoordenada(coordenada);
    }

    public Robot(Robot robot) {
        zona = robot.zona;
        orientacion = robot.orientacion;
        coordenada = robot.coordenada;
    }

    private void setZona(Zona zona) {
        this.zona = zona;
    }

    private void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    private void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Zona getZona() {
        return zona;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void avanzar() throws OperationNotSupportedException {
        int nuevaX = coordenada.x();
        int nuevaY = coordenada.y();

        switch (orientacion) {
            case SUR -> nuevaY--;
            case ESTE -> nuevaX++;
            case NORTE -> nuevaY++;
            case OESTE -> nuevaX--;
            case NORESTE -> {nuevaX++; nuevaY++;}
            case SURESTE -> {nuevaX++; nuevaY--;}
            case NOROESTE -> {nuevaX++; nuevaY++;}
            case SUROESTE -> {nuevaY--; nuevaX--;}
        }
        try {
            setCoordenada(new Coordenada(nuevaX, nuevaY));
        } catch (IllegalArgumentException e) {
            throw new OperationNotSupportedException("No se puede avanzar más puesto que no hay más zona.");
        }
    }

    public void girarALaDerecha() {
        orientacion = switch (orientacion) {
            case NORTE -> Orientacion.NORESTE;
            case NORESTE -> Orientacion.ESTE;
            case ESTE -> Orientacion.SURESTE;
            case SURESTE -> Orientacion.SUR;
            case SUR -> Orientacion.SUROESTE;
            case SUROESTE -> Orientacion.OESTE;
            case OESTE -> Orientacion.NOROESTE;
            case NOROESTE -> Orientacion.NORTE;
        };
    }

    public void girarALaIzquierda() {
        orientacion = switch (orientacion) {
            case NORTE -> Orientacion.NOROESTE;
            case NORESTE -> Orientacion.NORTE;
            case ESTE -> Orientacion.NORESTE;
            case SURESTE -> Orientacion.ESTE;
            case SUR -> Orientacion.SURESTE;
            case SUROESTE -> Orientacion.SUR;
            case OESTE -> Orientacion.SUROESTE;
            case NOROESTE -> Orientacion.OESTE;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(zona, robot.zona) && orientacion == robot.orientacion && Objects.equals(coordenada, robot.coordenada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zona, orientacion, coordenada);
    }

    @Override
    public String toString() {
        return String.format("[zona=%s, orientacion=%s, coordenada=%s]", zona, orientacion, coordenada);
    }
}
