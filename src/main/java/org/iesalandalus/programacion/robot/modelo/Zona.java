package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public record Zona() {

    public static final int ANCHO_MINIMO = 10;
    public static final int ANCHO_MAXIMO = 100;
    public static final int ALTO_MINIMO = 10;
    public static final int ALTO_MAXIMO = 100;

    private static int ancho;
    private static int alto;

    public void Zona(int ancho, int alto) {
        validarAncho(ancho);
        validarAlto(alto);
    }

    public Zona() {
        this.ancho = ancho;
        this.alto = alto;
    }

    private void validarAlto(int alto) {
        if (alto > ALTO_MAXIMO || alto < ALTO_MINIMO) {
            throw new IllegalArgumentException("El alto no es válido.");
        }
    }

    private void validarAncho(int ancho) {
        if (ancho > ANCHO_MAXIMO || ancho < ANCHO_MINIMO) {
            throw new IllegalArgumentException("El ancho no es válido.");
        }
    }

    public Coordenada getCentro() {
        return new Coordenada(ancho / 2, alto / 2);
    }

    private boolean perteneceX(int x) {
        return (x >= 0 && x < ancho);
    }

    private boolean perteneceY(int y) {
        return (y >= 0 && y < alto);
    }

    public boolean pertenece(Coordenada coordenada) {
        Objects.requireNonNull(coordenada, "La coordenada no puede ser nula");
        return (perteneceX(coordenada.x()) && perteneceY(coordenada.y()));
    }
}