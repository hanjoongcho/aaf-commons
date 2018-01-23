package io.github.hanjoongcho.commons.views

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by CHO HANJOONG on 2018-01-23.
 */

class ModalView : TextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}