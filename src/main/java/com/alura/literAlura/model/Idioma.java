package com.alura.literAlura.model;

public enum Idioma {
    INGLES("[en]"),
    ESPAÑOL("[es]"),
    FRANCES("[fr]"),
    PORTUGUES("[pt]");


    private String idiomaGutendex;

    Idioma (String idiomas ){
        this.idiomaGutendex =idiomas ;
    }


    public static Idioma fromString(String text) {
        for (Idioma idiomas : Idioma.values()) {
            if (idiomas.idiomaGutendex.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }



}
