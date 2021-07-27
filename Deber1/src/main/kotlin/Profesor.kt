class Profesor(archivo1: Archivo, archivo2: Archivo) {
    val separador = "||"
    val saltoLinea = "\n"
    val nombre: String
    val apellido: String
    val facultad1: String
    val edad: Int
    val disponible: Boolean
    val estado: String


    init {
        println("Ingrese el nombre del profesor:")
        nombre = validarTexto()
        println("Ingrese el apellido del profesor:")
        apellido = validarTexto()
        println("Ingrese la facultad que da clases:")
        facultad1 = validarTexto()
        if (!archivo1.buscarRegistro(facultad1)) {
            println(
                "Profesor no registrado, desea registrarlo?\n" +
                        "1.Sí\n" +
                        "2.No,Cancelar"
            )
            when (validarEntero()) {
                1 -> {
                    val registro = Facultad()
                    registro.registrarFacultad(archivo1)
                }
                2 -> {
                    println("Cancelando registro")
                    menu(archivo1, archivo2)
                }
                else -> {
                    println("Opción no válida, registre nuevamente")
                    registrarProfe(archivo2)
                }
            }

        }
        println("Edad:")
        edad = validarEntero()

        println(
            "Estado:\n" +
                    "1. Activo\n" +
                    "2. No Activo\n" +
                    "Seleccione:"
        )
        disponible = validarBooleano()
        if (disponible == true){
            estado = "Activo"
        }else{
            estado = "no Activo"
        }
    }

    fun registrarProfe(archivo2: Archivo) {
        //Verificamos si el libro no ha sido registrado previamente
        if (!archivo2.buscarRegistro(nombre)) {
            //Guardamos en el archivo
            archivo2.escritorArchivo("$nombre$separador$apellido$separador$facultad1$separador$edad$separador$estado$separador")
            println("Profesor registrado exitósamente")
        } else {
            println("El profesor ya está registrado")
        }
    }
}