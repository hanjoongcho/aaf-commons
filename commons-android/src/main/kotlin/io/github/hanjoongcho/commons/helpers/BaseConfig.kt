package io.github.hanjoongcho.commons.helpers

import android.content.Context

/**
 * Created by CHO HANJOONG on 2017-12-30.
 * This code based 'Simple-Commons' package
 * You can see original 'Simple-Commons' from below link.
 * https://github.com/SimpleMobileTools/Simple-Commons
 */

open class BaseConfig(context: Context) : com.simplemobiletools.commons.helpers.BaseConfig(context) {
    companion object {
        fun newInstance(context: Context) = BaseConfig(context)
    }

    var aafPatternLockProtectionOn: Boolean
        get() = prefs.getBoolean(AAF_PATTERN_LOCK_PROTECTION_ON, false)
        set(appPasswordProtectionOn) = prefs.edit().putBoolean(AAF_PATTERN_LOCK_PROTECTION_ON, appPasswordProtectionOn).apply()

    
    var aafPatternLockPauseMillis: Long
        get() = prefs.getLong(AAF_PATTERN_LOCK_PAUSE_MILLIS, 0L)
        set(aafPatternLockPauseMillis) = prefs.edit().putLong(AAF_PATTERN_LOCK_PAUSE_MILLIS, aafPatternLockPauseMillis).apply()
}