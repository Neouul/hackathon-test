package com.sesac.hackathon.contextualinterpreter.ui.voice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sesac.hackathon.contextualinterpreter.ui.components.BottomNavigationBar
import com.sesac.hackathon.contextualinterpreter.ui.theme.Black1E
import com.sesac.hackathon.contextualinterpreter.ui.theme.BlueBackground
import com.sesac.hackathon.contextualinterpreter.ui.theme.GrayBackground
import com.sesac.hackathon.contextualinterpreter.ui.theme.GrayBorder
import com.sesac.hackathon.contextualinterpreter.ui.theme.GrayText

data class ChatItem(
    val message: String,
    val time: String,
    val sentiment: String, // "부정", "중립", "긍정"
    val isMe: Boolean = false // For alignment, though design shows all left aligned currently
)

@Composable
fun VoiceRecognitionScreen(navController: NavController) {
    val chatItems = listOf(
        ChatItem("안타까운 소식 들었습니다. 유감이네요.", "오후 07:36", "부정"),
        ChatItem("그 일에 대해서는 서로 오해했던 것을\n알고 잘 풀었습니다. 걱정 감사합니다.", "오후 07:37", "중립"),
        ChatItem("오해였다니 다행입니다.", "오후 07:38", "긍정")
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "voice_recognition",
                onNavigate = { route ->
                    if (route != "voice_recognition") {
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
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(BlueBackground, BlueBackground)
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .background(Color.White, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.RecordVoiceOver,
                            contentDescription = null,
                            tint = Black1E,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "음성 인식 모드",
                        style = MaterialTheme.typography.titleLarge,
                        color = Black1E
                    )
                }
            }

            // Chat List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 33.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp) // Gap between messages
            ) {
                items(chatItems) { item ->
                    ChatItemCard(item)
                }
            }
        }
    }
}

@Composable
fun ChatItemCard(item: ChatItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(123.dp)
            .background(if (item.sentiment == "중립") GrayBackground else Color.White, RoundedCornerShape(18.dp))
            .border(1.dp, GrayBorder, RoundedCornerShape(18.dp))
    ) {
        // Sentiment Tag
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 10.dp, end = 12.dp)
                .size(45.dp, 22.dp)
                .background(Color(0xFF1E1E1E).copy(alpha = 0.15f), RoundedCornerShape(9.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.sentiment,
                fontSize = 12.sp,
                color = Black1E
            )
        }

        // Icon (Placeholder black square from design)
        Box(
            modifier = Modifier
                .padding(start = 14.dp, top = 10.dp)
                .size(22.dp)
                .background(Black1E)
        )

        // Message
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.message,
                fontSize = 16.sp,
                color = Black1E,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.time,
                fontSize = 12.sp,
                color = GrayText
            )
        }
    }
}
