package com.example.examen2bii.DTO
import android.os.Parcel
import android.os.Parcelable

class FirestoreProfesorDto (
    var id: String? = "",
    var cedula: String? = "",
    var nombre: String? = "",
    var telefono: String? ="",
    var fechaNacimiento: String? = "",
    var latitud: String? = "",
    var longitud: String? = "",
    var idFacultad: String? = "",
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun toString(): String {

        return "ID Profesor: ${id} -" +
                "cedula: ${cedula} -" +
                "Nombre:  ${nombre} -" +
                "Telefono: ${telefono} -" +
                "fecha de nacimiento: ${fechaNacimiento} -" +
                "ID Facultad: ${idFacultad} -" +
                "Latiud: ${latitud} -" +
                "Longitud ${longitud}"

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(cedula)
        parcel.writeString(nombre)
        parcel.writeString(telefono)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(latitud)
        parcel.writeString(longitud)
        parcel.writeString(idFacultad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreProfesorDto> {
        override fun createFromParcel(parcel: Parcel): FirestoreProfesorDto {
            return FirestoreProfesorDto(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreProfesorDto?> {
            return arrayOfNulls(size)
        }
    }
}