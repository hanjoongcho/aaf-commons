package io.github.hanjoongcho.commons.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Looper
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import com.simplemobiletools.commons.extensions.baseConfig
import com.simplemobiletools.commons.extensions.isBlackAndWhiteTheme
import com.simplemobiletools.commons.helpers.*
import com.simplemobiletools.commons.views.*
import io.github.hanjoongcho.commons.helpers.*
import io.github.hanjoongcho.commons.helpers.BaseConfig
import io.github.hanjoongcho.commons.views.ModalView

/**
 * Created by CHO HANJOONG on 2017-12-30.
 * This code based 'Simple-Commons' package
 * You can see original 'Simple-Commons' from below link.
 * https://github.com/SimpleMobileTools/Simple-Commons
 */

fun Context.isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()
fun Context.getSharedPrefs() = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

fun Context.isJellyBean1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
fun Context.isAndroidFour() = Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH
fun Context.isKitkatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
fun Context.isLollipopPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun Context.isMarshmallowPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun Context.isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun Context.isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun Context.updateTextColors(viewGroup: ViewGroup, tmpTextColor: Int = 0, tmpAccentColor: Int = 0) {
    val textColor = if (tmpTextColor == 0) baseConfig.textColor else tmpTextColor
    val backgroundColor = baseConfig.backgroundColor
    val accentColor = if (tmpAccentColor == 0) {
        if (isBlackAndWhiteTheme()) {
            Color.WHITE
        } else {
            baseConfig.primaryColor
        }
    } else {
        tmpAccentColor
    }

    val cnt = viewGroup.childCount
    (0 until cnt)
            .map { viewGroup.getChildAt(it) }
            .forEach {
                when (it) {
                    is MyTextView -> {
                        it.setColors(textColor, accentColor, backgroundColor)
                    }
                    is MyAppCompatSpinner -> it.setColors(textColor, accentColor, backgroundColor)
                    is MySwitchCompat -> it.setColors(textColor, accentColor, backgroundColor)
                    is MyCompatRadioButton -> it.setColors(textColor, accentColor, backgroundColor)
                    is MyAppCompatCheckbox -> it.setColors(textColor, accentColor, backgroundColor)
                    is MyEditText -> it.setColors(textColor, accentColor, backgroundColor)
                    is MyFloatingActionButton -> it.backgroundTintList = ColorStateList.valueOf(accentColor)
                    is MySeekBar -> it.setColors(textColor, accentColor, backgroundColor)
                    is MyButton -> it.setColors(textColor, accentColor, backgroundColor)
                    is ModalView -> it.setBackgroundColor(accentColor)
                    is ViewGroup -> updateTextColors(it, textColor, accentColor)
                }
            }
}

fun Context.updateAppViews(viewGroup: ViewGroup, tmpBackgroundColor: Int = 0) {
    val backgroundColor = if (tmpBackgroundColor == 0) baseConfig.backgroundColor else tmpBackgroundColor
    val cnt = viewGroup.childCount
    (0 until cnt)
            .map { viewGroup.getChildAt(it) }
            .forEach {
                when (it) {
                    is CardView -> {
                        it.setCardBackgroundColor(backgroundColor)
                    }
                }
            }
}

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


