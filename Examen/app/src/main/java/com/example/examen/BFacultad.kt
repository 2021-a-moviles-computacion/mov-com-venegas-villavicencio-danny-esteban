import android.os.Parcel
import android.os.Parcelable

class BFacultad(
    var id: Int,
    var nomfacultad: String?,
    var materia: String?,
    var curso: String?,
    var numcurso: String?,
    var fechaingreso: String?,
    var estado: String?
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
        return "${nomfacultad} - ${curso} - ${materia}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(nomfacultad)
        parcel?.writeString(materia)
        parcel?.writeString(curso)
        parcel?.writeString(numcurso)
        parcel?.writeString(fechaingreso)
        parcel?.writeString(estado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BFacultad> {
        override fun createFromParcel(parcel: Parcel): BFacultad {
            return BFacultad(parcel)
        }

        override fun newArray(size: Int): Array<BFacultad?> {
            return arrayOfNulls(size)
        }
    }
}