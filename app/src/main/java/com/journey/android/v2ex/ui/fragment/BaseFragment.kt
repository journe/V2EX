package com.journey.android.v2ex.ui.fragment

import androidx.fragment.app.Fragment
import io.realm.Realm

open class BaseFragment : Fragment() {
  val realm: Realm = Realm.getDefaultInstance()
}

