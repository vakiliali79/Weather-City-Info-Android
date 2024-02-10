package com.alivakili.weather_city_info.mainActivity.dataShape


import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

import kotlinx.android.parcel.Parcelize;


@Parcelize
data class City(
    @StringRes val name:Int,
    @DrawableRes var image:Int

): Parcelable {
    constructor(dto: City):this(
        name =dto.name?:0,
        image =dto.image?:0,
    )
}