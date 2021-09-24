import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

fun main() {
    //Creamos las entidades, si ya están creadas simplemente te indica que el archivo ya existe
    val archivo1 = Archivo("facultad.txt")
    val archivo2 = Archivo("profesor.txt")
    menu(archivo1, archivo2)
}

fun menu(archivo1: Archivo, archivo2: Archivo) {
    println(
        "**********************Escuela politecnica Nacional**********************\n" +
                "INICIO DEL SISTEMA\n" +
                "1.Facultad\n" +
                "2.Profesor\n" +
                "3.Salir del Sistema\n" +
                "Seleccione:"
    )
    when (validarEntero()) {
        1 -> {
            println(
                "1. Registrar una Facultad\n" +
                        "2. Actualizar datos de una Facultad\n" +
                        "3. Eliminar Facultad\n" +
                        "4. Ver Registro\n" +
                        "5. Regresar\n" +
                        "Seleccione:"
            )
            when (validarEntero()) {
                1 -> {
                    val facultad= Facultad()
                    facultad.registrarFacultad(archivo1)
                    menu(archivo1, archivo2)
                }
                2 -> {
                    println("Ingrese nombre de la Facultad:")
                    val autor = validarTexto()
                    if (archivo1.buscarRegistro(autor)) {
                        println(
                            "1.Nombre\n" +
                                    "2.Materia\n" +
                                    "3.Curso\n" +
                                    "4.Número de Curso\n" +
                                    "Seleccione cambio:"
                        )
                        val indice = validarEntero()
                        var cambio = ""
                        when (indice) {
                            1 -> {
                                println("Ingrse el nombre de la Facultad:")
                                cambio = validarTexto()

                            }
                            2 -> {
                                println("Materia:")
                                cambio = validarTexto()

                            }
                            3 -> {
                                println("Curso:")
                                cambio = validarTexto()

                            }
                            4 -> {
                                println("Numero del curso:")
                                cambio = validarEntero().toString()
                            }
                            5-> {
                                println(
                                    "Curso dsiponible:\n" +
                                            "1. Sí\n" +
                                            "2. No\n" +
                                            "Seleccione:"
                                )
                                cambio = validarBooleano().toString()
                            }
                            else -> {
                                println("Opción no válida")
                                menu(archivo1, archivo2)
                            }
                        }

                        archivo1.actualizarArchivo(indice - 1, autor, cambio)
                        println("Registro actualizado con éxito")
                        menu(archivo1, archivo2)
                    } else {
                        println("Facultad no registrado")
                        menu(archivo1, archivo2)
                    }

                }
                3 -> {
                    println("Ingrese nombre de la Facultad:")
                    val autor = validarTexto()
                    if (archivo1.buscarRegistro(autor)) {
                        archivo1.eliminarRegistro(autor)
                        println("Facultad eliminado del registro exitósamente")
                        menu(archivo1, archivo2)
                    } else {
                        println("Autor no registrado")
                        menu(archivo1, archivo2)
                    }
                }
                4 -> {
                    println("-------------------------------------------------------------------------")
                    println("Facultad||Materia||Curso||Numero de curso||Curso Disponible")
                    archivo1.leerArchivos()
                    menu(archivo1, archivo2)
                }
                5 -> { menu(archivo1, archivo2)}
                else -> {
                    println("Opción no válida")
                    menu(archivo1, archivo2)
                }
            }
        }
        2 -> {
            println(
                "1. Registrar Profesor\n" +
                        "2. Actualizar datos de un profesor\n" +
                        "3. Eliminar profesor\n" +
                        "4. Ver Registro\n" +
                        "5. Regresar\n" +
                        "Seleccione:"
            )
            when (validarEntero()) {
                1 -> {
                    val profe = Profesor(archivo1,archivo2)
                    profe.registrarProfe(archivo2)
                    menu(archivo1, archivo2)
                }
                2 -> {
                    println("Ingrese nombre del Profesor:")
                    val libro = validarTexto()
                    if (archivo2.buscarRegistro(libro)) {
                        println(
                            "1.Nombre del Profesor\n" +
                                    "2.Apellido\n" +
                                    "3.Facultad\n" +
                                    "4.Edad\n" +
                                    "5.Estado\n" +
                                    "Seleccione cambio:"
                        )
                        val indice = validarEntero()
                        var cambio = ""
                        when (indice) {
                            1 -> {
                                println("Ingrese el nombre del profesor:")
                                cambio = validarTexto()
                            }
                            2 -> {
                                println("Ingrese el apellido del profesor:")
                                cambio = validarTexto()
                            }
                            3 -> {
                                println("Ingrese la Facultad:")
                                cambio = validarTexto()
                                if (!archivo1.buscarRegistro(cambio)) {
                                    println(
                                        "Facultad no registrado, desea registrarlo?\n" +
                                                "1.Sí\n" +
                                                "2.No,Cancelar"
                                    )
                                    when (validarEntero()) {
                                        1 -> {
                                            val facu= Facultad()
                                            facu.registrarFacultad(archivo1)
                                        }
                                        2 -> {
                                            println("Cancelando registro")
                                            menu(archivo1, archivo2)
                                        }
                                        else -> {
                                            println("Opción no válida")
                                            menu(archivo1, archivo2)
                                        }
                                    }

                                }
                            }
                            4 -> {
                                println("Edad:")
                                cambio = validarEntero().toString()

                            }
                            5 -> {
                                println(
                                    "Estado\n" +
                                            "1. Disponible\n" +
                                            "2. No Dispenible\n" +
                                            "Seleccione:"
                                )
                                cambio = validarBooleano().toString()
                            }

                            else -> {
                                println("Opción no válida")
                                menu(archivo1, archivo2)
                            }
                        }
                        archivo2.actualizarArchivo(indice - 1, libro, cambio)
                        println("Registro actualizado con éxito")
                        menu(archivo1, archivo2)
                    } else {
                        println("LProfesor no registrado")
                        menu(archivo1, archivo2)
                    }

                }
                3 -> {
                    println("Ingrese nombre del NOmbre del profesor:")
                    val nombre = validarTexto()
                    if (archivo2.buscarRegistro(nombre)) {
                        archivo2.eliminarRegistro(nombre)
                        println("Profesor eliminado del registro exitósamente")
                        menu(archivo1, archivo2)
                    } else {
                        println("Profesro no registrado")
                        menu(archivo1, archivo2)
                    }
                }
                4 -> {
                    println("-------------------------------------------------------------------------")
                    println("Nombre||APellido||Facultad||Edad||Estado")
                    archivo2.leerArchivos()
                    menu(archivo1, archivo2)
                }
                5 -> { menu(archivo1, archivo2)}
                else -> {
                    println("Opción no válida")
                    menu(archivo1, archivo2)
                }
            }
        }
        3 -> {
            println("Fin del sistema")
        }
        else -> {
            println("Opción no válida, seleccione nuevamente")
            menu(archivo1, archivo2)
        }
    }
}

