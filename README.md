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
[도움을 준 링크](https://seoulbliss.tistory.com/88)
