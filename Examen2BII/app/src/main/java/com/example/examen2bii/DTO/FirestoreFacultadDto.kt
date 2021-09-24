package com.example.examen2bii.DTO

import android.os.Parcel
import android.os.Parcelable

class FirestoreFacultadDto (
    var id: String? ="",
    var nomfacultad: String? = "",
    var carrera: String? = "",
    var direccion: String? = "",
    var telefono: String? = "",
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString()
    ) {
    }

    override fun toString(): String {
        return "ID: ${id} -" +
                "Nombre Facultad: ${nomfacultad} -" +
                " Carrera:  ${carrera} -" +
                "Dirección: ${direccion} -" +
                "Telefono: ${telefono} "

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeString(id)
        parcel?.writeString(nomfacultad)
        parcel?.writeString(carrera)
        parcel?.writeString(direccion)
        parcel?.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreFacultadDto> {
        override fun createFromParcel(parcel: Parcel): FirestoreFacultadDto {
            return FirestoreFacultadDto(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreFacultadDto?> {
            return arrayOfNulls(size)
        }
    }
}