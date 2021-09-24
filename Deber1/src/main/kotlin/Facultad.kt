import java.util.*

class Facultad {
    val separador = "||"
    val saltoLínea = "\n"
    val nomfacultad: String
    val materia: String
    val curso: String
    val numcurso: Int
    val disponicurso: Boolean
    val estado: String

    init {
        println("Ingrese la facultad:")
        nomfacultad = validarTexto()
        println("Ingrese la materia:")
        materia = validarTexto()
        println("Id del Curso:")
        curso = validarTexto()
        println("Numero del curso:")
        numcurso = validarEntero()
        println(
            "Curso dsiponible:\n" +
                    "1. Sí\n" +
                    "2. No\n" +
                    "Seleccione:"
        )
        disponicurso = validarBooleano()
        if (disponicurso == true){
            estado = "Disponible"
        }else{
            estado = "no Disponible"
        }
    }

    fun registrarFacultad(archivo1: Archivo) {
        //Verificamos si el autor no ha sido registrado previamente
        if (!archivo1.buscarRegistro(nomfacultad)) {
            //Guardamos en el archivo
            archivo1.escritorArchivo("$nomfacultad$separador$materia$separador$curso$separador$numcurso$separador$estado$saltoLínea")
            println("Facultad registrada exitósamente")
        } else {
            println("La facultad ya está registrado")
        }
    }
}