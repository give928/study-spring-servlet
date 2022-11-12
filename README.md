# 김영한님 스프링 MVC 스터디

- [서블릿](https://github.com/give928/study-spring-servlet)
- [타임리프](https://github.com/give928/study-spring-mvc-thymeleaf)
- [아이템 서비스](https://github.com/give928/study-spring-mvc-item-service)
- [메시지, 국제화](https://github.com/give928/study-spring-mvc-message)
- [검증](https://github.com/give928/study-spring-mvc-validation)
- [로그인, 필터, 스프링 인터셉터](https://github.com/give928/study-spring-mvc-login)
- [예외 처리와 오류 페이지](https://github.com/give928/study-spring-mvc-exception)
- [스프링 타입 컨버터](https://github.com/give928/study-spring-mvc-typeconverter)
- [파일 업로드](https://github.com/give928/study-spring-mvc-upload)

### ****웹 서버, 웹 애플리케이션 서버(WAS)****

- 웹 서버는 정적 리소스(파일), WAS는 애플리케이션 로직
- 자바는 서블릿 컨테이너 기능을 제공하면 WAS
- 서블릿 없이 자바코드를 실행하는 서버 프레임워크도 있음
  WAS는 애플리케이션 코드를 실행하는데 더 특화

### 서블릿

- WAS 작동
    - 서버 TCP/IP 대기, 소켓 연결
    - HTTP 요청 메시지 파싱
    - HTTP Method, URL 인지
    - Content-Type 확인
    - HTTP 메시지 바디 내용 파싱
        - 파라미터
    - 저장 프로세스 실행
    - 비지니스 로직 실행
        - 데이터베이스 저장 요청
    - HTTP 응답 메시지 생성
        - HTTP 시작 라인 생성
        - Header 생성
        - 메시지 바디에 HTML 생성해서 입력
    - TCP/IP 응답 전달, 소켓 종료
- HTTP 요청
    - WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체 호출
    - 개발자는 Request 객체에서 HTTP 요청 정보를 편리하게 꺼내서 사용
    - 개발자는 Response 객체에 HTTP 응답 정보를 편리하게 입력
    - WAS는 Response 객체에 담겨있는 내용으로 HTTP 응답 정보를 생성
- 서블릿 컨테이너
    - 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 함
    - 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리
    - 서블릿 객체는 **싱글톤으로 관리**
        - 고객의 요청이 올 때 마다 계속 객체를 생성하는 것은 비효율
        - 최초 로딩 시점에 서블릿 객체를 미리 만들어두고 재활용
        - 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근
        - **공유 변수 사용 주의**
        - 서블릿 컨테이너 종료시 함께 종료
    - JSP도 서블릿으로 변환 되어서 사용
    - 동시 요청을 위한 멀티 스레드 처리 지원

### 동시요청 - 멀티 스레드

- 동시 요청을 처리할 수 있다.
- 리소스(CPU, 메모리)가 허용할 때 까지 처리가능
- 하나의 쓰레드가 지연 되어도, 나머지 쓰레드는 정상 동작한다.
- 쓰레드는 생성 비용은 매우 비싸다.
- 쓰레드는 컨텍스트 스위칭 비용이 발생한다.
- 쓰레드 생성에 제한이 없다. 고객 요청이 너무 많이 오면, CPU, 메모리 임계점을 넘어서 서버가 죽을 수 있다.
- 필요한 쓰레드를 쓰레드 풀에 보관하고 관리한다.
- 쓰레드 풀에 생성 가능한 쓰레드의 최대치를 관리한다. 톰캣은 최대 200개 기본 설정 (변경 가능)
- 쓰레드가 미리 생성되어 있으므로, 쓰레드를 생성하고 종료하는 비용(CPU)이 절약되고, 응답 시간이 빠르다.
- 생성 가능한 쓰레드의 최대치가 있으므로 너무 많은 요청이 들어와도 기존 요청은 안전하게 처리할 수 있다.
- was의 주요 튜닝 포인트는 최대 스레드(max thread) 수
    - 성능 테스트로 적정 숫자 찾아야 한다.
    - 아파치 ab, 제이미터, nGrinder
- 멀티 쓰레드에 대한 부분은 WAS가 처리
- **개발자가 멀티 쓰레드 관련 코드를 신경쓰지 않아도 됨**
- 개발자는 마치 **싱글 쓰레드 프로그래밍을 하듯이 편리하게 소스 코드를 개발**
- 멀티 쓰레드 환경이므로 싱글톤 객체(서블릿, 스프링 빈)는 주의해서 사용

### HTML, HTTP API, CSR, SSR

HTTP API

- HTML이 아니라 데이터 전달
- 주로 JSON 형식 사용
- 다양한 시스템에서 호출. 앱, 웹 클라이언트, 서버 to 서버

CSR

- Client Side Rendering
- HTML 결과를 자바스크립트를 사용해 웹 브라우저에서 동적으로 생성해서 적용
- 주로 동적인 화면에 사용, 웹 환경을 마치 앱 처럼 필요한 부분부분 변경할 수 있음
- 관련기술: React, Vue.js -> 웹 프론트엔드 개발자

SSR

- Server Side Rendering
- HTML 최종 결과를 서버에서 만들어서 웹 브라우저에 전달
- 주로 정적인 화면에 사용
- 관련기술: JSP, 타임리프 **> 백엔드 개발자**

React, Vue.js를 CSR + SSR 동시에 지원하는 웹 프레임워크도 있음
SSR을 사용하더라도, 자바스크립트를 사용해서 화면 일부를 동적으로 변경 가능

### 자바 벡엔드 웹 기술 역사

- 서블릿
- JSP
- 서블릿, JSP 조합 MVC 패턴 사용
- MVC 프레임워크 춘추전국 시대 - 2000년 초 ~ 2010년 초
    - MVC 패턴 자동화
    - 스트럿츠, 웹워크, 스프링 MVC(과거 버전)
- 애너테이션 기반 스프링 MVC 등장
    - @Controller
- 스프링 부트의 등장
    - Jar로 빌드 → 빌드 배포 단순화
- 스프링 웹 기술의 분화
    - Web Servlet - Spring MVC
    - Web Reactive - Spring WebFlux
        - **특징**
            - 비동기 넌 블러킹 처리
            - 최소 쓰레드로 최대 성능(CPU 코어 수와 비슷하게 스레드 수 설정) - 쓰레드 컨텍스트 스위칭 비용 효율화
            - 함수형 스타일로 개발 - 동시처리 코드 효율화
            - 서블릿 기술 사용X- netty
        - **그런데**
            - 웹 플럭스는 기술적 난이도 매우 높음
            - 아직은 RDB 지원 부족
            - 일반 MVC의 쓰레드 모델도 충분히 빠르다.
            - 실무에서 아직 많이 사용하지는 않음 (전체 1% 이하)
- 자바 뷰 템플릿
    - JSP
        - 속도 느림, 기능 부족
    - 프리마커(Freemarker), Velocity(벨로시티)
        - 속도 문제 해결, 다양한 기능
    - 타임리프(Thymeleaf)
        - 내추럴 템플릿: HTML의 모양을 유지하면서 뷰 템플릿 적용 가능
        - 스프링 MVC와 강력한 기능 통합
        - **최선의 선택**, 단 성능은 프리마커, 벨로시티가 더 빠름

### 서블릿

```java
@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class SpringServletApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringServletApplication.class, args);
	}
}
```

```java
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
```
