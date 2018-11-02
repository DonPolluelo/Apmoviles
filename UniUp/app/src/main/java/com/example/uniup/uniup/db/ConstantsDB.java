package com.example.uniup.uniup.db;

public class ConstantsDB {
    //General
    public static final String DB_NAME = "uniup.db";
    public static final int DB_VERSION = 1;
    public final static String DATABASE_PATH = "/data/data/com.example.uniup.uniup/databases/";


    //TABLA UNIVERSIDAD
    public static final String TABLA_UNIVERSIDAD = "Universidad";

    public static final String ID_UNIVERSIDAD = "id_universidad";
    public static final String NOMBRE_UNIVERSIDAD = "nombre";

    //TABLA CARRERA
    public static final String TABLA_CARRERA = "Carrera";

    public static final String ID_CARRERA = "id_carrera";
    public static final String NOMBRE_CARRERA = "nombre";

    //TABLA RAMO
    public static final String TABLA_RAMO = "Ramo";

    public static final String ID_RAMO = "id_ramo";
    public static final String NOMBRE_RAMO = "nombre";
    public static final String NOTA_FINAL = "nota_final";

    //TABLA PRERREQUISITOS
    public static final String TABLA_PRERREQUISITOS = "Prerrequisitos";

    public static final String ID_RAMO1 = "id_ramo1";
    public static final String ID_RAMO2 = "id_ramo2";

    //TABLA LINKSDEUNIVERSIDAD
    public static final String TABLA_LINKSDEUNIVERSIDAD = "LinksDeUniversidad";


    //TABLA HORARIO
    public static final String TABLA_HORARIO = "Horario";

    public static final String DIA = "dia";
    public static final String HORA_INICIO = "hora_inicio";
    public static final String HORA_TERMINO = "hora_termino";

    //TABLA EVALUACION
    public static final String TABLA_EVALUACION = "Evaluacion";

    public static final String ID_EVALUACION = "id_evaluacion";
    public static final String NOMBRE_EVALUACION = "nombre";
    public static final String NOTA = "nota";
    public static final String PORCENTAJE = "porcentaje";

    //TABLA LINKS
    public static final String TABLA_LINKS = "Links";

    public static final String ID_LINK = "id_link";
    public static final String NOMBRE_LINK = "nombre";
    public static final String URL = "url";

    //TABLA SEMESTRE
    public static final String TABLA_SEMESTRE = "Semestre";

    public static final String ID_SEMESTRE = "id_semestre";
    public static final String NOMBRE_SEMESTRE = "nombre";

    //TABLA RAMOSDELSEMESTRE
    public static final String TABLA_RAMOSDELSEMESTRE = "RamosDelSemestre";

}
