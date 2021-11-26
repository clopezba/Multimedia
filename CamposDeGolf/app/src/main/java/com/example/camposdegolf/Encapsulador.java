package com.example.camposdegolf;
/**
 * Contiene los atributos que se corresponden con los campos de la tabla en la base de datos (id y nombreCampo),
 * un constructor y sus respectivos 'getters' y 'setters'. Esto permitirá trabajar con los datos para poder
 * realizar consultas o modificaciones a la base de datos.
 */
public class Encapsulador {
        private String id;
        private String nombreCampo;

        /**
         * Constructor de la clase 'Encapsulador'. Permite crear objetos de dicha clase.
         * @param id Cadena de texto del nombre del id
         * @param nombreCampo Cadena de texto del nombre del nombreCampo
         */
        public Encapsulador(String id, String nombreCampo) {
            this.id = id;
            this.nombreCampo = nombreCampo;
        }

        /**
         * Obtiene el nombre del id
         * @return Cadena de texto con el nombre del id
         */
        public String getid() {return id;}

        /**
         * Establece el nombre del id
         * @param id Cadena de texto con el nombre del id que se desea añadir
         */
        public void setid(String id) {
            this.id = id;
        }

        /**
         * Obtiene el nombre del nombreCampo
         * @return Cadena de texto con el nombre del nombreCampo
         */
        public String getnombreCampo() {
            return nombreCampo;
        }

        /**
         * Establece el nombre del nombreCampo
         * @param nombreCampo Cadena de texto con el nombre del nombreCampo que se desea añadir
         */
        public void setnombreCampo(String nombreCampo) {
            this.nombreCampo = nombreCampo;
        }
    }

