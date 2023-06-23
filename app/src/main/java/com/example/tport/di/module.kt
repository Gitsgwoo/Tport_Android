package com.example.tport.di

import com.example.tport.ui.PathFindingViewModel
import com.example.tport.util.ExtractData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ExtractData(get()) }
    viewModel { PathFindingViewModel(get(), get()) }
}