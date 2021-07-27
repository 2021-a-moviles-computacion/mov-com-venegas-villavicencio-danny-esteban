import android.os.Parcel
import android.os.Parcelable

class BProfesor(
    var id: Int,
    var nombre: String?,
    var apellido: String?,
    var facultad: String?,
    var edad: String?,
    var estado: String?,
    var idcurso: String?
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String{
        return "${nombre} - ${apellido}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(nombre)
        parcel?.writeString(apellido)
        parcel?.writeString(facultad)
        parcel?.writeString(edad)
        parcel?.writeString(estado)
        parcel?.writeString(idcurso)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BProfesor> {
        override fun createFromParcel(parcel: Parcel): BProfesor {
            return BProfesor(parcel)
        }

        override fun newArray(size: Int): Array<BProfesor?> {
            return arrayOfNulls(size)
        }
    }

}