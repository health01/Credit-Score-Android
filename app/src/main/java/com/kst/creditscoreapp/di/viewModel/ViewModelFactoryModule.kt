package com.kst.creditscoreapp.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kst.creditscoreapp.ui.main.credit.CreditFragmentViewModel
import com.kst.creditscoreapp.viewModels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CreditFragmentViewModel::class)
    abstract fun bindFirstFragmentViewModel(viewModel: CreditFragmentViewModel): ViewModel
}