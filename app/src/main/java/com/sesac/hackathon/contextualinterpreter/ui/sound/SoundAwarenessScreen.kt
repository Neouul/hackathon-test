package com.sesac.hackathon.contextualinterpreter.ui.sound

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
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sesac.hackathon.contextualinterpreter.ui.components.BottomNavigationBar
import com.sesac.hackathon.contextualinterpreter.ui.theme.Black1E
import com.sesac.hackathon.contextualinterpreter.ui.theme.Blue00
import com.sesac.hackathon.contextualinterpreter.ui.theme.GrayText
import com.sesac.hackathon.contextualinterpreter.ui.theme.RedBackground
import com.sesac.hackathon.contextualinterpreter.ui.theme.RedC8
import com.sesac.hackathon.contextualinterpreter.ui.theme.White

data class SoundItem(
    val name: String,
    val timeAgo: String,
    val direction: String,
    val isUrgent: Boolean,
    val iconColor: Color,
    val borderColor: Color
)

@Composable
fun SoundAwarenessScreen(navController: NavController) {
    val soundItems = listOf(
        SoundItem("초인종", "9초 전", "왼쪽 뒤 5m", false, Blue00, Blue00),
        SoundItem("경적", "36초 전", "오른쪽 앞 17m", true, RedC8, RedC8),
        SoundItem("사이렌", "1시간 전", "왼쪽 뒤 5m", true, RedC8, RedC8)
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "sound_awareness",
                onNavigate = { route ->
                    if (route != "sound_awareness") {
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
                                colors = listOf(RedBackground, RedBackground)
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
                            imageVector = Icons.Default.Hearing,
                            contentDescription = null,
                            tint = Black1E,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "환경 소리 모드",
                        style = MaterialTheme.typography.titleLarge,
                        color = Black1E
                    )
                }
            }

            // List Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 33.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "감지된 소리",
                    style = MaterialTheme.typography.titleMedium,
                    color = Black1E
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(45.dp, 22.dp)
                            .background(Color(0xFF1E1E1E).copy(alpha = 0.15f), RoundedCornerShape(9.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${soundItems.size}개",
                            fontSize = 12.sp,
                            color = Black1E
                        )
                    }
                }
            }

            // List
            LazyColumn(
                modifier = Modifier.padding(horizontal = 33.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(soundItems) { item ->
                    SoundItemCard(item)
                }
            }
        }
    }
}

@Composable
fun SoundItemCard(item: SoundItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .border(1.dp, item.borderColor.copy(alpha = 0.6f), RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(18.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(51.dp)
                    .background(item.iconColor.copy(alpha = 0.1f), RoundedCornerShape(50))
                    .border(3.dp, item.borderColor.copy(alpha = 0.6f), RoundedCornerShape(50)),
                contentAlignment = Alignment.Center
            ) {
                 Icon(
                    imageVector = if (item.isUrgent) Icons.Default.Warning else Icons.Default.Hearing,
                    contentDescription = null,
                    tint = item.iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(24.dp))

            // Info
            Column {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    color = Black1E
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${item.direction}",
                    fontSize = 12.sp,
                    color = GrayText
                )
                Text(
                    text = "${item.timeAgo}",
                    fontSize = 12.sp,
                    color = GrayText
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Tag
            if (item.isUrgent) {
                Box(
                    modifier = Modifier
                        .size(45.dp, 22.dp)
                        .background(RedC8.copy(alpha = 0.6f), RoundedCornerShape(9.dp))
                        .align(Alignment.Top)
                        .padding(top = 14.dp), // Adjust alignment to match design visual
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "긴급",
                        fontSize = 12.sp,
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                 Box(
                    modifier = Modifier
                        .size(45.dp, 22.dp)
                        .background(Color(0xFF1E1E1E).copy(alpha = 0.15f), RoundedCornerShape(9.dp))
                        .align(Alignment.Top)
                        .padding(top = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "일상",
                        fontSize = 12.sp,
                        color = Black1E
                    )
                }
            }
        }
    }
}
