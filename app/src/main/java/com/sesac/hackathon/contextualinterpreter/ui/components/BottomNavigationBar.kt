package com.sesac.hackathon.contextualinterpreter.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sesac.hackathon.contextualinterpreter.ui.theme.Black1E
import com.sesac.hackathon.contextualinterpreter.ui.theme.GrayText

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(78.dp),
        tonalElevation = 10.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { onNavigate("home") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("홈", fontSize = 14.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Black1E,
                selectedTextColor = Black1E,
                unselectedIconColor = GrayText,
                unselectedTextColor = GrayText,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "sound_awareness",
            onClick = { onNavigate("sound_awareness") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Hearing,
                    contentDescription = "Sound Awareness",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("환경 소리", fontSize = 14.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Black1E,
                selectedTextColor = Black1E,
                unselectedIconColor = GrayText,
                unselectedTextColor = GrayText,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == "voice_recognition",
            onClick = { onNavigate("voice_recognition") },
            icon = {
                Icon(
                    imageVector = Icons.Default.RecordVoiceOver,
                    contentDescription = "Voice Recognition",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("음성 인식", fontSize = 14.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Black1E,
                selectedTextColor = Black1E,
                unselectedIconColor = GrayText,
                unselectedTextColor = GrayText,
                indicatorColor = Color.Transparent
            )
        )
    }
}
