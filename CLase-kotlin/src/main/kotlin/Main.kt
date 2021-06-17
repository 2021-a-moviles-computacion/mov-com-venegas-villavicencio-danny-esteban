import java.util.*

fun main() {
    println("Hola mundo")
    // Tipo nombre = valor
    // Int edad = 12;

    // Duck Typing

    var edadProfesor = 32
    // var edadProsor: Int = 32
    var sueldoProfesor = 1.23
    edadProfesor = 23
    sueldoProfesor = 2.33

    // Mutables / Inmutables

    // MUTABLES ( RE asignar "=")
    var edadCachorro: Int = 0
    edadCachorro = 1
    edadCachorro = 2
    edadCachorro = 3

    // INMUTABLE ( NO RE asignar "=")
    val numeroCedula = 18123123123
    // numeroCedula = 123123

    // Tipos de variables (JAVA)
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()

    // Condicionales

    if (true) {
        // Verdadera
    } else {
        // Falso
    }

    // switch Estado -> S -> C -> :::::
    val estadoCivilWhen: String = "S"

    when (estadoCivilWhen) {
        ("S") -> {
            println("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"
    // condicion ? bloque-true : bloque-false

    imprimirNombre("Adrian")
    //    imprimirNombre("Adrian")
//    imprimirNombre(nombre:"Adrian") -> ESTA MAL
//    imprimirNombre()

    calcularSueldo(100.00)
    calcularSueldo(100.00, 14.00)
    calcularSueldo(100.00, 14.00, 25.00)
//    calcularSueldo()

    // Named Parameters
    // calcularSueldo(15.00,12.00,150.00)
    calcularSueldo(
        bonoEspecial = 15.00,
        // tasa = 12.00
        sueldo = 150.00,
    )

    calcularSueldo(
        tasa = 14.00,
        bonoEspecial = 30.00,
        sueldo = 1000.00
    )

    // Tipos de Arreglos

    // Arreglo Estáticos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    // arregloEstatico.add(12) -> NO TENEMOS AQUI, NO SE PUEDE MODIFICAR LOS
    //                            Elementos del arreglo

    // Arreglo Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // OPERADORES -> Sirven para los arreglos estáticos y dinámicos

    // FOR EACH -> Unit
    // Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloDinamico
        .forEach {
            // it:Int -> El valor o los valores que van a llegar a esta funcion
            //           Si solamente se recibe 1 parametro, este se va a llamar "it"
            println("Valor actual: ${it}")
        }
    println(respuestaForEach)
    arregloDinamico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    val respuestaMapDos = arregloDinamico.map { it + 15 }
//        .map { valorActual: Int ->
//            return@map valorActual + 15
//        }
    println(respuestaMap)
    println(respuestaMapDos)

    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 // Expresion Condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR ->  ANY (Alguno cumple?)
    // AND ->  ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // false

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println(respuestaReduce) // 78

    // 100
    // [12, 15, 8, 10]
    val arregloDanio = arrayListOf<Int>(12, 15, 8, 10)
    val respuestaReduceFold = arregloDanio
        .fold(
            100, // acumulado inicial
            { acumulado, valorActualIteracion ->
                return@fold acumulado - valorActualIteracion
            }
        )
    println(respuestaReduceFold)

    val vidaActual: Double = arregloDinamico
        .map { it * 2.3 } // arreglo
        .filter { it > 20 } // arreglo
        .fold(100.00, { acc, i -> acc - i }) // valor
        .also { println(it) } // ejecutar codigo extra
    println("Valor vida actual ${vidaActual}") // 3.4

    val ejemploUno = Suma(1, 2)
    // val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null, 2)
    // val ejemploDos = Suma(null,2)
    val ejemploTres = Suma(1, null)
    // val ejemploTres = Suma(1,null)
    val ejemploCuatro = Suma(null, null)
    // val ejemploCuatro = Suma(null,null)
    println(ejemploUno.sumar())
    println(ejemploDos.sumar())
    println(ejemploTres.sumar())
    println(ejemploCuatro.sumar())
    //    Suma.historialSumas
    //    Suma.agregarHistorial(1)

} // FIN bloque MAIN

// void imprimirNombre (String nombre){}
fun imprimirNombre(nombre: String): Unit {
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (Defecto)
    bonoEspecial: Double? = null, // Opcional (Null) -> nullable
): Double {
    // String -> String?
    // Int -> Int?
    // Date -> Date?
    if (bonoEspecial == null) { // Indentar -> (ctrl + a) -> (ctrl + alt + l)
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}

abstract class NumerosJava {
    protected val numeroUno: Int // Propiedad clase
    private val numeroDos: Int // Propiedad clase

    constructor(
        uno: Int,   // Parametros requeridos
        dos: Int,   // Parametros requeridos
    ) {
//        this.numeroUno = uno
//        this.numeroDos = dos
        numeroUno = uno
        numeroDos = dos
        println("Inicializar")
    }

}
// instancia.numeroUno
// instancia.numeroDos

abstract class Numeros(
    // Constructor Primario
    protected var numeroUno: Int, // Propiedad clase
    protected var numeroDos: Int,  // Propiedad clase
) {
    init { // Bloque inicio del constructor primario
        println("Inicializar")
    }
}
// instancia.numeroUno
// instancia.numeroDos

class Suma(
    // Constructor primario
    uno: Int, // Parametro requerido
    dos: Int, // Parametro requerido
) : Numeros( // Constructor "papa" (super)
    uno,
    dos
) {
    init {
        this.numeroUno
        this.numeroDos
        // X -> this.uno -> NO EXISTEN
        // X -> this.dos -> NO EXISTEN
    }

    constructor( //  Segundo constructor
        uno: Int?, // parametros
        dos: Int // parametros
    ) : this( // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    )

    constructor( //  Tercer constructor
        uno: Int, // parametros
        dos: Int? // parametros
    ) : this(
        // llamada constructor primario
        uno,
        if (dos == null) 0 else dos,
    )

    constructor( //  Cuarto constructor
        uno: Int?, // parametros
        dos: Int? // parametros
    ) : this(
        // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    )

    // public fun sumar(): Int {
    fun sumar(): Int {
        // val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    // SINGLETON
    companion object {
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            // this.historialSumas.add(valorNuevaSuma)
            historialSumas.add(valorNuevaSuma)
            println(historialSumas)
        }
    }

}