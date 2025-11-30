# UI 구현 워크스루

제공된 [docs/figma.html](file:///e:/Documents/Project/SeSAC/SESAC%20Hackathon%202025/docs/figma.html)을 기반으로 "소리(Sori)" 애플리케이션의 UI를 구현했습니다.

## 변경 사항

### 테마 (Theme)
- [Color.kt](file:///e:/Documents/Project/SeSAC/SESAC%20Hackathon%202025/app/src/main/java/com/sesac/hackathon/contextualinterpreter/ui/theme/Color.kt): 디자인에서 추출한 커스텀 색상(빨강, 파랑, 검정 등)을 업데이트했습니다.
- [Type.kt](file:///e:/Documents/Project/SeSAC/SESAC%20Hackathon%202025/app/src/main/java/com/sesac/hackathon/contextualinterpreter/ui/theme/Type.kt): 디자인 폰트를 모방한 타이포그래피 스타일을 업데이트했습니다.

### 컴포넌트 (Components)
- **BottomNavigationBar**: 홈, 환경 소리, 음성 인식 탭이 있는 커스텀 내비게이션 바를 구현했습니다.
- **HomeCard**: 홈 화면의 모드 선택을 위한 재사용 가능한 카드 컴포넌트를 구현했습니다.

### 화면 (Screens)
- **HomeScreen**:
    - "소리 비서: Sori" 타이틀을 표시합니다.
    - "환경 소리 모드"와 "음성 인식 모드" 카드를 보여줍니다.
- **SoundAwarenessScreen**:
    - 감지된 소리 목록을 방향, 시간, 긴급 여부와 함께 표시합니다.
    - 시각적 표시기(긴급은 빨강, 일반은 파랑)를 사용합니다.
- **VoiceRecognitionScreen**:
    - 음성 인식을 위한 채팅 인터페이스를 구현했습니다.
    - 각 메시지에 대해 감정 태그(부정, 중립, 긍정)를 표시합니다.

### 내비게이션 (Navigation)
- **AppNavigation**: 세 개의 화면 간 이동이 가능하도록 라우팅을 업데이트했습니다.

## 검증 결과

### 빌드 (Build)
- `./gradlew assembleDebug` 명령어를 실행하여 성공적으로 완료되었습니다 (Exit code 0).

### 시각적 검증 (수동)
- UI 구조가 HTML 디자인과 일치합니다.
- 색상과 레이아웃은 표준 Compose 컴포넌트를 사용하여 최대한 가깝게 구현되었습니다.
