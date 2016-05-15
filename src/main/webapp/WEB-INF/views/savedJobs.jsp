<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">
      <title>Career Path Recommendation System</title>
      <!-- Bootstrap Core CSS -->
      <link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">
      <!-- Custom CSS -->
      <link href='<c:url value="/resources/css/sb-admin.css" />' rel="stylesheet">
      <!-- Morris Charts CSS -->
      <link href='<c:url value="/resources/css/plugins/morris.css" />' rel="stylesheet">
      <!-- Custom Fonts -->
      <link href='<c:url value="/resources/font-awesome/css/font-awesome.min.css" />' rel="stylesheet">
      <style>
         #savebutton{
         float:right;
         }
         .vdivide [class*='col-']:not(:last-child):after {
         background: #009900;
         width: 1px;
         content: "";
         display:block;
         position: absolute;
         top:-0.8em;
         bottom: 0;
         right: 0;
         min-height: 169px;
         }
         #heightPanel{
         min-height: 12em;
         }
         hr{
         border-color: #009900;
         border-width: 1px;
         margin-left: 0px;
         margin-right: auto;
         }
         .panel-heading{
         padding-right:0;
         padding-left:0;
         }
         .no-gutter > [class*='col-'] {
         padding-right:0;
         padding-left:0;
         }
         .dataTables_filter, .dataTables_info { display: none; }
      </style>
      
      <script type="text/javascript">
			function applyJob() {
				var jobForm = document.forms['jobForm'];
				jobForm.method = "post";
				jobForm.action = "apply";
				jobForm.submit();
			}
	   </script>
      
   </head>
   <body>
      <div id="wrapper">
         <!-- Navigation -->
         <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="index.html">CRS Admin</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
               <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                  <ul class="dropdown-menu message-dropdown">
                     <li class="message-preview">
                        <a href="#">
                           <div class="media">
                              <span class="pull-left">
                              <img class="media-object" src="http://placehold.it/50x50" alt="">
                              </span>
                              <div class="media-body">
                                 <h5 class="media-heading"><strong>Varun Kakuste</strong>
                                 </h5>
                                 <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                 <!--<p>Lorem ipsum dolor sit amet, consectetur...</p>-->
                              </div>
                           </div>
                        </a>
                     </li>
                     <li class="message-preview">
                        <a href="#">
                           <div class="media">
                              <span class="pull-left">
                              <img class="media-object" src="http://placehold.it/50x50" alt="">
                              </span>
                              <div class="media-body">
                                 <h5 class="media-heading"><strong>Bharat Patel</strong>
                                 </h5>
                                 <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                 <!--<p>Lorem ipsum dolor sit amet, consectetur...</p>-->
                              </div>
                           </div>
                        </a>
                     </li>
                     <li class="message-preview">
                        <a href="#">
                           <div class="media">
                              <span class="pull-left">
                              <img class="media-object" src="http://placehold.it/50x50" alt="">
                              </span>
                              <div class="media-body">
                                 <h5 class="media-heading"><strong>Purvi Singla</strong>
                                 </h5>
                                 <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                 <!--<p>Lorem ipsum dolor sit amet, consectetur...</p>-->
                              </div>
                           </div>
                        </a>
                     </li>
                     <li class="message-footer">
                        <a href="#">Read All New Messages</a>
                     </li>
                  </ul>
               </li>
               <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                  <ul class="dropdown-menu alert-dropdown">
                     <li>
                        <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                     </li>
                     <li>
                        <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                     </li>
                     <li>
                        <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                     </li>
                     <li>
                        <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                     </li>
                     <li>
                        <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                     </li>
                     <li>
                        <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                     </li>
                     <li class="divider"></li>
                     <li>
                        <a href="#">View All</a>
                     </li>
                  </ul>
               </li>
               <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                     <i class="fa fa-user"></i> 
                     <c:out value="${sessionScope.userName}"/>
                     <b class="caret"></b>
                  </a>
                  <ul class="dropdown-menu">
                     <li>
                        <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                     </li>
                     <li>
                        <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                     </li>
                     <li>
                        <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                     </li>
                     <li class="divider"></li>
                     <li>
                        <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                     </li>
                  </ul>
               </li>
               <li>
                  <a href="logout" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Logout <b class="caret"></b></a>
               </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
               <ul class="nav navbar-nav side-nav">
                  <li>
                     <a href="dashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                  </li>
                  <li>
                     <a href="#"><i class="glyphicon glyphicon-pencil"></i>  Create Profile</a>
                  </li>
                  <li>
                     <a href="jobrecommendation"><i class="glyphicon glyphicon-thumbs-up"></i> Job Recommendation</a>
                  </li>
                  <li>
                     <a href="skillrecommendation"><i class="glyphicon glyphicon-thumbs-up"></i> Skill Recommendation</a>
                  </li>
                  <li>
                     <a href="appliedjobs"><i class="glyphicon glyphicon-thumbs-up"></i> Applied Jobs</a>
                  </li>
                  <li class="active">
                     <a href="savedjobs"><i class="glyphicon glyphicon-thumbs-up"></i> Saved Jobs</a>
                  </li>
               </ul>
            </div>
            <!-- /.navbar-collapse -->
         </nav>
         <div id="page-wrapper">
         	<c:if test="${not empty jobList}">
         		<form:form id="jobForm" name="jobForm" action="#" method="POST">
	            <div class="row">
	               <div class="col-lg-12">
	                  <!-- /.panel-heading -->
	                  <div class="dataTable_wrapper">
	                     <table id="dataTables-example">
	                        <thead>
	                           <tr>
	                              <th></th>
	                           </tr>
	                        </thead>
	                        <c:forEach var="arrayVar" items="${jobList}">
	                           <tr>
	                              <td>
	                                 <div class="row">
	                                    <div class="col-md-1"></div>
	                                    <div class="col-md-9">
	                                       <div class="panel panel-info">
	                                          <div class="panel-heading" id="heightPanel">
	                                             <div class ="container">
	                                                <div class="row vdivide no-gutter">
	                                                   <div class="col-md-4 text-center">
	                                                      <h4><input type="radio" id="selectedJob" name="selectedJob" value="${arrayVar.jobId}"> &nbsp;&nbsp;&nbsp;${arrayVar.companyName}, ${arrayVar.location}</h4>
	                                                      <div class="row ">
	                                                         <div class="col-md-12">
	                                                            <hr>
	                                                         </div>
	                                                      </div>
	                                                      <div class="row no-gutter">
	                                                         <div class="col-md-12">
	                                                            <c:if test="${arrayVar.percentageMatched eq 0}">
	                                                               <h5>${arrayVar.position}</h5>
	                                                            </c:if>
	                                                            <c:if test="${arrayVar.percentageMatched ne 0}">
	                                                               <h5>${arrayVar.position}</h5>
	                                                               <br>
	                                                               This job matches <strong>${arrayVar.percentageMatched}%</strong>
	                                                            </c:if>
	                                                         </div>
	                                                      </div>
	                                                   </div>
	                                                   <div class="col-md-5 text-center">
	                                                   		<h4 align="center"> Recommended Skills </h4>
	                                                      <h4>
	                                                         <c:forEach var="arrayVar" items="${arrayVar.skills}">
	                                                            <button type="button" class="btn btn-primary btn-sm" style="border-radius: 50px; margin:0.5em;">${arrayVar}</button>
	                                                         </c:forEach>
	                                                      </h4>
	                                                   </div>
	                                                </div>
	                                             </div>
	                                          </div>
	                                       </div>
	                                    </div>
	                                 </div>
	                              </td>
	                           </tr>
	                        </c:forEach>
	                     </table>
	                  </div>
	               </div>
	               <!-- /.col-lg-12 -->
	            </div>
	            <div class="row">
	            	<div align="center">
	            		<div class="col-md-6">
			            	<button type="button" class="btn btn-success btn-md" onclick="javascript: applyJob();">Apply</button>
			            </div>
					</div>
	            </div>
	            </form:form>
            </c:if>
            <c:if test="${empty jobList}">
            	<div class="row">
            		<h3 align="center"><font color="red">Sorry</font></h3><br><br>
            		<h4 align="center"><font color="red">No Saved Jobs...</font></h4>
            	</div>
            </c:if>
         </div>
      </div>
      <!-- /#wrapper -->
      <!-- jQuery -->
      <script src="js/jquery.js"></script>
      <!-- Bootstrap Core JavaScript -->
      <script src="js/bootstrap.min.js"></script>
      <!-- Morris Charts JavaScript -->
      <script src="js/plugins/morris/raphael.min.js"></script>
      <script src="js/plugins/morris/morris.min.js"></script>
      <script src="js/plugins/morris/morris-data.js"></script>
      <!-- Flot Charts JavaScript -->
      <!--[if lte IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
      <script src="js/plugins/flot/jquery.flot.js"></script>
      <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
      <script src="js/plugins/flot/jquery.flot.resize.js"></script>
      <script src="js/plugins/flot/jquery.flot.pie.js"></script>
      <script src="js/plugins/flot/flot-data.js"></script>
   </body>
</html>