package com.akinguldere.etsturcase.ui.splash

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.akinguldere.etsturcase.BR

class SplashModel: BaseObservable() {

    @get:Bindable
    var textForScreen: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textForScreen)
        }

    @get:Bindable
    var isConnected: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.connected)
        }
}