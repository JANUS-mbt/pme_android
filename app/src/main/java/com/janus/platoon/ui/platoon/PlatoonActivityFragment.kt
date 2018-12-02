package com.janus.platoon.ui.platoon

import com.janus.platoon.R
import com.janus.platoon.base.BaseFragment
import com.janus.platoon.databinding.FragmentPlatoonBinding
import com.janus.platoon.vm.PlatoonVM


class PlatoonActivityFragment : BaseFragment<PlatoonVM, FragmentPlatoonBinding>() {
    override val getLayoutId: Int = R.layout.fragment_platoon
    override val viewModelClass = PlatoonVM::class.java
}


