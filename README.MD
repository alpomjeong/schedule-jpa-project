# 1. api 명세서

## 1-1 유저 api 명세서
| 기능           | method | url             | request                                                  | response                                                                                                                                                                  | 상태 코드 |
|----------------|--------|-----------------|----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|
| 유저 생성      | POST   | /api/users/     | { "username": "홍길동", "email": :"hong@example.com" }   | {   "id": 1,   "username": "홍길동",   "email": "hong@example.com",   "createdAt": "2025-04-01T12:00:00",   "updatedAt": "2025-04-01T12:00:00" }                          | 200/ok    |
| 유저 전체 조회 | GET    | /api/users      |                                                          | [   {     "id": 1,     "username": "홍길동",     "email": "hong@example.com",     "createdAt": "2025-04-01T12:00:00",     "updatedAt": "2025-04-01T12:00:00"   },   ... ] | 200/ok    |
| 유저 단건 조회 | GET    | /api/users/{id} |                                                          | {   "id": 1,   "username": "홍길동",   "email": "hong@example.com",   "createdAt": "2025-04-01T12:00:00",   "updatedAt": "2025-04-01T12:00:00" }                          | 200/ok    |
| 유저 수정      | PUT    | /api/users/{id} | {   "username": "김철수",   "email": "kim@example.com" } | {   "id": 1,   "username": "김철수",   "email": "kim@example.com",   "createdAt": "2025-04-01T12:00:00",   "updatedAt": "2025-04-01T12:00:00" }                           | 200/ok    |
| 유저 삭제      | DELETE | /api/users/{id} |                                                          | 204 No Content                                                                                                                                                            | 200/ok    |

## 1-2 일정 api 명세서
| 기능           | method | url             | request                                                              | response                                                                                                                                                                               | 상태 코드 |
|----------------|--------|-----------------|----------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|
| 일정 생성      | POST   | /api/todos      | {   "userId": 1,   "title": "스터디 하기",   "content": "JPA 복습" } | {   "id": 1,   "userId": 1,   "title": "스터디 하기",   "content": "JPA 복습",   "createdAt": "2025-04-01T12:00:00",   "updatedAt": "2025-04-01T12:00:00" }                            | 200/ok    |
| 일정 전체 조회 | GET    | /api/todos      |                                                                      | [   {     "id": 1,     "userId": 1,     "title": "스터디 하기",     "content": "JPA 복습",     "createdAt": "2025-04-01T12:00:00",     "updatedAt": "2025-04-01T12:00:00"   },   ... ] | 200/ok    |
| 일정 단건 조회 | GET    | /api/todos/{id} |                                                                      | {   "id": 1,   "userId": 1,   "title": "스터디 하기",   "content": "JPA 복습",   "createdAt": "2025-04-01T12:00:00",   "updatedAt": "2025-04-01T12:00:00" }                            | 200/ok    |
| 일정 수정      | PUT    | /api/todos/{id} | {   "title": "강의 듣기",   "content": "Spring Boot 인강 수강" }     | {   "id" : 1,   "userId" : 1,   "title": "강의 듣기",   "content": "Spring Boot 인강 수강",   "createdAt": "2025-04-01T12:00:00",   "updatedAt": "2025-04-01T12:00:00"  }              | 200/ok    |
| 일정 삭제      | DELETE | /api/todos/{id} |                                                                      | 204 No Content                                                                                                                                                                         | 200/ok    |

# 2. erd 다이어그램
![Image](https://github.com/user-attachments/assets/9eb0bea1-926a-4d9e-b9ea-490a501b0ef4)


# 3. 프로젝트 구조
📁 src
└── 📁 main                         # 애플리케이션의 실제 코드 및 리소스
├── 📁 java
│   └── 📁 com.example.schedulejpaproject
│       ├── 📄 ScheduleJpaProjectApplication.java    # 🚀 애플리케이션 시작점
│
│       ├── 📁 auth                                # 🔐 인증 관련 로직
│       │   └── 📄 LoginFilter.java                # 사용자 로그인 필터
│
│       ├── 📁 config                              # 🛠️ 웹 필터 및 전역 설정 관리
│       │   └── 📄 WebConfig.java                  # 🔐 LoginFilter를 /api/* 경로에 등록하는 설정 클래스
│
│       ├── 📁 controller                          # 🌐 API 요청 처리 컨트롤러
│       │   ├── 📄 ScheduleController.java         # 일정 관련 API
│       │   └── 📄 UserController.java             # 사용자 관련 API
│
│       ├── 📁 dto                                 # 📦 데이터 전달 객체 (DTO)
│       │   ├── 📁 schedule                        # 일정 관련 DTO
│       │   │   ├── 📄 ScheduleRequestDto.java     # 일정 요청 DTO
│       │   │   └── 📄 ScheduleResponseDto.java    # 일정 응답 DTO
│       │   └── 📁 user                            # 사용자 관련 DTO
│       │       ├── 📄 UserLoginRequestDto.java    # 로그인 요청 DTO
│       │       ├── 📄 UserRequestDto.java         # 사용자 생성/수정 요청 DTO
│       │       ├── 📄 UserResponseDto.java        # 사용자 응답 DTO
│       │       └── 📄 UserSignupRequestDto.java   # 회원가입 요청 DTO
│
│       ├── 📁 entity                              # 🧱 DB 엔티티 클래스
│       │   ├── 📄 BaseTimeEntity.java             # 생성/수정 시간 자동 관리용 추상 클래스
│       │   ├── 📄 Schedule.java                   # 일정 엔티티
│       │   └── 📄 User.java                       # 사용자 엔티티
│
│       ├── 📁 repository                          # 💾 JPA Repository 인터페이스
│       │   ├── 📄 ScheduleRepository.java         # 일정 Repository
│       │   └── 📄 UserRepository.java             # 사용자 Repository
│
│       └── 📁 service                             # 🧠 비즈니스 로직 처리
│           ├── 📁 Schedule
│           │   ├── 📄 ScheduleService.java        # 일정 서비스 인터페이스
│           │   └── 📄 ScheduleServiceImpl.java    # 일정 서비스 구현체
│           └── 📁 User
│               ├── 📄 UserService.java            # 사용자 서비스 인터페이스
│               └── 📄 UserServiceImpl.java        # 사용자 서비스 구현체
│
└── 📁 resources                                   # 📁 리소스 및 설정 파일
├── 📄 application.properties                  # 애플리케이션 전역 설정 파일
├── 📁 static                                  # 정적 파일 (HTML, JS, CSS 등)
└── 📁 templates                               # Thymeleaf 등 템플릿 파일
