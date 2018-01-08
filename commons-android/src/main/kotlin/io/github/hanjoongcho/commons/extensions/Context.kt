package io.github.hanjoongcho.commons.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import com.simplemobiletools.commons.helpers.*
import io.github.hanjoongcho.commons.helpers.*
import io.github.hanjoongcho.commons.helpers.BaseConfig

/**
 * Created by CHO HANJOONG on 2017-12-30.
 * This code based 'Simple-Commons' package
 * You can see original 'Simple-Commons' from below link.
 * https://github.com/SimpleMobileTools/Simple-Commons
 */

val Context.baseConfig: BaseConfig get() = BaseConfig.newInstance(this)

fun Context.hasPermission(permId: Int) = ContextCompat.checkSelfPermission(this, getPermissionString(permId)) == PackageManager.PERMISSION_GRANTED

fun Context.getPermissionString(id: Int) = when (id) {
    PERMISSION_READ_STORAGE -> Manifest.permission.READ_EXTERNAL_STORAGE
    PERMISSION_WRITE_STORAGE -> Manifest.permission.WRITE_EXTERNAL_STORAGE
    PERMISSION_CAMERA -> Manifest.permission.CAMERA
    PERMISSION_RECORD_AUDIO -> Manifest.permission.RECORD_AUDIO
    PERMISSION_READ_CONTACTS -> Manifest.permission.READ_CONTACTS
    PERMISSION_WRITE_CONTACTS -> Manifest.permission.WRITE_CONTACTS
    PERMISSION_READ_CALENDAR -> Manifest.permission.READ_CALENDAR
    PERMISSION_WRITE_CALENDAR -> Manifest.permission.WRITE_CALENDAR
    PERMISSION_CALL_PHONE -> Manifest.permission.CALL_PHONE
    PERMISSION_ACCESS_FINE_LOCATION ->  Manifest.permission.ACCESS_FINE_LOCATION
    PERMISSION_ACCESS_COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
    else -> ""
}


