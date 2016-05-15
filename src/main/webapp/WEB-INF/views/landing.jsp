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
      <title>Career Recommendation System</title>
      <!-- Bootstrap Core CSS -->
      <link href='<c:url value="/resources/landing/css/bootstrap.min.css" />' rel="stylesheet">
      <!-- Custom CSS -->
      <link href='<c:url value="/resources/landing/css/agency.css" />' rel="stylesheet">
      <link href='<c:url value="/resources/landing/css/style.css" />' rel="stylesheet">
      <link rel="stylesheet" href='<c:url value="/resources/landing/css/form-elements.css" />'>
      <!-- Custom Fonts -->
      <link href='<c:url value="/resources/landing/font-awesome/css/font-awesome.min.css" />' rel="stylesheet" type="text/css">
      <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
      <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
      <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
      <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
      <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
      <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
   </head>
   <body id="page-top" class="index">
      <!-- Navigation -->
      <nav class="navbar navbar-default navbar-fixed-top">
         <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand page-scroll" href="#page-top">CRS</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav navbar-right">
                  <li class="hidden">
                     <a href="#page-top"></a>
                  </li>
                  <li>
                     <a class="page-scroll" href="#about" >What we do</a>
                  </li>
                  <li>
                     <a class="page-scroll" href="#portfolio">Outline</a>
                  </li>
                  <li>
                     <a class="page-scroll" href="#team">Who we are</a>
                  </li>
                  <li>
                     <a class="page-scroll" href="#contact">Contact</a>
                  </li>
                  <li>
                     <form:form action="login" method="get" class="navbar-form navbar-right" modelAttribute="loginForm">
                        <div class="form-group">
                           <input type="email" name="emailLogin" placeholder="Email" class="form-control" />
                        </div>
                        <div class="form-group">
                           <input type="password" name="passwordLogin" placeholder="Password" class="form-control" />
                        </div>
                        <button type="submit" class="btn btn-success"> Login </button>
                     </form:form>
                  </li>
               </ul>
            </div>
            <!-- /.navbar-collapse -->
         </div>
         <!-- /.container-fluid -->
      </nav>
      
      <!-- Header -->
      <header>
         <div class="container">
            <div class="row">
               <div class="col-lg-6">
                  <div class="intro-text">
                     <p class="intro-lead-in">Welcome to the </p>
                     <p class="intro-lead-in"> Next Gen </p>
                     <p class="intro-lead-in"> Career Recommendation </p>
                     <p class="intro-lead-in"> System </p>
                  </div>
               </div>
               <div class="col-lg-2 intro-text">
                  <p class="sign-up-form"><img src='<c:url value="/resources/landing/img/signup.png" />' width="200" height="180"> </p>
               </div>
               <div class="col-lg-4">
                  <div class="intro-text">
                     <form:form action="signup" method="post" role="form" modelAttribute="signupForm" enctype="multipart/form-data">
                        <p class="intro-lead-in">Would you like to sign up?</p>
                        <hr class="colorgraph">
                        <div class="row">
                           <div class="col-xs-12 col-sm-6 col-md-6">
                              <div class="form-group">
                                 <input type="text" name="first_name" id="first_name" class="form-control input-lg" placeholder="First Name" tabindex="1">
                              </div>
                           </div>
                           <div class="col-xs-12 col-sm-6 col-md-6">
                              <div class="form-group">
                                 <input type="text" name="last_name" id="last_name" class="form-control input-lg" placeholder="Last Name" tabindex="2">
                              </div>
                           </div>
                        </div>
                        <div class="form-group">
                           <input type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address" tabindex="4">
                        </div>
                        <div class="form-group">
                           <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="5">
                        </div>
                        <div class="form-group">
                           <label for="resume" class="col-sm-3 control-label" style="color: white;">Resume</label>	
                           <div class="col-sm-9">
                              <input type="file" name="file"><br>
                           </div>
                        </div>
                        <hr class="colorgraph">
                        <div class="row">
                           <div class="col-lg-12"><button type="submit" class="btn btn-primary btn-block btn-lg" tabindex="7"> Register </button></div>
                        </div>
                     </form:form>
                  </div>
               </div>
            </div>
         </div>
      </header>
      
      <!-- Services Section -->
      <section id="about">
         <div class="container">
            <div class="row">
               <div class="col-lg-12 text-center">
                  <h2 class="section-heading">About Us</h2>
                  <h3 class="section-subheading">
                     Recommendation systems play a major role in directing the students to the right path in their professional and academic career choices.
                     <p></p>
                     <p>After spending countless hours to hunt for jobs that match our interests and skills we came to the conclusion that people need an application which would save time by suggesting available job profiles based on an individual's skill set and interests.</p>
                     <p> We provide two features in our system as listed below:</p>
                  </h3>
               </div>
            </div>
            <div class="row text-center">
               <div class="col-md-6">
                  <span class="fa-stack fa-4x">
                  <img src='<c:url value="/resources/landing/img/service2.png" />' height="100" width="80">
                  </span>
                  <h4 class="service-heading">Jobs Recommendation</h4>
                  <p class="text-muted">Recommend jobs to the users and display the following information: Company, Job Title and the Percentage match of the job description and requirements with the profile listed.</p>
                  <p class="text-muted"> You have an option to apply or save the jobs recommended.</p>
               </div>
               <div class="col-md-6">
                  <span class="fa-stack fa-4x">
                  <img src='<c:url value="/resources/landing/img/service.png" />' height="100" width="80">
                  </span>
                  <h4 class="service-heading">Skills Recommendation</h4>
                  <p class="text-muted">A list of the potential skills you should work on that would help you score that dream job. </p>
                  <p></p>
                  <p class="text-muted">This information helps you understand in which areas you need improvement and what you need to focus on in order to achieve your goals.</p>
               </div>
            </div>
            <div class="row text-center">
               <div class="col-md-12">
                  <div class="timeline-image">
                     <h4>Be Part of Our story.!</h4>
                  </div>
               </div>
            </div>
         </div>
      </section>
      
      <!-- Portfolio Grid Section -->
      <section id="portfolio" class="bg-light-gray">
         <div class="container">
            <div class="row">
               <div class="col-lg-12 text-center">
                  <h2 class="section-heading">CRS Overview</h2>
                  <h3 class="section-subheading"> A Glimpse of our Application Design</h3>
                  <h3 class="section-subheading text-muted"></h3>
               </div>
            </div>
            <div class="row">
               <div class="col-md-4 col-sm-6 portfolio-item">
                  <img src='<c:url value="/resources/landing/img/one.jpg" />' class="img-responsive" alt="">   
               </div>
               <div class="col-md-4 col-sm-6 portfolio-item">
                  <img src='<c:url value="/resources/landing/img/two.jpg" />' class="img-responsive" alt="">
               </div>
               <div class="col-md-4 col-sm-6 portfolio-item">
                  <img src='<c:url value="/resources/landing/img/three.jpg" />' class="img-responsive" alt="">
               </div>
            </div>
         </div>
      </section>
      
      <!-- Team Section -->
      <section id="team" class="bg-light-gray">
         <div class="container">
            <div class="row">
               <div class="col-lg-12 text-center">
                  <h3 class="section-heading">The Rockstars</h3>
               </div>
            </div>
            <div class="row">
               <div class="col-lg-12 text-center"></div>
            </div>
            <div class="row">
               <div class="col-lg-12 text-center"></div>
            </div>
            <div class="row">
               <div class="col-lg-12 text-center"></div>
            </div>
            <div class="row">
               <div class="col-lg-12 text-center"></div>
            </div>
            <div class="row">
               <div class="col-sm-3">
                  <div class="team-member">
                     <img src='<c:url value="/resources/landing/img/amol.png" />' class="img-responsive img-circle" alt="">
                     <p class="intro-lead-in"> Amol Thombre</p>
                  </div>
               </div>
               <div class="col-sm-3">
                  <div class="team-member">
                     <img src='<c:url value="/resources/landing/img/bharat.jpeg" />' class="img-responsive img-circle" alt="">
                     <p class="intro-lead-in">Bharat Patel</p>
                  </div>
               </div>
               <div class="col-sm-3">
                  <div class="team-member">
                     <img src='<c:url value="/resources/landing/img/purvi.png" />' class="img-responsive img-circle" alt="">
                     <p class="intro-lead-in"> Purvi Singla</p>
                  </div>
               </div>
               <div class="col-sm-3">
                  <div class="team-member">
                     <img src='<c:url value="/resources/landing/img/varun.png" />' class="img-responsive img-circle" alt="">
                     <p class="intro-lead-in"> Varun Kakuste</p>
                  </div>
               </div>
            </div>
            <div class="row text-center">
               <div class="col-lg-12 text-center"></div>
               <p>Our team comprises of Software Engineering graduates at the San Jose State University.</p>
               <p> We specialize in Enterprise Software Technologies and Cloud Computing.</p>
            </div>
         </div>
      </section>
      
      <!-- Contact Section -->
      <section id="contact">
         <div class="container">
            <div class="row">
               <div class="col-lg-12 text-center">
                  <h2 class="section-heading">Contact Us</h2>
               </div>
            </div>
            <div class="row">
               <div class="col-md-3"></div>
               <div class="col-md-6">
                  <form name="sentMessage" id="contactForm" novalidate>
                     <p class="section-heading">We're happy to answer any questions you have. Just send us a message in the form below.</p>
                     <div class="row">
                        <div class="col-md-12">
                           <div class="form-group">
                              <input type="text" class="form-control" placeholder="Your Name *" id="name" required data-validation-required-message="Please enter your name.">
                              <p class="help-block text-danger"></p>
                           </div>
                           <div class="form-group">
                              <input type="email" class="form-control" placeholder="Your Email *" id="email" required data-validation-required-message="Please enter your email address.">
                              <p class="help-block text-danger"></p>
                           </div>
                           <div class="form-group">
                              <textarea class="form-control" placeholder="Your Message *" id="message" required data-validation-required-message="Please enter a message."></textarea>
                              <p class="help-block text-danger"></p>
                           </div>
                        </div>
                     </div>
                     <div class="clearfix"></div>
                     <div class="col-md-12 text-center">
                        <div id="success"></div>
                        <button type="submit" class="btn btn-l" style="background-color:#E6E6E6">Send Message</button>
                     </div>
                  </form>
               </div>
               <div class="col-md-3"></div>
            </div>
         </div>
      </section>
      
      <!-- jQuery -->
      <script src="js/jquery.js"></script>
      <!-- Bootstrap Core JavaScript -->
      <script src="js/bootstrap.min.js"></script>
      <!-- Plugin JavaScript -->
      <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
      <script src="js/classie.js"></script>
      <script src="js/cbpAnimatedHeader.js"></script>
      <!-- Contact Form JavaScript -->
      <script src="js/jqBootstrapValidation.js"></script>
      <script src="js/contact_me.js"></script>
      <!-- Custom Theme JavaScript -->
      <script src="js/agency.js"></script>
   </body>
</html>