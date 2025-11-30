package com.sesac.hackathon.contextualinterpreter.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sesac.hackathon.contextualinterpreter.data.model.ConversationItem
import com.sesac.hackathon.contextualinterpreter.data.model.DangerLevel
import com.sesac.hackathon.contextualinterpreter.data.model.Emotion
import com.sesac.hackathon.contextualinterpreter.data.model.SoundEvent
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(uiState.conversationItems.size) {
        if (uiState.conversationItems.isNotEmpty()) {
            listState.animateScrollToItem(uiState.conversationItems.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Top Section: Environment Radar & Alerts
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            RadarView(
                soundEvent = uiState.latestSoundEvent,
                modifier = Modifier.size(300.dp)
            )
            
            uiState.latestSoundEvent?.let { event ->
                if (event.dangerLevel == DangerLevel.HIGH) {
                    DangerAlert(event)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Section: Conversation Transcription
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "실시간 대화",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.conversationItems) { item ->
                        ConversationBubble(item)
                    }
                }
            }
        }
    }
}

@Composable
fun RadarView(
    soundEvent: SoundEvent?,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2

        // Draw Radar Circles
        drawCircle(
            color = Color.Gray.copy(alpha = 0.3f),
            radius = radius,
            style = Stroke(width = 2.dp.toPx())
        )
        drawCircle(
            color = Color.Gray.copy(alpha = 0.3f),
            radius = radius * 0.66f,
            style = Stroke(width = 2.dp.toPx())
        )
        drawCircle(
            color = Color.Gray.copy(alpha = 0.3f),
            radius = radius * 0.33f,
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw Crosshair
        drawLine(
            color = Color.Gray.copy(alpha = 0.3f),
            start = Offset(center.x, 0f),
            end = Offset(center.x, size.height),
            strokeWidth = 2.dp.toPx()
        )
        drawLine(
            color = Color.Gray.copy(alpha = 0.3f),
            start = Offset(0f, center.y),
            end = Offset(size.width, center.y),
            strokeWidth = 2.dp.toPx()
        )

        // Draw Sound Event
        soundEvent?.let { event ->
            val angleRad = Math.toRadians(event.directionAngle.toDouble() - 90) // -90 to start from top
            val distanceRatio = (event.distance / 10f).coerceIn(0.1f, 1.0f) // Normalize distance (max 10m)
            val eventRadius = radius * distanceRatio
            
            val eventX = center.x + (eventRadius * cos(angleRad)).toFloat()
            val eventY = center.y + (eventRadius * sin(angleRad)).toFloat()

            val color = when (event.dangerLevel) {
                DangerLevel.HIGH -> Color.Red
                DangerLevel.MEDIUM -> Color.Yellow
                else -> Color.Green
            }

            drawCircle(
                color = color,
                radius = 12.dp.toPx(),
                center = Offset(eventX, eventY)
            )
            
            // Draw Pulse Effect (Simple)
            drawCircle(
                color = color.copy(alpha = 0.3f),
                radius = 20.dp.toPx(),
                center = Offset(eventX, eventY)
            )
        }
    }
}

@Composable
fun DangerAlert(event: SoundEvent) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 16.dp)
            .background(Color.Red.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning",
            tint = Color.Red,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = "${event.label} 감지!",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "${String.format("%.1f", event.distance)}m 전방",
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ConversationBubble(item: ConversationItem) {
    val alignment = if (item.isUser) Alignment.End else Alignment.Start
    val backgroundColor = if (item.isUser) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (item.isUser) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (item.isUser) Arrangement.End else Arrangement.Start
        ) {
            if (!item.isUser) {
                Text(
                    text = item.speaker,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
                )
            }
        }
        
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = backgroundColor,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = item.text,
                    color = textColor,
                    style = MaterialTheme.typography.bodyLarge
                )
                
                if (item.emotion != Emotion.NEUTRAL) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "감정: ${item.emotion.name}", // Replace with Icon later
                        style = MaterialTheme.typography.labelSmall,
                        color = textColor.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
