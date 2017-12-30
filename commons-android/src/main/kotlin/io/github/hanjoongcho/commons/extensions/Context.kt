package io.github.hanjoongcho.commons.extensions

import android.content.Context
import io.github.hanjoongcho.commons.helpers.BaseConfig

/**
 * Created by CHO HANJOONG on 2017-12-30.
 */

val Context.baseConfig: BaseConfig get() = BaseConfig.newInstance(this)