# Contextual Interpreter App - Walkthrough

청각 장애인을 위한 실시간 대화 및 환경 소리 비서 어플리케이션 'Contextual Interpreter'의 안드로이드 프로토타입 구현이 완료되었습니다.

## Implemented Features

### 1. Navigation & Home Screen
- **Home Screen**: 앱 실행 시 가장 먼저 표시되는 화면입니다.
    - **환경 소리 모드 버튼**: 환경 소리 인식 화면으로 이동합니다.
    - **음성 인식 모드 버튼**: 실시간 대화 자막 화면으로 이동합니다.
- **Navigation**: Jetpack Compose Navigation을 사용하여 화면 간 전환을 매끄럽게 구현했습니다.

### 2. Environment Sound Screen
- **Radar View**: 화면 중앙에 레이더 형태의 뷰를 구현하여 소리의 방향과 거리를 시각화했습니다.
- **Sound Alerts**: 위험한 소리(사이렌, 경적 등)가 감지되면 붉은색 경고 알림을 표시합니다.
- **Mock Data**: [SoundRepository](file:///e:/Documents/Project/SeSAC/SESAC%20Hackathon%202025/app/src/main/java/com/sesac/hackathon/contextualinterpreter/data/repository/SoundRepository.kt#11-37)에서 생성된 가상의 소리 데이터가 실시간으로 표시됩니다.

### 3. Voice Recognition Screen
- **Conversation Transcription**: 화면 하단에 실시간 대화 자막이 스크롤되는 리스트를 구현했습니다.
- **Emotion Analysis**: 대화 내용과 함께 감정(행복, 놀람, 공포 등)을 텍스트로 표시합니다.
- **Mock Data**: [ConversationRepository](file:///e:/Documents/Project/SeSAC/SESAC%20Hackathon%202025/app/src/main/java/com/sesac/hackathon/contextualinterpreter/data/repository/ConversationRepository.kt#9-41)에서 생성된 가상의 대화 스크립트가 자동으로 재생됩니다.

## Verification Results

### Build Verification
- `gradlew assembleDebug` 명령을 통해 빌드가 성공적으로 완료됨을 확인했습니다.
- **Compile SDK**: 36
- **Min SDK**: 24
- **Target SDK**: 36

## How to Run

1.  **Open in Android Studio**: 프로젝트 폴더(`e:\Documents\Project\SeSAC\SESAC Hackathon 2025`)를 Android Studio에서 엽니다.
2.  **Sync Gradle**: Gradle Sync를 수행하여 의존성을 다운로드합니다.
3.  **Run**: 에뮬레이터 또는 실제 기기를 연결하고 `Run` 버튼(▶)을 클릭합니다.
4.  **Verify**:
    -   **홈 화면**에서 각 모드 버튼을 눌러 화면이 전환되는지 확인합니다.
    -   **환경 소리 모드**에서 레이더에 점들이 나타나는지 확인합니다.
    -   **음성 인식 모드**에서 대화 자막이 올라오는지 확인합니다.
