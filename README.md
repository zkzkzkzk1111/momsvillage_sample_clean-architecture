맘스빌리지 Clean Architecture Multi-Module Sample
이 프로젝트는 "맘스빌리지" 프로젝트 Clean Architecture 원칙을 기반으로 한 간단한 샘플입니다.

1.아키텍처 개요
┌─────────────────┐
│   Presentation  │  ← UI Layer (Compose, ViewModels)
└─────────────────┘
         ↓
┌─────────────────┐
│     Domain      │  ← Business Logic Layer (Use Cases, Entities)
└─────────────────┘
         ↓
┌─────────────────┐
│      Data       │  ← Data Layer (Repository Implementations)
└─────────────────┘
         ↓
┌─────────────────┐
│     Remote      │  ← Network Layer (API, DTOs, Network Logic)
└─────────────────┘

2.모듈 구조

🌐 Remote Module

네트워크 통신을 담당하는 최하위 레이어입니다.

remote/
└── src/main/kotlin/com/ez/remote/
├── api/
│   ├── interceptor/
│   │   └── RequestHeaderInterceptor.kt
│   └── ApiService.kt
├── model/
│   ├── EditorMainResponse.kt
│   └── Network.kt
├── di/
│   ├── NetworkModule.kt
│   └── RemoteDataSourceModule.kt
├── impl/
│   └── EditorMainRemoteDataSourceImpl.kt
└── RemoteMapper.kt

주요 구성 요소

· API: REST API 인터페이스 정의 및 HTTP 인터셉터
· Model: 네트워크 응답을 위한 DTO
· DI: Dagger/Hilt를 통한 의존성 주입 설정
· Impl: 원격 데이터 소스 구현체
· Mapper: DTO를 Domain 엔티티로 변환

🗄️ Data Module

Repository 패턴을 구현하여 데이터 소스를 추상화합니다.

data/
└── src/main/kotlin/com/ez/data/
├── repository/
│   └── EditorMainRepositoryImpl.kt
├── datasource/
│   ├── local/
│   └── remote/
└── mapper/
└── DataMapper.kt

🎯 Domain Module

비즈니스 로직의 핵심을 담당합니다.

domain/
└── src/main/kotlin/com/ez/domain/
├── entity/
│   └── EditorMain.kt
├── repository/
│   └── EditorMainRepository.kt
├── usecase/
│   └── GetEditorMainUseCase.kt
└── common/
└── Result.kt

🎨 Presentation Module

UI와 사용자 상호작용을 담당합니다.

presentation/
└── src/main/kotlin/com/ez/presentation/
├── ui/
│   ├── screen/
│   ├── component/
│   └── theme/
├── viewmodel/
│   └── EditorMainViewModel.kt
└── navigation/
└── Navigation.kt

🔧 의존성 방향

Presentation → Domain ← Data ← Remote

Presentation: Domain 모듈에만 의존
Data: Domain과 Remote 모듈에 의존
Domain: 다른 모듈에 의존하지 않음
Remote: 독립적인 네트워크 레이어
