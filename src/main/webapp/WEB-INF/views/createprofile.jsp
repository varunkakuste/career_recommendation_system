<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
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
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <c:out value="${sessionScope.userName}"/> <b class="caret"></b></a>
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
                  <li class="active">
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
                  <!-- <li class="active">
                     <a href="charts.html"><i class="fa fa-fw fa-bar-chart-o"></i> Charts</a>
                     </li>
                     <li>
                     <a href="tables.html"><i class="fa fa-fw fa-table"></i> Tables</a>
                     </li>
                     <li>
                     <a href="forms.html"><i class="fa fa-fw fa-edit"></i> Forms</a>
                     </li>
                     <li>
                     <a href="bootstrap-elements.html"><i class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a>
                     </li>
                     <li>
                     <a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>
                     </li>
                     <li>
                     <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>
                     <ul id="demo" class="collapse">
                         <li>
                             <a href="#">Dropdown Item</a>
                         </li>
                         <li>
                             <a href="#">Dropdown Item</a>
                         </li>
                     </ul>
                     </li>
                     <li>
                     <a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Blank Page</a>
                     </li>
                     <li>
                     <a href="index-rtl.html"><i class="fa fa-fw fa-dashboard"></i> RTL Dashboard</a>
                     </li>-->
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
                        Profile
                     </h1>
                     <!-- <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-bar-chart-o"></i> Charts
                        </li>
                        </ol>-->
                  </div>
               </div>
               <!-- /.row -->
               <div class="container">
                  <form:form method="post" action="createprofile" modelAttribute="userForm" class="form-horizontal" role="form">
                     <!--<h2>Registration Form</h2>-->
                     <div class="form-group">
                        <label for="Address" class="col-sm-3 control-label">First Name</label>
                        <div class="col-sm-9">
                           <form:input path="signupForm.first_name" placeholder="first name"/>
                           <form:hidden path="userId"/>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="Address" class="col-sm-3 control-label">Last Name</label>
                        <div class="col-sm-9">
                           <form:input path="signupForm.last_name" placeholder="last name"/>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="Address" class="col-sm-3 control-label">Email</label>
                        <div class="col-sm-9">
                           <form:input path="signupForm.email" placeholder="email"/>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="Address" class="col-sm-3 control-label">Password</label>
                        <div class="col-sm-9">
                           <form:input path="signupForm.password" placeholder="password"/>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="Address" class="col-sm-3 control-label">Address</label>
                        <div class="col-sm-9">
                           <!-- <input type="text" id="firstName" placeholder="Address" class="form-control" autofocus> -->
                           <form:input path="address" placeholder="Address"/>
                           <span class="help-block">Block Number, Street Name, Apt. Number</span>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="city" class="col-sm-3 control-label">City/Town</label>
                        <div class="col-sm-9">
                           <!--  <input type="text" id="city" placeholder="City/Town" class="form-control"> -->
                           <form:input path="city" placeholder="City/Town"/>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="state" class="col-sm-3 control-label">State</label>
                        <div class="col-sm-9">
                           <!-- <input type="text" id="state" placeholder="State" class="form-control"> -->
                           <form:input path="state" placeholder="State"/>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="country" class="col-sm-3 control-label">Country</label>
                        <div class="col-sm-9">
                           <form:select path="country" class="form-control">
                              <option>United States</option>
                              <option>Greece</option>
                              <option>India</option>
                              <option>Denmark</option>
                              <option>Canada</option>
                              <option>Fiji</option>
                              <option>Gabon</option>
                              <option>Haiti</option>
                           </form:select>
                        </div>
                     </div>
                     <%-- <div class="form-group">
                        <label for="birthDate" class="col-sm-3 control-label">Date of Birth</label>
                        <div class="col-sm-9">
                           <!-- <input type="date" id="birthDate" class="form-control"> -->
                           <form:input path="dob" class="form-control" placeholder="birth-date"/>
                        </div>
                        </div> --%>
                     <!-- /.form-group -->
                     <div class="form-group">
                        <label class="control-label col-sm-3">Gender</label>
                        <div class="col-sm-6">
                           <div class="row">
                              <div class="col-sm-4">
                                 <label class="radio-inline">
                                    <!-- <input type="radio" id="femaleRadio" value="Female">Female -->
                                    <form:radiobutton path="gender" value="female" />
                                    Female
                                 </label>
                              </div>
                              <div class="col-sm-4">
                                 <label class="radio-inline">
                                    <!-- <input type="radio" id="maleRadio" value="Male">Male -->
                                    <form:radiobutton path="gender" value="male" />
                                    Male
                                 </label>
                              </div>
                              <div class="col-sm-4">
                                 <label class="radio-inline">
                                    <!-- <input type="radio" id="uncknownRadio" value="Unknown">Unknown -->
                                    <form:radiobutton path="gender" value="unknown" />
                                    Unknown
                                 </label>
                              </div>
                           </div>
                        </div>
                     </div>
                     <!-- /.form-group -->
                     <!-- <div class="form-group">
                        <label for="resume" class="col-sm-3 control-label">Resume</label>	
                                       
                           	     <div class="col-sm-9">
                                          <input type="file">
                                      </div>                     
                        
                                  </div> -->
                     <!-- <div class="form-group">
                        <label for="profile_picture" class="col-sm-3 control-label">Profile Picture</label>	
                        <div class="col-sm-9">
                           <input type="file">
                        </div>
                        </div> -->
                     <div class="form-group">
                        <label class="control-label col-sm-3">Skills Proficiency</label>
                        <div class="col-lg-9">
                           <table>
                              <tr>
                                 <th>Skills</th>
                                 <th>Beginner&nbsp;</th>
                                 <th>Intermediate&nbsp;</th>
                                 <th>Expert</th>
                              </tr>
                              <c:forEach items="${userForm.skillsProficiency}" var="user" varStatus="status">
                                 <tr>
                                    <td>
                                       <input readonly="readonly" style="outline: none; border: 0;" name="skillsProficiency[${status.index}].skill" value="${user.skill}"/>
                                       <input type="hidden" name="skillsProficiency[${status.index}].skillId" value="${user.skillId}"/>
                                       <%--  <input type="hidden" name="userId" value="${user.userId}"/> --%>
                                    </td>
                                    <td>
                                       <form:radiobutton path="skillsProficiency[${status.index}].proficiency" value="1" />
                                    </td>
                                    <td>
                                       <form:radiobutton path="skillsProficiency[${status.index}].proficiency" value="3" />
                                    </td>
                                    <td>
                                       <form:radiobutton path="skillsProficiency[${status.index}].proficiency" value="5" />
                                    </td>
                                 </tr>
                              </c:forEach>
                           </table>
                        </div>
                     </div>
                     <!-- <div class="form-group">
                        <label class="control-label col-sm-3">Job Preference</label>
                        <div class="col-sm-9">
                        <div class="row">
                        <div class="col-sm-4">
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Software Engineer" value="Software Engineer">Software Engineer
                          </label>
                        </div>
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Front End Engineer" value="Front End Engineer">Front End Engineer
                          </label>
                        </div>
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Back End Engineer" value="Back End Engineer">Back End Engineer
                          </label>
                        </div>
                        </div>
                        <div class="col-sm-4">
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Full Stack Engineer" value="Full Stack Engineer">Full Stack Engineer
                          </label>
                        </div>
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Data Engineer" value="Data Engineer">Data Engineer
                          </label>
                        </div>
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Android Engineer" value="Android Engineer">Android Engineer
                          </label>
                        </div>
                        </div>
                        <div class="col-sm-4">
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Security Engineer" value="Security Engineer">Security Engineer
                          </label>
                        </div>
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Network Engineer" value="Network Engineer">Network Engineer
                          </label>
                        </div>
                        <div class="checkbox">
                          <label>
                              <input type="checkbox" id="Java Developer" value="Java Developer">Java Developer
                          </label>
                        </div>
                        </div>		
                        </div>
                        </div>	
                        </div> /.form-group -->
                     <div class="form-group">
                        <label for="location_preference" class="col-sm-3 control-label">Location Preference</label>
                        <div class="col-sm-9">
                           <!-- <input type="text" id="location_preference" placeholder="Location Preference" class="form-control"> -->
                           <form:input path="location" class="form-control" placeholder="location preference"/>
                        </div>
                     </div>
                     <!--<div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox">I accept <a href="#">terms</a>
                                </label>
                            </div>
                        </div>
                        </div>--> <!-- /.form-group -->
                     <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                           <button type="submit" class="btn btn-primary btn-block">Register</button>
                        </div>
                     </div>
                  </form:form>
                  <!-- /form -->
               </div>
               <!-- ./container -->
               <!-- Flot Charts -->
               <!--<div class="row">
                  <div class="col-lg-12">
                      <h2 class="page-header">Flot Charts</h2>
                      <p class="lead">Flot is a pure JavaScript plotting library for jQuery, with a focus on simple usage, attractive looks and interactive features. For full usage instructions and documentation for Flot Charts, visit <a href="http://www.flotcharts.org/">http://www.flotcharts.org/</a>.</p>
                  </div>
                  </div>-->
               <!-- /.row -->
               <!--<div class="row">
                  <div class="col-lg-12">
                      <div class="panel panel-primary">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Line Graph Example with Tooltips</h3>
                          </div>
                          <div class="panel-body">
                              <div class="flot-chart">
                                  <div class="flot-chart-content" id="flot-line-chart"></div>
                              </div>
                          </div>
                      </div>
                  </div>
                  </div>-->
               <!-- /.row -->
               <!--<div class="row">
                  <div class="col-lg-4">
                      <div class="panel panel-green">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Pie Chart Example with Tooltips</h3>
                          </div>
                          <div class="panel-body">
                              <div class="flot-chart">
                                  <div class="flot-chart-content" id="flot-pie-chart"></div>
                              </div>
                              <div class="text-right">
                                  <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="col-lg-8">
                      <div class="panel panel-yellow">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Multiple Axes Line Graph Example with Tooltips and Raw Data</h3>
                          </div>
                          <div class="panel-body">
                              <div class="flot-chart">
                                  <div class="flot-chart-content" id="flot-multiple-axes-chart"></div>
                              </div>
                              <div class="text-right">
                                  <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                              </div>
                          </div>
                      </div>
                  </div>
                  </div>-->
               <!-- /.row -->
               <!--  <div class="row">
                  <div class="col-lg-6">
                      <div class="panel panel-red">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Moving Line Chart</h3>
                          </div>
                          <div class="panel-body">
                              <div class="flot-chart">
                                  <div class="flot-chart-content" id="flot-moving-line-chart"></div>
                              </div>
                              <div class="text-right">
                                  <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="col-lg-6">
                      <div class="panel panel-primary">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Bar Graph with Tooltips</h3>
                          </div>
                          <div class="panel-body">
                              <div class="flot-chart">
                                  <div class="flot-chart-content" id="flot-bar-chart"></div>
                              </div>
                              <div class="text-right">
                                  <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                              </div>
                          </div>
                      </div>
                  </div>
                  </div>-->
               <!-- /.row -->
               <!-- Morris Charts -->
               <!--<div class="row">
                  <div class="col-lg-12">
                      <h2 class="page-header">Morris Charts</h2>
                      <p class="lead">Morris.js is a very simple API for drawing line, bar, area and donut charts. For full usage instructions and documentation for Morris.js charts, visit <a href="http://morrisjs.github.io/morris.js/">http://morrisjs.github.io/morris.js/</a>.</p>
                  </div>
                  </div>-->
               <!-- /.row -->
               <!--<div class="row">
                  <div class="col-lg-12">
                      <div class="panel panel-green">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Area Line Graph Example with Tooltips</h3>
                          </div>
                          <div class="panel-body">
                              <div id="morris-area-chart"></div>
                          </div>
                      </div>
                  </div>
                  </div>-->
               <!-- /.row -->
               <!--<div class="row">
                  <div class="col-lg-4">
                      <div class="panel panel-yellow">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Donut Chart Example</h3>
                          </div>
                          <div class="panel-body">
                              <div id="morris-donut-chart"></div>
                              <div class="text-right">
                                  <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="col-lg-4">
                      <div class="panel panel-red">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Line Graph Example with Tooltips</h3>
                          </div>
                          <div class="panel-body">
                              <div id="morris-line-chart"></div>
                              <div class="text-right">
                                  <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="col-lg-4">
                      <div class="panel panel-primary">
                          <div class="panel-heading">
                              <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Bar Graph Example</h3>
                          </div>
                          <div class="panel-body">
                              <div id="morris-bar-chart"></div>
                              <div class="text-right">
                                  <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                              </div>
                          </div>
                      </div>
                  </div>
                  </div>-->
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
      <!-- Flot Charts JavaScript -->
      <!--[if lte IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
      <script src="js/plugins/flot/jquery.flot.js"></script>
      <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
      <script src="js/plugins/flot/jquery.flot.resize.js"></script>
      <script src="js/plugins/flot/jquery.flot.pie.js"></script>
      <script src="js/plugins/flot/flot-data.js"></script>
   </body>
</html>