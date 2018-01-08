package io.github.hanjoongcho.commons.activities

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.hanjoongcho.commons.R
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
//            setTheme(getThemeId())
            setTheme(R.style.AppTheme_AAF)
        }

        super.onCreate(savedInstanceState)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this@BaseSimpleActivity.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        isAskingPermissions = false
        if (requestCode == GENERIC_PERM_HANDLER && grantResults.isNotEmpty()) {
            actionOnPermission?.invoke(grantResults[0] == 0)
        }
    }
}