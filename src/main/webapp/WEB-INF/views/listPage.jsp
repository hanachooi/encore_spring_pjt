<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글목록 페이지</title>
    <link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../plugin/mCustomScrollbar/jquery.mCustomScrollbar.min.css" />

</head>
<body>

<div class="fixed-navbar">
    <div class="pull-left">
        <h1 class="page-title">Hanwha SW Camp 5th</h1>
    </div>
    <div class="pull-right">
        <h4 class="page-title">${loginUser.name}님 로그인</h4>
        <button class="btn btn-warning btn-sm"><a href="https://kauth.kakao.com/oauth/logout?client_id=fb0e7d80d7a723a4a8d4b65e6afe125d&logout_redirect_uri=http://localhost:8888/user/kakao_logout.hanwha">로그아웃</a></button>
    </div>
</div>

<div id="wrapper">
    <div class="main-content">
        <div class="row row-inline-block small-spacing">
            <div class="col-xs-12">
                <div class="box-content">



                    <!--  content -->
                    <%-- <form id="searchForm" class="searchform js__toggle active pull-right" onsubmit="return false;" autocomplete="off">
                    <input type="search" class="input-search" placeholder="Search..." />
                    <button type="button" class="mdi mdi-magnify button-search"><i class="fa fa-search" aria-hidden="true"></i></button>
                    </form> --%>
                    <!--  content -->
                    <div id="adv-search" class="input-group">
                        <input type="search" id="mainKeyword"
                               class="form-control"
                               onkeyup="document.getElementById()"
                               placeholder="키워드를 입력해 주세요." />
                        <div class="input-group-btn">
                            <div class="btn-group" role="group">
                                <div class="dropdown dropdown-lg">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="caret"></span></button>
                                    <div class="dropdown-menu dropdown-menu-right" role="menu">
                                        <!--/* 검색 form */-->
                                        <form id="searchForm" class="form-horizontal" onsubmit="return false;" autocomplete="off">
                                            <div class="form-group">
                                                <label for="searchType">검색 유형</label>
                                                <select id="searchType" name="searchType" class="form-control">
                                                    <option value="">전체</option>
                                                    <option value="title">제목</option>
                                                    <option value="content">내용</option>
                                                    <option value="writer">작성자</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="keyword">키워드</label>
                                                <input type="search" id="keyword"
                                                       name="keyword" class="form-control"
                                                       <%-- onkeyup 은 밑의 키워드에서 작성한 게, 위에 뜰 수 있도록 함. --%>
                                                       onkeyup="document.getElementById('mainKeyword').value = this.value" placeholder="키워드를 입력해 주세요." />
                                            </div>
                                            <%-- button 의 type 이 submit 이면, 액션을 뜻하는 것이고, type = "button"에 onclick = "" 이 되게 되면, 스크립트로 행위를 처리하겠다는 뜻임. --%>
                                            <button type="button" onclick="movePage(1)" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                        </form>
                                    </div>
                                </div>
                                <%-- 페이지 1로 이동하라는 뜻임.  --%>
                                <button type="button" onclick="movePage(1)" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                            </div>
                        </div>
                    </div>

                    <div class="table-responsive clearfix">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>등록일</th>
                                <th>조회 수</th>
                            </tr>
                            </thead>
                            <tbody>
                    
                <c:forEach items = "${result.list}" var="row" varStatus="status" >

                <tr>
                    <td> ${status.count} </td>
                    <td class="text-left"><a href="javascript:void(0)" onclick = "viewPage(${row.idx})"> ${row.title}</a> </td>
                    <td>${row.writer}</td>
                    <td>${row.insertTime}</td>
                    <td>${row.viewCnt}</td>
                </tr>

                    </c:forEach>
                    
                </tbody>
            </table>

            <div class="btn_wrap text-right">
                <a href="/board/write.hanwha" class="btn btn-primary waves-effect waves-light">등록</a>
            </div>

            <!-- page  -->
            <nav aria-label="Page navigation" class="text-center">
                <ul class = "pagination"></ul>
            </nav>
        
        </div>
            <!-- card-content -->
            
</div>
      			</div>
      			
      			<footer class="footer">
	                <ul class="list-inline">
	                    <li>2024 ⓒ Encore</li>
	                </ul>
            	</footer>
            
      			
    		</div>
  		</div>
	</div>
        <script src="../scripts/common.js"></script>
	    <script src="../scripts/jquery.min.js"></script>
	    <script src="../scripts/main.js"></script>
	    <script src="../plugin/bootstrap/js/bootstrap.min.js"></script>
	    <script src="../plugin/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
	    
        			
        <script>
        /*<![CDATA[*/
        window.onload = () => {
            console.log("debug >> load event");
            console.log("debug >> pagination event");
            const startPage = "${result.pagination.startPage}";
            const endPage = "${result.pagination.endPage}"
            console.log("debug >> pagination startPage : " + startPage);
            console.log("debug >> pagination endPage : " + endPage);
            const page = "${params.page}";
            const recordSize = "${params.recordSize}";
            const pageSize = "${params.pageSize}";
            console.log("debug >> pageDTO page : " + page);
            console.log("debug >> pageDTO recordSize : " + recordSize);
            console.log("debug >> pageDTO pageSize : " + pageSize);
            render_pagination(startPage, endPage);
            maintainQueryParams();
            //viewPage(page);
        } // 윈도우가 로드될 때 이 이벤트를 실행

        function viewPage(idx){
            window.alert(idx + "번째의 상세 페이지로 이동합니다.");
            const queryString = (location.search) ? location.search + "&idx=" + idx : "?idx=" + idx;
            console.log("debug >>. queryString , ", queryString);
            location.href = "/board/view.hanwha" + queryString;
        }

        function maintainQueryParams(){
            if(!location.search){
                return false;

            }
            const params = new URLSearchParams(location , location.search);
            console.log("debug params >>> ", params);
            const form = document.getElementById("searchForm");
            params.forEach((value, key) => {
                if(form[key]) {
                    form[key].value = value;
                }
            });
            document.getElementById('mainKeyword').value = form.keyword.value;

        }


        function render_pagination(startPage, endPage){

            let html = '';

            for(let idx = 1 ; idx <= endPage ; idx++ ) {
                html += "<li><a href='javascript:movePage("+ idx + ")'>" + idx + "</a></li>";
            }
            document.querySelector(".pagination").innerHTML = html;
        }

        function movePage(idx) {
            alert(idx + "번째 페이지로 이동합니다.");
            // 검색을 같이 생각하는 페이지 이동이라면?
            console.log("debug >> path : " + location.pathname);
            const searchType = document.getElementById('searchType').value;
            const keyword = document.getElementById('keyword').value;
            console.log("debug >> searchType , keyword : " + searchType + ", " + keyword);

            const queryString = {
                page : idx,
                recordSize : 2,
                pageSize : 10,
                keyword : keyword,
                searchType : searchType
            };

            console.log("debug >> " , queryString);
            console.log("debug >> " + new URLSearchParams(queryString).toString());
            location.href=location.pathname + "?" + new URLSearchParams(queryString).toString();
        }

        /*]]>*/
        </script>
</body>
</html>
