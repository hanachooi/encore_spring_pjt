<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
                    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글작성 페이지</title>
	<link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../plugin/mCustomScrollbar/jquery.mCustomScrollbar.min.css" />
    
</head>
<body>

	<div class="fixed-navbar">
        <div class="pull-left">
            <h1 class="page-title">Hanwha SW Camp 5th</h1>
        </div>
    </div>
    <%-- 세션 처리 추가 --%>

    
	<div id="wrapper">
        <div class="main-content">
            <div class="row row-inline-block small-spacing">
                <div class="col-xs-12">
                    <div class="box-content">

    <div class="card-content">
        <div class="form-group">
            <label class="col-sm-2 control-label">제목</label>
            <div class="col-sm-10">
                <p class="form-control" id="title">${ result.title }</p> 
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">이름</label>
            <div class="col-sm-10">
                <p class="form-control" id="writer">${ result.writer }</p> 
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">내용</label>
            <div class="col-sm-10">
                <p class="form-control" id="content">${ result.content }</p> 
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">등록일</label>
            <div class="col-sm-10">
                <p class="form-control">${ result.insertTime }</p> 
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">조회 수</label>
            <div class="col-sm-10">
                <p class="form-control" id="viewCnt">${ result.viewCnt }</p> 
            </div>
        </div>

        <div class="btn_wrap text-center">
            <a  href="/board/list.hanwha" 
                class="btn btn-default waves-effect waves-light"
                onclick="listPage();">뒤로가기</a>
            <a  href="/board/write.hanwha?idx=${result.idx}" 
                class="btn btn-primary waves-effect waves-light"
                >수정하기</a>
            
            <%-- <a  href="/board/delete.hanwha?idx=${result.idx}" 
                class="btn btn-danger waves-effect waves-light"
                onclick="writePage();">삭제하기</a> --%>

            <button type="button" class="btn btn-danger waves-effect waves-light" id="delBtn">삭제하기</button>
        
            <input type="hidden" id="idx" value="${result.idx}" />
        
        </div>
    </div>

    <div class="box-content">
        <div class="card-content">
            <div class="clearfix">
                <h4 class="box-title pull-left">Comment</h4>
            </div>

            <form class="form-horizontal form-view">
                <div class="input-group margin-bottom-20">
                    <input type="email" class="form-control" placeholder="">
                    <div class="input-group-btn"><button type="button" class="btn waves-effect waves-light"><i class="fa fa-commenting" aria-hidden="true"></i></button></div>
                </div>
                <ul class="notice-list">
                    <li>
                        <span class="name">Betty Simmons</span>
                        <span class="desc">There are new settings available</span>
                        <span class="time">2019.03.26</span>
                        <button type="button" class="btn btn-xs btn-circle"><i class="fa fa-close" aria-hidden="true"></i></button>
                    </li>
                    <li>
                        <span class="name">Betty Kim</span>
                        <span class="desc">There are new settings available</span>
                        <span class="time">2019.03.15</span>
                        <button type="button" class="btn btn-xs btn-circle"><i class="fa fa-close" aria-hidden="true"></i></button>
                    </li>
                </ul>
            </form>
        </div>
    </div>


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
            $(document).ready(function(){
                $("#delBtn").click(function(){
                    // window.alert("버튼을 클릭하셨습니다.");
                    // console.log( $("#idx").val() );

                    // QueryString을 출력
                    console.log(location.search)

                    // 중복되지 않는 상수값
                    const idx = $("#idx").val();
                    if(!confirm(idx + "번 게시글을 삭제할까요?")){
                        return false;
                    }else {
                        location.href="/board/delete.hanwha?idx=" + location.search;
                    }

                })
            });
        /*]]>*/
        </script>
</body>
</html>