fun validarBooleano(): Boolean {
    when (validarEntero()) {
        1 -> {
            return true
        }
        2 -> {
            return false
        }
        else -> {
            println("Opción no válida")
            return validarBooleano()
        }
    }
}


fun ingresoPorTeclado(): Scanner { //Ingreso teclado
    return Scanner(System.`in`)
}

fun validarEntero(limite: Int = 1000): Int {
    return try {
        val num = ingresoPorTeclado().nextLine().toInt() //Verifica número entero
        if (num in 1 until limite) {
            num
        } else {
            println("Ingrese nuevamente")
            validarEntero(limite)
        }
    } catch (e: java.lang.Exception) {
        println("Ingrese un valor entero mayor a cero")
        validarEntero()
    }

}

fun validarDouble(): Double {
    return try {
        val num = ingresoPorTeclado().nextLine().toDouble() //Verifica número double
        if (num > 0) num else validarDouble()
    } catch (e: java.lang.Exception) {
        println("Ingrese un valor decimal mayor a cero")
        validarDouble()
    }
}

fun validarTexto(): String {
    val ingreso = ingresoPorTeclado().nextLine()
    return if (ingreso.matches("^[\\p{L} .'-]+$".toRegex())) {
        ingreso
    } else {
        println("Ingrese solo texto")
        validarTexto()
    }
}