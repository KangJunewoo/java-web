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
4. service()를 실행한다.
4. WAS가 종료되거나 웹앱이 새롭게 갱신될 경우 destroy()를 실행한다.





