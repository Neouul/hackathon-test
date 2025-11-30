package com.sesac.hackathon.contextualinterpreter.data.repository

import com.sesac.hackathon.contextualinterpreter.data.model.ConversationItem
import com.sesac.hackathon.contextualinterpreter.data.model.Emotion
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ConversationRepository {
    private val script = listOf(
        Triple("안녕하세요! 반가워요.", Emotion.HAPPY, false),
        Triple("네, 안녕하세요. 오늘 날씨가 참 좋네요.", Emotion.NEUTRAL, true),
        Triple("맞아요. 산책하기 딱 좋은 날씨예요.", Emotion.HAPPY, false),
        Triple("혹시 저기 들리는 소리가 무슨 소리인가요?", Emotion.SURPRISED, false),
        Triple("아, 저건 구급차 사이렌 소리 같아요.", Emotion.NEUTRAL, true),
        Triple("정말요? 조심해야겠네요.", Emotion.FEAR, false),
        Triple("네, 길을 비켜주는 게 좋겠어요.", Emotion.NEUTRAL, true)
    )

    fun getConversationStream(): Flow<ConversationItem> = flow {
        var idCounter = 0L
        var index = 0
        while (true) {
            delay(2000) // Emit every 2 seconds
            
            val (text, emotion, isUser) = script[index % script.size]
            
            val item = ConversationItem(
                id = idCounter++,
                speaker = if (isUser) "나" else "상대방",
                text = text,
                emotion = emotion,
                timestamp = System.currentTimeMillis(),
                isUser = isUser
            )
            emit(item)
            index++
        }
    }
}
