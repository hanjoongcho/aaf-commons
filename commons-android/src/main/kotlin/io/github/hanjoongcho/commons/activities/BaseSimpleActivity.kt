package io.github.hanjoongcho.commons.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.hanjoongcho.commons.R

/**
 * Created by CHO HANJOONG on 2017-11-25.
 * This code based 'Simple-Commons' package
 * You can see original 'Simple-Commons' from below link.
 * https://github.com/SimpleMobileTools/Simple-Commons
 */

open class BaseSimpleActivity : AppCompatActivity() {
    var useDynamicTheme = true

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
}