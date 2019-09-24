package david.barbaran.savetheamazon.feature.home.di

import david.barbaran.savetheamazon.feature.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}