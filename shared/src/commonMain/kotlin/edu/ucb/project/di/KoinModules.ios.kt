package edu.ucb.project.di

import org.koin.mp.KoinPlatform

fun initKoinIos() {
    if (KoinPlatform.getKoinOrNull() == null) initKoin()
}
