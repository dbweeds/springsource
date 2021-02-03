<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<link rel="stylesheet" href="/resources/css/mycss.css" />

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Modify Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" method="post" role="form" id="modifyForm">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value="${row.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" value="${row.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content">${row.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly" value="${row.writer}">                				
                				</div>  
                				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                				<sec:authentication property="principal" var="info"/>
                				<sec:authorize access="isAuthenticated()">
                				<c:if test="${info.username == row.writer}"> 
                				<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>              			
                				<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>              			
                				</c:if>
                				</sec:authorize>
                				<button type="submit" data-oper='list' class="btn btn-info">List</button>              			
                			</form>
                		</div>
                	</div>
                </div>
            </div>
            <%-- 첨부파일 보여주기 --%>
            <div class="bigPictureWrapper">
				<div class="bigPicture"></div>
			</div>
            <div class="row">
            	<div class="col-lg-12">
            		<div class="panel panel-default">
            			<div class="panel-heading">File Attach</div>
            			<div class="panel-body">
            				<div class="form-group uploadDiv">
            					<input type="file" name="uploadFile" id="" multiple/>
            				</div>
            				<div class="uploadResult">
            					<ul></ul>
            				</div>
            			</div>
            		</div>
            	</div>
            </div>         
			<%-- remove와 list를 위한 폼--%>
		<form action="" id="myform" method="post">
			<input type="hidden" name="bno" value="${row.bno}"/>
			<input type="hidden" name="type" value="${cri.type}"/>
    		<input type="hidden" name="keyword" value="${cri.keyword}"/>
    		<input type="hidden" name="pageNum" value="${cri.pageNum}"/>
    		<input type="hidden" name="amount" value="${cri.amount}"/>
    		<!-- 시큐리티 때문에 추가 -->
    		<input type="hidden" name="writer" value="${row.writer}"/>
    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
			<%-- 스크립트 --%>
<script>
	var bnoVal=${row.bno};
	//토큰값 생성
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTocenValue = "${_csrf.token}";
</script>
<script src="/resources/js/modify.js"></script> 
<%@include file="../includes/footer.jsp" %>       