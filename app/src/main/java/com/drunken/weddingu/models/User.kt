package com.drunken.weddingu.models

import android.os.Parcel
import android.os.Parcelable

data class User(
    val id : String = "",
    val username : String = "",
    val email : String = "",
    val imageProfile : String = "",
    val address : String = "",
    val handphoneNumber : String = "",
    val gedungModel: Int = 0,
    val tanggal : String = "",
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(imageProfile)
        parcel.writeString(address)
        parcel.writeString(handphoneNumber)
        parcel.writeInt(gedungModel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}