package io.github.hanjoongcho.commons.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import com.simplemobiletools.commons.extensions.baseConfig
import com.simplemobiletools.commons.extensions.isBlackAndWhiteTheme
import com.simplemobiletools.commons.extensions.launchViewIntent
import com.simplemobiletools.commons.extensions.updateTextColors
import com.simplemobiletools.commons.helpers.APP_NAME
import com.simplemobiletools.commons.helpers.APP_VERSION_NAME
import io.github.hanjoongcho.commons.R
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by CHO HANJOONG on 2017-11-25.
 * This code based 'Simple-Commons' package
 * You can see original 'Simple-Commons' from below link.
 * https://github.com/SimpleMobileTools/Simple-Commons
 */

open class BaseAboutActivity : BaseSimpleActivity() {
    private var appName = ""
    var linkColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            title = getString(R.string.about)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_cross);
        }

        appName = intent.getStringExtra(APP_NAME) ?: ""
        linkColor = if (isBlackAndWhiteTheme()) Color.WHITE else baseConfig.primaryColor
        updateTextColors(about_scrollview)
    }

    fun setupWebsite() {
        val websiteText = String.format(getString(R.string.two_string_placeholder), getString(R.string.website_label), getString(R.string.aaf_my_website))
        about_website.text = websiteText
    }

    fun setupEmail() {
        val label = getString(R.string.email_label)
        val email = getString(R.string.aaf_my_email)

        val appVersion = String.format(getString(R.string.app_version, intent.getStringExtra(APP_VERSION_NAME)))
        val deviceOS = String.format(getString(R.string.device_os), Build.VERSION.RELEASE)
        val newline = "%0D%0A"
        val separator = "___________________"
        val body = "$newline$newline$newline$separator$newline${getString(R.string.additional_info)}:$newline$appVersion$newline$deviceOS"
        val href = "$label<br><a href=\"mailto:$email?subject=$appName&body=$body\">$email</a>"
        about_email.text = Html.fromHtml(href)
        about_email.movementMethod = LinkMovementMethod.getInstance()
    }

    fun setupMoreApps() {
        about_more_apps.setOnClickListener {
            launchViewIntent("https://play.google.com/store/apps/dev?id=5999113397113629620")
        }
        about_more_apps.setTextColor(linkColor)
    }

    fun setupInvite() {
        about_invite.setOnClickListener {
            val text = String.format(getString(R.string.share_text), appName, getStoreUrl())
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_SUBJECT, appName)
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
                startActivity(Intent.createChooser(this, getString(R.string.invite_via)))
            }
        }
        about_invite.setTextColor(linkColor)
    }

    fun setupRateUs() {
//        if (baseConfig.appRunCount < 5) {
//            about_rate_us.visibility = View.GONE
//        } else {
        about_rate_us.setOnClickListener {
            try {
                launchViewIntent("market://details?id=$packageName")
            } catch (ignored: ActivityNotFoundException) {
                launchViewIntent(getStoreUrl())
            }
        }
//        }
        about_rate_us.setTextColor(linkColor)
    }

    open fun setupLicense() {}

    //    private fun setupDonate() {
//        about_donate.setOnClickListener {
//            launchViewIntent("https://simplemobiletools.github.io/donate/")
//        }
//        about_donate.setTextColor(linkColor)
//    }
//
//    private fun setupFacebook() {
//        about_facebook.setOnClickListener {
//            var link = "https://www.facebook.com/simplemobiletools"
//            try {
//                packageManager.getPackageInfo("com.facebook.katana", 0)
//                link = "fb://page/150270895341774"
//            } catch (ignored: Exception) {
//            }
//
//            launchViewIntent(link)
//        }
//    }
//
//    private fun setupGPlus() {
//        about_gplus.setOnClickListener {
//            launchViewIntent("https://plus.google.com/communities/104880861558693868382")
//        }
//    }
//
    open fun setupCopyright() {}

    fun getStoreUrl() = "https://play.google.com/store/apps/details?id=$packageName"
}