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
    
	<div id="wrapper">
        <div class="main-content">
            <div class="row row-inline-block small-spacing">
                <div class="col-xs-12">
                    <div class="box-content">
                    
                    
                    
			<!--  content -->
			<div class="card-content">
            <form id="fmt" class="form-horizontal" method="POST" autocomplete="off">
                
                <input type="hidden" name="idx" id="idx"/>

                <div class="form-group">
                    <label for="isNotice" class="col-sm-2 control-label">공지글 설정</label>
                    <div class="col-sm-10" style="margin-top: 10px;">
                        <input  type="checkbox" 
                                id="noticeYn" 
                                name="noticeYn"
                                />
                                <%-- id : script와 연계할 때 사용 / name : 우리가 만든 객체와 binding할 때 --%>
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">제목</label>
                    <div class="col-sm-10">
                        <input type="text" id="title" name="title" class="form-control" maxlength="50" placeholder="제목을 입력해 주세요." value = "${response.title}" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="writer" class="col-sm-2 control-label">이름</label>
                    <div class="col-sm-10">
                        <input type="text" id="writer" name="writer" class="form-control" maxlength="10" placeholder="이름을 입력해 주세요." value = "${response.writer}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="content" class="col-sm-2 control-label">내용</label>
                    <div class="col-sm-10">
                        <textarea id="content" name="content" class="form-control" maxlength="1000" placeholder="내용을 입력해 주세요." >${response.content}</textarea>
                    </div>
                </div>

            </div>
            </form>

            <div class="btn_wrap text-center" >
                    <a href="javascript:listPage()" class="btn btn-default waves-effect waves-light">뒤로가기</a>
                    <%-- <a href="/board/save.hanwha" class="btn btn-primary waves-effect waves-light">저장하기</a> --%>
                    <button type="button" id="btn" class="btn btn-primary waves-effect waves-light" >저장하기</button>
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
        $(document).ready(function(){

            if("${response.noticeYn}" == "true" ){
                $("#noticeYn").attr("checked", true);
            }else{
                $("#noticeYn").attr("checked", false);
            }

            $("#btn").click(function(){
                //alert("btn click");
                console.log(location.search);

                if(location.search == ""){
                    // null로 확인이 안되서 ""으로!
                    console.log("location search null path /board/write.hanwha")
                    $("#fmt").attr("action", "/board/write.hanwha").submit();
                    // form태그에 접근해서 attr을 통해 action을 지정하고 action을 실행
                }else {
                    console.log("location search null path /board/update.hanwha")
                    $("#idx").val(${response.idx});
                    // 값을 변경
                    $("#fmt").attr("action", "/board/update.hanwha?idx=" + $("#idx").val()).submit();
                    // script에서 EL을 사용할 수 있다.
                }
            })
        })

        function listPage(){
            console.log("debug >>> listPage() call");
            const queryString = new URLSearchParams(location.search);
            location.href = "/board/list.hanwha?" + queryString.toString();
        }
        /*]]>*/
        </script>
</body>
</html>


