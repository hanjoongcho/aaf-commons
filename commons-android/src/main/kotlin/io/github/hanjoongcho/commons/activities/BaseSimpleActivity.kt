package io.github.hanjoongcho.commons.activities

import android.app.ActivityManager
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import io.github.hanjoongcho.commons.extensions.*

import io.github.hanjoongcho.commons.extensions.getPermissionString
import io.github.hanjoongcho.commons.extensions.hasPermission

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
            updateBackgroundColor()
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

    fun updateBackgroundColor(color: Int = baseConfig.backgroundColor) {
        val mainView: ViewGroup? = getMainViewGroup()
        mainView?.run {
            setBackgroundColor(color)
            alpha = 0.7f
        }
    }
    
    open fun getMainViewGroup(): ViewGroup? = null
    
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