# 맘스빌리지 Clean Architecture Multi-Module Sample

이 프로젝트는 **"맘스빌리지"** 프로젝트의 Clean Architecture 원칙을 기반으로 한 간단한 샘플입니다. Jetpack Compose를 사용하여 현대적인 UI를 구현하고, 관심사의 분리와 테스트 가능성을 높이기 위해 레이어별로 모듈을 분리했습니다.

## 1. 아키텍처 개요

```
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
```

## 2. 모듈 구조

### 🌐 Remote Module
네트워크 통신을 담당하는 최하위 레이어입니다.

```
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
```

**주요 구성 요소:**
- **API**: REST API 인터페이스 정의 및 HTTP 인터셉터
- **Model**: 네트워크 응답을 위한 DTO (Data Transfer Objects)
- **DI**: Dagger/Hilt를 통한 의존성 주입 설정
- **Impl**: 원격 데이터 소스 구현체
- **Mapper**: DTO를 Domain 엔티티로 변환

### 🗄️ Data Module
Repository 패턴을 구현하여 데이터 소스를 추상화합니다.

```
data/
└── src/main/kotlin/com/ez/data/
    ├── bound/
    │   └── flowDataResource.kt
    ├── di/
    │   └── RepositoryModule.kt
    ├── model/
    │   └── EditorMainEntity.kt
    ├── remote/
    │   └── EditorMainRemoteDataSource.kt
    ├── repository/
    │   └── EditorMainRepositoryImpl.kt
    └── DataMapper.kt
```

**주요 구성 요소:**
- **Bound**: 데이터 플로우 관리 유틸리티
- **DI**: Repository 의존성 주입 설정
- **Model**: Data Layer의 엔티티 모델
- **Remote**: 원격 데이터 소스 인터페이스
- **Repository**: Repository 패턴 구현체
- **Mapper**: Entity 변환 로직

### 🎯 Domain Module
비즈니스 로직의 핵심을 담당하는 순수 Kotlin 모듈입니다.

```
domain/
└── src/main/kotlin/com/ez/domain/
    ├── model/
    │   └── EditorMain.kt
    ├── repository/
    │   └── EditorMainRepository.kt
    ├── usecase/
    │   └── GetEditorMainUseCase.kt
    └── util/
        └── ApiResult.kt
```

**주요 구성 요소:**
- **Model**: 비즈니스 도메인 모델 (순수 Kotlin)
- **Repository**: Repository 인터페이스 정의
- **UseCase**: 비즈니스 로직 구현
- **Util**: 공통 유틸리티 (API 결과 처리 등)

### 🎨 Presentation Module
UI와 사용자 상호작용을 담당합니다.

```
presentation/
└── src/main/kotlin/com/ez/presentation/
    ├── model/
    │   └── EditorMainModel.kt
    ├── screen/
    │   └── EditorMain.kt
    ├── util/
    └── viewmodel/
        └── EditorMainViewModel.kt
```

**주요 구성 요소:**
- **Model**: UI 상태를 위한 프레젠테이션 모델
- **Screen**: Jetpack Compose UI 스크린
- **ViewModel**: UI 상태 관리 및 비즈니스 로직 연결
- **Util**: UI 관련 유틸리티

## 3. 🔧 의존성 방향

```
Presentation → Domain ← Data ← Remote
```

각 모듈은 다음과 같은 의존성 규칙을 따릅니다:
- **Presentation**: Domain 모듈에만 의존
- **Data**: Domain과 Remote 모듈에 의존
- **Domain**: 다른 모듈에 의존하지 않음 (순수 Kotlin)
- **Remote**: 독립적인 네트워크 레이어
