package com.mansep.agenda.utils;

public class Format {
    public static String CleanRut(String rut) {
        rut = rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        rut = rut.replace(" ", "");
        return rut;
    }

}