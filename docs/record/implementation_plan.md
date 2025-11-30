# Implementation Plan - Contextual Interpreter App

청각 장애인을 위한 실시간 대화 및 환경 소리 비서 어플리케이션 'Contextual Interpreter'의 안드로이드 프로토타입 구현 계획입니다.

## User Review Required

> [!IMPORTANT]
> **AI 모델 연동 제외**: 현재 단계에서는 실제 AI 모델(Whisper, SED, DOA 등)을 연동하지 않고, **Mock Data**를 사용하여 UI 및 UX 흐름을 검증하는 프로토타입을 구현합니다. 실제 모델 연동은 추후 단계로 진행합니다.

## Proposed Changes

### Project Structure
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)

### Features to Implement

#### 1. Navigation Structure
- **Home Screen**:
    - 앱 진입 화면.
    - "환경 소리 모드" 버튼 -> Environment Sound Screen으로 이동.
    - "음성 인식 모드" 버튼 -> Voice Recognition Screen으로 이동.

#### 2. Environment Sound Screen
- **Environment Sound Visualization**:
    - 소리의 방향(DOA)을 나타내는 레이더 또는 화살표 UI.
    - 감지된 소리 종류(사이렌, 초인종 등) 아이콘 및 텍스트 표시.
    - 위험도에 따른 시각적 알림 (색상 변화, 강조).

#### 3. Voice Recognition Screen
- **Conversation Transcription**:
    - 실시간 대화 자막 표시 영역.
    - 감정 분석 결과(이모지, 텍스트) 표시.
    - 대화 요약 카드.

#### 2. Data Layer (Mock)
- [SoundRepository](file:///e:/Documents/Project/SeSAC/SESAC%20Hackathon%202025/app/src/main/java/com/sesac/hackathon/contextualinterpreter/data/repository/SoundRepository.kt#11-37): 가상의 환경 소리 이벤트 생성.
- [ConversationRepository](file:///e:/Documents/Project/SeSAC/SESAC%20Hackathon%202025/app/src/main/java/com/sesac/hackathon/contextualinterpreter/data/repository/ConversationRepository.kt#9-41): 가상의 대화 스크립트 및 감정 데이터 제공.

### Directory Structure (Proposed)
```
app/src/main/java/com/sesac/hackathon/contextualinterpreter/
├── MainActivity.kt
├── ui/
│   ├── theme/ (Theme, Color, Type)
│   ├── main/ (MainScreen, MainViewModel)
│   ├── components/ (Common UI Components)
├── data/
│   ├── model/ (SoundEvent, ConversationItem)
│   ├── repository/ (MockRepository)
```

## Verification Plan

### Manual Verification
1.  **앱 실행**: 에뮬레이터 또는 실제 기기에서 앱 실행.
2.  **메인 화면 확인**:
    -   가상의 환경 소리 이벤트 발생 시 화면에 방향과 아이콘이 올바르게 표시되는지 확인.
    -   가상의 대화가 자막으로 흐르고, 감정 이모지가 표시되는지 확인.
3.  **인터랙션 확인**:
    -   알림 클릭 시 상세 정보 표시 여부 확인 (구현 범위에 따라 조정).
