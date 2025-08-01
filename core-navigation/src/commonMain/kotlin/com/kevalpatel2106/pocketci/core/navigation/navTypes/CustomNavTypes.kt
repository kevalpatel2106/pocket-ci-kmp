package com.kevalpatel2106.pocketci.core.navigation.navTypes

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import com.kevalpatel2106.pocketci.entity.CIType
import kotlinx.serialization.json.Json

object CustomNavTypes {

    val CITypeNavType = object : NavType<CIType>(isNullableAllowed = false) {

        override fun put(bundle: SavedState, key: String, value: CIType) {
            bundle.write { putString(key, serializeAsValue(value)) }
        }

        override fun get(bundle: SavedState, key: String): CIType? {
            return parseValue(bundle.read { getString(key) })
        }

        override fun parseValue(value: String): CIType = Json.decodeFromString(value)

        override fun serializeAsValue(value: CIType): String {
            return Json.encodeToString(value)
        }
    }
}