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
                                 <h5 class="media-heading"><strong><c:out value="${sessionScope.userName}"/></strong>
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
                                 <h5 class="media-heading"><strong><c:out value="${sessionScope.userName}"/></strong>
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
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Welcome <c:out value="${sessionScope.userName}"/> <b class="caret"></b></a>
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
                  <li class="active">
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
                  <li>
                     <a href="savedjobs"><i class="glyphicon glyphicon-thumbs-up"></i> Saved Jobs</a>
                  </li>
               </ul>
            </div>
            <!-- /.navbar-collapse -->
         </nav>
         <div id="page-wrapper">
            <div class="container-fluid">
               <!-- Page Heading -->
               <div class="row">
                  <div class="col-lg-12">
                     <h1 class="page-header">
                        Dashboard <small>Statistics Overview</small>
                     </h1>
                     <ol class="breadcrumb">
                        <li class="active">
                           <i class="fa fa-dashboard"></i> Dashboard
                        </li>
                     </ol>
                  </div>
               </div>
               <!-- /.row -->
               <div class="row">
                  <div class="col-lg-3 col-md-6">
                     <div class="panel panel-primary">
                        <div class="panel-heading">
                           <div class="row">
                              <div class="col-xs-3">
                                 <i class="fa fa-comments fa-5x"></i>
                              </div>
                              <div class="col-xs-9 text-right">
                                 <div class="huge">${userForm.dashBoardBean.noOfRecommendedJobs}</div>
                                 <div>Recommended Jobs</div>
                              </div>
                           </div>
                        </div>
                        <a href="jobrecommendation">
                           <div class="panel-footer">
                              <span class="pull-left">View Details</span>
                              <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                              <div class="clearfix"></div>
                           </div>
                        </a>
                     </div>
                  </div>
                  <div class="col-lg-3 col-md-6">
                     <div class="panel panel-green">
                        <div class="panel-heading">
                           <div class="row">
                              <div class="col-xs-3">
                                 <i class="fa fa-tasks fa-5x"></i>
                              </div>
                              <div class="col-xs-9 text-right">
                                 <div class="huge">${userForm.dashBoardBean.noOfRecommendedSkills}</div>
                                 <div>Recommended Skills</div>
                              </div>
                           </div>
                        </div>
                        <a href="skillrecommendation">
                           <div class="panel-footer">
                              <span class="pull-left">View Details</span>
                              <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                              <div class="clearfix"></div>
                           </div>
                        </a>
                     </div>
                  </div>
                  <div class="col-lg-3 col-md-6">
                     <div class="panel panel-yellow">
                        <div class="panel-heading">
                           <div class="row">
                              <div class="col-xs-3">
                                 <i class="fa fa-shopping-cart fa-5x"></i>
                              </div>
                              <div class="col-xs-9 text-right">
                                 <div class="huge">${userForm.dashBoardBean.noOfAppliedJobs}</div>
                                 <div>Applied Jobs</div>
                              </div>
                           </div>
                        </div>
                        <a href="appliedjobs">
                           <div class="panel-footer">
                              <span class="pull-left">View Details</span>
                              <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                              <div class="clearfix"></div>
                           </div>
                        </a>
                     </div>
                  </div>
                  <div class="col-lg-3 col-md-6">
                     <div class="panel panel-red">
                        <div class="panel-heading">
                           <div class="row">
                              <div class="col-xs-3">
                                 <i class="fa fa-support fa-5x"></i>
                              </div>
                              <div class="col-xs-9 text-right">
                                 <div class="huge">${userForm.dashBoardBean.noOfSavedJobs}</div>
                                 <div>Saved Jobs</div>
                              </div>
                           </div>
                        </div>
                        <a href="savedjobs">
                           <div class="panel-footer">
                              <span class="pull-left">View Details</span>
                              <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                              <div class="clearfix"></div>
                           </div>
                        </a>
                     </div>
                  </div>
               </div>
               <!-- /.row -->
               <div class="row">
                  <div class="col-lg-12">
                     <div class="panel panel-default">
                        <div class="panel-heading">
                           <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> Area Chart</h3>
                        </div>
                        <div class="panel-body">
                           <div id="morris-area-chart"></div>
                        </div>
                     </div>
                  </div>
               </div>
               <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
         </div>
         <!-- /#page-wrapper -->
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
   </body>
</html>