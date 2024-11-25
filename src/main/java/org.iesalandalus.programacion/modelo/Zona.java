package org.iesalandalus.programacion.modelo;

public record Zona(int ancho, int alto) {

    public static int ANCHO_MINIMO;
    public static int ANCHO_MAXIMO;
    public static int ALTO_MINIMO;
    public static int ALTO_MAXIMO;

    private int ancho;
    private int alto;

    public Zona(int ancho, int alto) {
        ancho = this.ancho;
        alto = this.alto;
    }

    public Zona() {
        ancho = ANCHO_MINIMO;
        alto = ALTO_MINIMO;
    }
}