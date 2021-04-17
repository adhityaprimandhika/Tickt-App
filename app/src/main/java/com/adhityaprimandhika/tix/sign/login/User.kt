package com.adhityaprimandhika.tix.sign.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    var email : String ?="",
    var name : String ?="",
    var password : String ?="",
    var url : String ?="",
    var username : String ?="",
    var balance : String ?=""
) : Parcelable