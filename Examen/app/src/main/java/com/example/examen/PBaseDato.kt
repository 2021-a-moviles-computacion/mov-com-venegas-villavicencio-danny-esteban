class PBaseDato() {

    companion object{
        val arregloBProfesor = arrayListOf<BProfesor>()
        init {
            if(PBaseDeDatos.TablaProfesro != null){
                val user = PBaseDeDatos.TablaProfesro!!.consultarProfesor()
                user.forEachIndexed { index:Int, s ->
                    arregloBProfesor.add(BProfesor(user.get(index).id,user.get(index).nombre,
                        user.get(index).apellido,user.get(index).facultad,user.get(index).edad,
                        user.get(index).estado,user.get(index).idcurso))
                }
            }
        }
    }
}