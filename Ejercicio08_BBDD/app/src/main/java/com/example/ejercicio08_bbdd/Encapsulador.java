/**
 * Contiene los atributos que se corresponden con los campos de la tabla en la base de datos (grupo y disco),
 * un constructor y sus respectivos 'getters' y 'setters'. Esto permitirá trabajar con los datos para poder
 * realizar consultas o modificaciones a la base de datos.
 */
package com.example.ejercicio08_bbdd;

public class Encapsulador {
    private String grupo;
    private String disco;

    /**
     * Constructor de la clase 'Encapsulador'. Permite crear objetos de dicha clase.
     * @param grupo Cadena de texto del nombre del grupo
     * @param disco Cadena de texto del nombre del disco
     */
    public Encapsulador(String grupo, String disco) {
        this.grupo = grupo;
        this.disco = disco;
    }

    /**
     * Obtiene el nombre del grupo
     * @return Cadena de texto con el nombre del grupo
     */
    public String getGrupo() {return grupo;}

    /**
     * Establece el nombre del grupo
     * @param grupo Cadena de texto con el nombre del grupo que se desea añadir
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * Obtiene el nombre del disco
     * @return Cadena de texto con el nombre del disco
     */
    public String getDisco() {
        return disco;
    }

    /**
     * Establece el nombre del disco
     * @param disco Cadena de texto con el nombre del disco que se desea añadir
     */
    public void setDisco(String disco) {
        this.disco = disco;
    }
}
