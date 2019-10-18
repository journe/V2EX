package com.journey.android.v2ex

import android.app.Application
import com.journey.android.v2ex.utils.Utils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.zzhoujay.richtext.RichText

/**
 * Created by journey on 2018/1/26.
 */

class V2exApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        Logger.addLogAdapter(AndroidLogAdapter())
        RichText.initCacheDir(this)
        Utils.init(this)
    }
    companion object {
        @JvmStatic
        lateinit var instance: V2exApplication
    }
}

