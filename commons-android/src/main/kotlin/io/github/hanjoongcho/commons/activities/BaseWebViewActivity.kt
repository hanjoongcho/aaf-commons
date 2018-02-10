package io.github.hanjoongcho.commons.activities

import android.os.Bundle
import io.github.hanjoongcho.commons.R
import kotlinx.android.synthetic.main.activity_web_view.*

/**
 * Created by CHO HANJOONG on 2017-11-24.
 */

open class BaseWebViewActivity : BaseSimpleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
//        webView.getSettings().setJavaScriptEnabled(true)
        webView.loadUrl(intent.getStringExtra(OPEN_URL_INFO))
        finish.setOnClickListener {
            this.onBackPressed()
        }
    }

    companion object {
        const val OPEN_URL_INFO = "open_url_info"
    }
}