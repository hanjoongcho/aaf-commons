package io.github.hanjoongcho.commons.activities

import android.app.ActivityManager
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.graphics.ColorUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.ViewGroup
import io.github.hanjoongcho.commons.extensions.*

/**
 * Created by CHO HANJOONG on 2017-11-25.
 * This code based 'Simple-Commons' package
 * You can see original 'Simple-Commons' from below link.
 * https://github.com/SimpleMobileTools/Simple-Commons
 */

open class BaseSimpleActivity : AppCompatActivity() {
    var actionOnPermission: ((granted: Boolean) -> Unit)? = null
    var isAskingPermissions = false
    var useDynamicTheme = true
    var isBackgroundColorFromPrimaryColor: Boolean = false
    private val GENERIC_PERM_HANDLER = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        if (useDynamicTheme) {
            setTheme(getThemeId())
//            setTheme(R.style.AppTheme_AAF)
        }

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (useDynamicTheme) {
            setTheme(getThemeId())
            if (isBackgroundColorFromPrimaryColor) {
                updateBackgroundColor(baseConfig.primaryColor)
            } else {
                updateBackgroundColor()    
            }
        }
        updateActionbarColor()
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this@BaseSimpleActivity.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        isAskingPermissions = false
        if (requestCode == GENERIC_PERM_HANDLER && grantResults.isNotEmpty()) {
            actionOnPermission?.invoke(grantResults[0] == 0)
        }
    }
    
    fun startCustomizationActivity() = startActivity(Intent(this, BaseCustomizationActivity::class.java))

    fun updateActionbarColor(color: Int = baseConfig.primaryColor) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(color))
//        supportActionBar?.title = Html.fromHtml("<font color='${color.getContrastColor().toHex()}'>${supportActionBar?.title}</font>")
        updateStatusbarColor(color)

        if (isLollipopPlus()) {
            setTaskDescription(ActivityManager.TaskDescription(null, null, color))
        }
    }

    fun updateStatusbarColor(color: Int) {
        if (isLollipopPlus()) {
            window.statusBarColor = color.darkenColor()
        }
    }

    open fun updateBackgroundColor(color: Int = baseConfig.backgroundColor) {
        val mainView: ViewGroup? = getMainViewGroup()
        mainView?.run {
            setBackgroundColor(ColorUtils.setAlphaComponent(color, getBackgroundAlpha()))
        }
    }
    
    open fun getMainViewGroup(): ViewGroup? = null
    
    open fun getBackgroundAlpha(): Int = 255
    
    fun handlePermission(permissionId: Int, callback: (granted: Boolean) -> Unit) {
        actionOnPermission = null
        if (hasPermission(permissionId)) {
            callback(true)
        } else {
            isAskingPermissions = true
            actionOnPermission = callback
            ActivityCompat.requestPermissions(this, arrayOf(getPermissionString(permissionId)), GENERIC_PERM_HANDLER)
        }
    }
}