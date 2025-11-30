package com.sesac.hackathon.contextualinterpreter.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sesac.hackathon.contextualinterpreter.ui.main.HomeScreen
import com.sesac.hackathon.contextualinterpreter.ui.sound.SoundAwarenessScreen
import com.sesac.hackathon.contextualinterpreter.ui.voice.VoiceRecognitionScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("sound_awareness") {
            SoundAwarenessScreen(navController = navController)
        }
        composable("voice_recognition") {
            VoiceRecognitionScreen(navController = navController)
        }
    }
}
