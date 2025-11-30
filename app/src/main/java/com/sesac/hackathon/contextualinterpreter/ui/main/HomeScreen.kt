package com.sesac.hackathon.contextualinterpreter.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sesac.hackathon.contextualinterpreter.ui.components.BottomNavigationBar
import com.sesac.hackathon.contextualinterpreter.ui.components.HomeCard
import com.sesac.hackathon.contextualinterpreter.ui.theme.Blue00
import com.sesac.hackathon.contextualinterpreter.ui.theme.BlueBackground
import com.sesac.hackathon.contextualinterpreter.ui.theme.RedBackground
import com.sesac.hackathon.contextualinterpreter.ui.theme.RedC8

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "home",
                onNavigate = { route ->
                    if (route != "home") {
                        navController.navigate(route)
                    }
                }
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(58.dp)) // Top padding + status bar approx
            Text(
                text = "소리 비서: Sori",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 40.sp),
                modifier = Modifier.padding(bottom = 40.dp)
            )

            HomeCard(
                title = "환경 소리 모드",
                description = "사이렌, 경적, 비상벨과 같은 위험 소리와\n초인종, 물 끓는 소리와 같은 일상 필수 소리를 감지하는 모드입니다",
                icon = Icons.Default.Hearing,
                backgroundColor = RedBackground,
                borderColor = RedC8.copy(alpha = 0.6f),
                iconBackgroundColor = Color.White, // Placeholder, design has gradient
                onClick = { navController.navigate("sound_awareness") }
            )

            Spacer(modifier = Modifier.height(32.dp))

            HomeCard(
                title = "음성 인식 모드",
                description = "대화 내용을 실시간으로 인식하고\n상대방의 감정 톤을 분석하여 대화 맥락을 감지하는 모드입니다",
                icon = Icons.Default.RecordVoiceOver,
                backgroundColor = BlueBackground,
                borderColor = Blue00.copy(alpha = 0.6f),
                iconBackgroundColor = Color.White, // Placeholder
                onClick = { navController.navigate("voice_recognition") }
            )
        }
    }
}
