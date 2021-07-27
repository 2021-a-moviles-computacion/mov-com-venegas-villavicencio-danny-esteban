import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import kotlin.collections.ArrayList

class SqliteFacultad (
    context: Context?,
) : SQLiteOpenHelper(
    context,
    "facultad",
    null,
    2
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                CREATE TABLE FACULTAD(
                id integer primary key autoincrement,
                nomfacultad varchar(50),
                materia varchar(50),
                curso varchar(50),
                numcurso varchar(10),
                fechaingreso varchar(10),
                estado varchar(1)
                )
            """.trimIndent()
        Log.i("bdd","Creando la tabla de FACULTAD")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearFacultadFormulario(
        nomfacultad: String,
        materia: String,
        curso: String,
        numcurso: String,
        fechaingreso: String,
        estado: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nomfacultad", nomfacultad)
        valoresAGuardar.put("materia", materia)
        valoresAGuardar.put("curso", curso)
        valoresAGuardar.put("numcurso", numcurso)
        valoresAGuardar.put("fechaingreso", fechaingreso)
        valoresAGuardar.put("estado", estado)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "FACULTAD",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return  if (resultadoEscritura.toInt() == 1) false else true
    }


    fun consultarListaFacultad(): ArrayList<BFacultad> {
        Log.i("bdd","Ingresa consultar Facultad")
        val scriptConsultarUsuario = "SELECT * FROM FACULTAD "

        val baseDatosLEctura = readableDatabase
        val resultaConsultaLectura = baseDatosLEctura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val arregloFacultad= ArrayList<BFacultad>() // si esperamos mas de una respuesta

        if(existeUsuario){
            resultaConsultaLectura.moveToFirst()
            while (!resultaConsultaLectura.isAfterLast) {
                val usuarioEncontrado = BFacultad(0,"","","","","","")

                val id = resultaConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nomfacultad = resultaConsultaLectura.getString(1) //Columna indice 1 -> ID
                val materia = resultaConsultaLectura.getString(2)
                val curso = resultaConsultaLectura.getString(3)
                val numcurso = resultaConsultaLectura.getString(4)
                val fechaingreso = resultaConsultaLectura.getString(5)//Columna indice 2 -> ID
                val estado = resultaConsultaLectura.getString(6)

//                if (id != null){
                usuarioEncontrado.id = id
                usuarioEncontrado.nomfacultad = nomfacultad
                usuarioEncontrado.materia = materia
                usuarioEncontrado.curso= curso
                usuarioEncontrado.numcurso= numcurso
                usuarioEncontrado.fechaingreso = fechaingreso
                usuarioEncontrado.estado = estado
                arregloFacultad.add(usuarioEncontrado)
                resultaConsultaLectura.moveToNext()
                Log.i("bdd","${usuarioEncontrado.nomfacultad} , ${usuarioEncontrado.curso} y ${usuarioEncontrado.numcurso}")
                //}
            }
        }

        resultaConsultaLectura.close()//Cerrar siempre las consultas
        baseDatosLEctura.close()
        return arregloFacultad

    }

    fun eliminarFacultadPorId(id:Int):Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete("FACULTAD",
                "numcurso=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarFacultadFormulario(
        nomfaucltad: String,
        materia: String,
        curso: String,
        numcurso: String,
        fechaingreso: String,
        estado: String,
        idActualizar: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nomfacultad",nomfaucltad)
        valoresActualizar.put("materia",materia)
        valoresActualizar.put("curso",curso)
        valoresActualizar.put("numcurso",numcurso)
        valoresActualizar.put("fechaingreso",fechaingreso)
        valoresActualizar.put("estado",estado)
        val resultadoActualizacion = conexionEscritura
            .update(
                "FACULTAD", // Nombre Tabla
                valoresActualizar,//Valores a actualizar
                "id=?",//Clausula Where
                arrayOf(
                    idActualizar.toString()
                ) //Parametros consultar Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

}