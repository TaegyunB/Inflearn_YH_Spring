# 스프링 완전 정복
## 섹션2. 프로젝트 환경설정
### 스프링 부트 라이브러리
- spring-boot-starter-web
    - spring-boot-starter-tomcat: 톰캣(웹서버)
    - spring-webmvc: 스프링 웹 MVC
- spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View) -> HTML 기반의 웹 화면을 쉽게 만들 수 있음
- spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로킹
    - spring-boot
        - spring-core
    - spring-boot-starter-logging
        - logback, slf4j

### 테스트 라이브러리
- spring-boot-starter-test
    - junit: 테스트 프레임워크
    - mockito: 목 라이브러리
    - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
    - spring-test: 스프링 통합 테스트 지원

### 퀴즈
1. start.spring.io는 주로 어떤 목적으로 사용될까요?
    A: Spring Boot 프로젝트 생성

2. Spring 프로젝트에서 Gradle 같은 빌드 도구의 주요 역할은 무엇일까요?
    A: 의존성 관리 및 빌드 자동화<br>
    => Gradle은 프로젝트 빌드 과정을 자동화하고, 필요한 외부 라이브러리(의존성)를 효율적으로 관리해줌

3. Spring Boot의 'Starter' 의존성은 어떤 점을 간소화해주나요?
    A: 관련 라이브러리 자동 포함 및 설정<br>
    => Starter 의존성 하나만 추가해도 관련 라이브러리들이 자동으로 따라옴

4. Spring Boot에 Tomcat 같은 내장 웹 서버가 포함되어 있어서 좋은 점은 무엇일까요?
    A: 별도 웹 서버 설치 불필요<br>
    => 내장 웹 서버 덕분에 별도로 서버를 설치하고 설정할 필요가 없음

5. 개발 시 System.out.println 대신 로깅(console.log) 프레임워크를 사용하는 주된 이유는 무엇일까요?
    A: 다양한 로그 레벨 및 파일 관리 용이<br>
    => 메시지 중요도(레벨), 출력 위치(콘솔, 파일) 설정 등 System.out.println보다 훨씬 다양한 기능을 제공

## 섹션3. 스프링 웹 개발 기초
### 정적 컨텐츠
- 서버에서 그대로 내려보내는 파일들, 즉 렌더링이나 처리 없이 직접 브라우저에 전달되는 리소스를 의미함
~~~
src -> resources -> static -> hello-static.html
~~~
- 파일 구조가 이렇게 되어 있으면 실행할 때 "http:///localhost:8080/hello-static.html" 로 요청보내면 됨

### MVC와 템플릿 엔진
- MVC: Model, View, Controller

~~~java
@GetMapping("hello-mvc")
public String helloMvc(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
}
~~~

- @RequestParam
    - HTTP 요청 파라미터를 컨트롤러 메서드의 매개변수로 연결해주는 Spring MVC 어노테이션
    - 클라이언트가 보낸 요청 URL에서 name=값을 찾아 String name 변수에 담음
~~~
http://localhost:8080/hello-mvc?name=태균
~~~

### API
~~~java
@GetMapping("hello-api")
@ResponseBody
public Hello helloApi(@RequestParam("name") String name) {  // public Hello는 이 메서드의 리턴 타입을 의미함
    Hello hello = new Hello();
    hello.setName(name);
    return hello;  // json 형태로 반환
    // hello 객체 반환 -> 즉 Hello 라는 자바 객체를 생성해서 반환하는 것이고, Spring은 이 객체를 JSON으로 자동 변환해서 클라이언트에 응답
}

static class Hello {
    private String name;

    public String getName() {
        return name;
    }

    public String setName() {
        this.name = name;
    }
}

~~~

- @ResponseBody
    - HTTP에서 HEAD, BODY가 나뉘는데, BODY에 데이터를 직접 넣어주겠다는 뜻
    - 리턴 객체를 View로 처리하지 않고, HTTP의 BODY(응답 본문)에 JSON으로 직렬화해서 보냄

### 퀴즈
1. 스프링 웹 개발의 세 가지 주요 방식인 정적 컨텐츠, MVC, API는 각각 어떤 결과를 주로 반환할까요?
    A: 정적 컨텐츠: 가공되지 않은 파일, MVC: 처리된 HTML, API: 데이터

2. 정적 컨텐츠 방식으로 파일을 전달할 때 서버에서 어떤 처리를 거치게 될까요?
    A: 특별한 처리 없이 파일을 그대로 전달함<br>
    => 정적 컨텐츠는 서버에서 내용을 변경하거나 처리하지 않고 요청된 파일을 그대로 브라우저에 전달하는 방식

3. MVC 패턴에서 Controller와 View를 분리하는 주된 이유는?
    A: 관심사를 분리하고 코드의 유지보수성을 높이기 위함

4. 웹 개발에서 API 방식(특히 객체 반환)은 주로 어떤 목적을 위해 사용될까요?
    A: 클라이언트(앱, 웹)나 다른 서버에 구조화된 데이터만 전달하기 위함<br>
    => API 화면 구성보다는 필요한 데이터 자체를 JSON과 같은 형식으로 클라이언트에 보내주는 데 중점을 둠

5. 스프링에서 컨트롤러 메서드가 객체를 반환하고 @ResponseBody 어노테이션이 붙어있을 때, 객체를 클라이언트에 전달 가능한 형태로 변환하는 역할을 하는 것은 무엇일까요?
    A: HTTP 메시지 컨버터 (HTTP Message Converter)<br>
    => @ResponseBody가 붙으면 뷰 리졸버 대신 HTTP 메시지 컨버터가 동작하여 객체를 JSON 등으로 변환해 응답 본문에 담아줌

## 섹션 5. 스프링 빈과 의존관계