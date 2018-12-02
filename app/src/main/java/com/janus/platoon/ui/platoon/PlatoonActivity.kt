package com.janus.platoon.ui.platoon

import android.os.Bundle
import com.janus.platoon.R
import com.janus.platoon.base.BaseActivity
import com.janus.platoon.databinding.ActivityPlatoonBinding

class PlatoonActivity : BaseActivity<ActivityPlatoonBinding>() {
    override val layoutRes: Int = R.layout.activity_platoon
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) { // to prevent recreation of fragment, vm, etc..
            loadFragment(R.id.container, PlatoonActivityFragment(), false)
        }
    }
}