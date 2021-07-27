import android.util.Log

class FBaseDatos {
    companion object{
        val arregloBFacultad = arrayListOf<BFacultad>()
        init {
            if(FBaseDeDatos.TablaFacultad != null){
                val user = FBaseDeDatos.TablaFacultad!!.consultarListaFacultad()
                user.forEachIndexed { index:Int, s ->
                    arregloBFacultad.add(BFacultad(user.get(index).id,user.get(index).nomfacultad,
                        user.get(index).materia,user.get(index).curso,user.get(index).numcurso,user.get(index).fechaingreso,user.get(index).estado))
                }
                Log.i("bdd", "${user}")
            }

        }

    }
}