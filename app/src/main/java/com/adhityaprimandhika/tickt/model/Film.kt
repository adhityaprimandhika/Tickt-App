package com.adhityaprimandhika.tickt.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (
    var desc : String ?= "",
    var director : String ?= "",
    var genre : String ?= "",
    var title : String ?= "",
    var poster : String ?= "",
    var rating : String ?= ""
) : Parcelable