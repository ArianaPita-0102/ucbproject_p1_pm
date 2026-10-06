package edu.ucb.project

import androidx.compose.ui.window.ComposeUIViewController
import edu.ucb.project.di.initKoinIos

fun MainViewController() = ComposeUIViewController(configure = { initKoinIos() }) { App() }
