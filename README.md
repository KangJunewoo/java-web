# java-web
네이버 부스트코스와 함께하는 자바 웹 프로그래밍

## MySQL 관련 설정
접속은
```bash
$ mysql -hlocalhost -uroot -p
```

깃헙에 mysql 루트계정 비밀번호를 올리기엔 찜찜해 유저를 하나 만들 것이다. connectuser라는 유저가 connect123!이라는 비밀번호로 connectdb에 접속하도록 하고 싶다면 다음을 치면 된다.

```sql
CREATE DATABASE connectdb;
CREATE USER connectuser@localhost IDENTIFIED BY 'connect123!@#';
GRANT ALL PRIVILEGES ON connectdb.* TO 'connectuser'@'localhost';
FLUSH PRIVILEGES;
```

그러면 접속가지는 잘 될텐데, 만약 JDBC 실행 시
```
Authentication plugin 'caching_sha2_password'
```
와 같은 에러가 뜬다면 다음을 추가로 해주자.

```bash
$ mysql -hlocalhost -uconnectuser -p connectdb
```
로 이번엔 루트가 아닌 connectuser로 접속한 다음에

```sql
ALTER USER 'connectuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'connect123!';
```

해주면 JDBC에서도 잘 돌아갈 것이다.  
[도움을 받은 링크](https://seoulbliss.tistory.com/88)

또한 뭔가 실행은 잘 되는데, 항상 다음과 같은 오류가 떴었다.
```
MESSAGE: closing inbound before receiving peer's close_notify
```

찾아보니 db url 뒤에 ?useSSL=false를 달아주면 된다고 나와있어서 그렇게 했더 잘 되었다.
[도움을 받은 링크](https://kimmy100b.github.io/error/2020/10/02/jsp-error/)

## Servlet
.java 파일인데 express의 라우터 같은 건가보다.  
코드 보면 바로 이해 가능.

## 서블릿 생명주기
lifecycleservlet 생성 -> init 호출 -> service 호출이고, 새로고침 하면 앞에 두개 빼고 service만 호출됨.

![생명주기](https://cphinf.pstatic.net/mooc/20180124_22/1516782982944xjogH_PNG/1_5_3_ServletLifcycle.PNG)

1. WAS가 서블릿 요청을 받는다.
2. 서블릿이 메모리에 있는지 확인한다. 메모리에 없다면, 메모리에 올리고 init()을 실행한다.
3. service()를 실행한다.
4. WAS가 종료되거나 웹앱이 새롭게 갱신될 경우 destroy()를 실행한다.


## JSP
모든 jsp는 서블릿으로 바뀌어 동작한다!  다음 path에서 확인 가능.  
```
~/Documents/coding/java-web/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/work/Catalina/localhost/firstweb/org/apache/jsp
```
이 쪽 코드를 좀 더 뜯어보면
out.write("jsp에들어있던html코드한줄한줄")
이 쭈루룩 들어간 것을 확인할 수 있을 것이다.  

### 라이프싸이클
1. 브라우저가 웹서버에 jsp에 대한 요청 정보를 전달한다
2. 브라우저가 요청한 jsp가 최초로 요청했을 경우에만
* jsp로 작성된 코드가 서블릿으로 코드를 변환한다. (java 파일 생성)
* 서블릿 코드를 컴파일해서 실행가능한 바이트코드로 변환한다 (class 파일 생성)
* 서블릿 클래스를 로딩하고 인스턴스를 생성한다.
3. 서블릿이 실행되어 요청을 처리하고 응답 정보를 생성한다.

생각해보니 jsp 내용은 프론트단이기도 해서, 강의 대신 줄글로 읽고 개념 정리해도 무방할 듯 하다.

## 스프링
프레임워크 : 반제품.  
IoC 컨테이너, 트랜잭션 관리, 모듈화 잘되어있고, MVC 프레임워크 제공하고, AOP지원하고, 뭐.. 이런 것들을 제공한다고 하네.

* AOP와 Instrumentation : 이번 과정에선 공부하지 않을듯.
* Messaging : 채팅같은건가보다. 우리 과정에선 공부하지 않는다 함.
* Data Access / Integration : 많은 부분들 중 spring-jdbc, spring-tx 사용 예정. 각각 DB접근, 트랜잭션 관리에 해당하겠지. 그 외에도 spring-orm, spring-oxm, spring-jms 등이 있다. 필요할 때 하나씩 익히면 된다.
* 웹 : spring-web, spring-webmvc 다룰 예정. 그 외에도 spring-websocket, spring-webmvcportlet 등이 있다.

