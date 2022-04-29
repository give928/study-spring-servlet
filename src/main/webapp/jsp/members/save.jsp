<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.give928.spring.servlet.domain.member.Member" %>
<%@ page import="com.give928.spring.servlet.domain.member.MemberRepository" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = Member.builder().username(username).age(age).build();
    memberRepository.save(member);
%>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%>
    </li>
    <li>username=<%=member.getUsername()%>
    </li>
    <li>age=<%=member.getAge()%>
    </li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
