# 섹션2. 프로젝트 환경설정
## 스프링 부트 라이브러리
    - spring-boot-starter-web
        - spring-boot-starter-tomcat: 톰캣(웹서버)
        - spring-webmvc: 스프링 웹 MVC
    - spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View) -> HTML 기반의 웹 화면을 쉽게 만들 수 있음
    - spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로킹
        - spring-boot
            - spring-core
        - spring-boot-starter-logging
            - logback, slf4j

## 테스트 라이브러리
    - spring-boot-starter-test
        - junit: 테스트 프레임워크
        - mockito: 목 라이브러리
        - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
        - spring-test: 스프링 통합 테스트 지원

## 퀴즈
    1. start.spring.io는 주로 어떤 목적으로 사용될까요?
        A: Spring Boot 프로젝트 생성

    2. Spring 프로젝트에서 Gradle 같은 빌드 도구의 주요 역할은 무엇일까요?
        A: 의존성 관리 및 빌드 자동화
        => Gradle은 프로젝트 빌드 과정을 자동화하고, 필요한 외부 라이브러리(의존성)를 효율적으로 관리해줌

    3. Spring Boot의 'Starter' 의존성은 어떤 점을 간소화해주나요?
        A: 관련 라이브러리 자동 포함 및 설정
        => Starter 의존성 하나만 추가해도 관련 라이브러리들이 자동으로 따라옴

    4. Spring Boot에 Tomcat 같은 내장 웹 서버가 포함되어 있어서 좋은 점은 무엇일까요?
        A: 별도 웹 서버 설치 불필요
        => 내장 웹 서버 덕분에 별도로 서버를 설치하고 설정할 필요가 없음

    5. 개발 시 System.out.println 대신 로깅(console.log) 프레임워크를 사용하는 주된 이유는 무엇일까요?
        A: 다양한 로그 레벨 및 파일 관리 용이
        => 메시지 중요도(레벨), 출력 위치(콘솔, 파일) 설정 등 System.out.println보다 훨씬 다양한 기능을 제공

# 섹션3. 스프링 웹 개발 기초
