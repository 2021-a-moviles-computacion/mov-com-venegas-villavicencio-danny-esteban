import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.examen.PActualizar

class SqliteProfesor (
    context: Context?,
) : SQLiteOpenHelper(
    context,
    "profesor",
    null,
    2
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                create table PROFESOR(
                id integer primary key autoincrement,
                nombre varchar(50),
                apellido varchar(50),
                facultad varchar(50),
                edad varchar(5),  
                estado varchar(10),
                idcurso varchar(5)
                )
            """.trimIndent()
        Log.i("bdd","Creando la tabla de PROFESOR")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearProfesor(
        nombre: String,
        apellido: String,
        facultad: String,
        edad: String,
        estado: String,
        idcurso: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("apellido", apellido)
        valoresAGuardar.put("facultad", facultad)
        valoresAGuardar.put("edad", edad)
        valoresAGuardar.put("estado", estado)
        valoresAGuardar.put("idcurso", idcurso)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "PROFESOR",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return  if (resultadoEscritura.toInt() == 1) false else true
    }

    fun consultarProfesor(): ArrayList<BProfesor> {
        val scriptConsultarUsuario = "SELECT * FROM PROFESOR"

        val baseDatosLEctura = readableDatabase
        val resultaConsultaLectura = baseDatosLEctura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val arregloUsuario = ArrayList<BProfesor>() // si esperamos mas de una respuesta

        if(existeUsuario){
            resultaConsultaLectura.moveToFirst()
            while (!resultaConsultaLectura.isAfterLast) {
                val profesorencontrado = BProfesor(0,"","","","","","")

                val id = resultaConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) //Columna indice 1 -> ID
                val apellido = resultaConsultaLectura.getString(2)
                val facultad = resultaConsultaLectura.getString(3)
                val edad = resultaConsultaLectura.getString(4)//Columna indice 2 -> ID
                val estado = resultaConsultaLectura.getString(5)
                val idcurso = resultaConsultaLectura.getString(6)

                //if (id != null){
                profesorencontrado.id = id
                profesorencontrado.nombre = nombre
                profesorencontrado.apellido = apellido
                profesorencontrado.facultad = facultad
                profesorencontrado.edad = edad
                profesorencontrado.estado = estado
                profesorencontrado.idcurso = idcurso
                arregloUsuario.add(profesorencontrado)
                resultaConsultaLectura.moveToNext()
                //}
            }
        }
        resultaConsultaLectura.close()//Cerrar siempre las consultas
        baseDatosLEctura.close()
        return arregloUsuario

    }

    fun consultarProfesorPorId(id : Int): ArrayList<BProfesor> {
        Log.i("bdd","Ingreso a consultar PROFESOR por id del curso")
        val scriptConsultarUsuario = "SELECT * FROM PROFESOR where idcurso = ${id}"

        val baseDatosLEctura = readableDatabase
        val resultaConsultaLectura = baseDatosLEctura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val arregloUsuario = ArrayList<BProfesor>() // si esperamos mas de una respuesta

        if(existeUsuario){
            resultaConsultaLectura.moveToFirst()
            while (!resultaConsultaLectura.isAfterLast) {
                val profesorencontrado = BProfesor(0,"","","","","","")

                val id = resultaConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) //Columna indice 1 -> ID
                val apellido = resultaConsultaLectura.getString(2)
                val facultad = resultaConsultaLectura.getString(3)
                val edad = resultaConsultaLectura.getString(4)//Columna indice 2 -> ID
                val estado = resultaConsultaLectura.getString(5)
                val idcurso = resultaConsultaLectura.getString(6)

                //if (id != null){
                profesorencontrado.id = id
                profesorencontrado.nombre = nombre
                profesorencontrado.apellido = apellido
                profesorencontrado.facultad = facultad
                profesorencontrado.edad = edad
                profesorencontrado.estado = estado
                profesorencontrado.idcurso = idcurso
                arregloUsuario.add(profesorencontrado)
                resultaConsultaLectura.moveToNext()
                //}
                Log.i("bdd","${profesorencontrado.nombre} y ${profesorencontrado.apellido}")
            }
        }

        resultaConsultaLectura.close()//Cerrar siempre las consultas
        baseDatosLEctura.close()
        return arregloUsuario

    }

    fun eliminarProfesorPorId(id:Int):Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete("PROFESOR",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarProfesorFormulario(
        nombre: String,
        apellido: String,
        facultad: String,
        edad: String,
        estado: String,
        idcurso: String,
        idPActualizar: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre",nombre)
        valoresActualizar.put("apellido",apellido)
        valoresActualizar.put("facultad",facultad)
        valoresActualizar.put("edad",edad)
        valoresActualizar.put("estado",estado)
        valoresActualizar.put("idcurso",idcurso)
        val resultadoActualizacion = conexionEscritura
            .update(
                "PROFESOR", // Nombre Tabla
                valoresActualizar,//Valores a actualizar
                "id=?",//Clausula Where
                arrayOf(
                    idPActualizar.toString()
                ) //Parametros consultar Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

}