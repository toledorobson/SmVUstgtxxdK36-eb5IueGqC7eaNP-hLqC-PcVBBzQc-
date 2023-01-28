package com.akinguldere.etsturcase

import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class EtsActivity : AppCompatActivity() {

    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int): Lazy<T> =
        lazy { DataBindingUtil.setContentView(this, resId) }

}