<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>MeTube</title>

    <!-- Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <?php
      session_start();
      include_once "new_function.php";

      if(isset($_POST['register']))
        { signup($_POST['regUsername'], $_POST['regP1'], $_POST['regP2'],
                $_POST['regEmail'], $_POST['regFirst'], $_POST['regLast']); }

      if(isset($register_error))
        { echo "<div id='passwd_result'> register_error:".$register_error."</div>"; }


      if(isset($_POST['login']))
        { login($_POST['loginUsername'], $_POST['loginPassword']); }

      if(isset($login_error))
        { echo "<div id='passwd_result'>".$login_error."</div>"; }

      if(isset($_POST['logout']))
        { unset($_SESSION['username']); }

      if(isset($_POST['update']))
        { update($_SESSION['username'], $_POST['updateP1'], $_POST['updateP2'],
                    $_POST['updateEmail'], $_POST['updateFirst'], $_POST['updateLast']); }

      if(isset($_POST['sendMessage']))
        { uploadMessage($_SESSION['username'], $_POST['messageUsername'], $_POST['message']); }
    ?>

    <!--***** TOP NAVIGATION *****-->
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="http://people.cs.clemson.edu/~kirstee/4620/" onclick="loadPhotos(0)">MeTube</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" onclick="loadPhotos(0)">Home<span class="sr-only">(current)</span></a></li>
<?php
  if(isset($_SESSION['username'])){
    $username = $_SESSION['username'];
    $result = mysql_query("SELECT COUNT(msg_read) FROM messages WHERE receiver='$username' AND msg_read=0")
		  or die(mysql_error()); 

    $row = mysql_fetch_array($result);
    echo '<li><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" onclick="loadPhotos(2)">Profile</a></li>
          <li><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab" onclick="loadMessages()">Messages <span class="badge" id="msg-badge">'.$row[0].'</span></a></li>
          <li><a href="#subscriptions" aria-controls="subscriptions" role="tab" data-toggle="tab" onclick="loadPhotos(4)">Subscriptions</a></li>';
    mysql_free_result($result);
  } else {
    echo '<li><a href="#" aria-controls="profile" class="disabled">Profile</a></li>
          <li><a href="#" aria-controls="messages" class="disabled">Messages</a></li>
          <li><a href="#" aria-controls="subscriptions" class="disabled">Subscriptions</a></li>';
  }
?>
          </ul>
          <div class="navbar-form navbar-left">
            <input type="text" class="form-control" placeholder="Keyword search" id="search-keywords">
            
            <button type="submit" class="btn btn-default" onclick="loadPhotos(3)">Submit</button></div>

          <ul class="nav navbar-nav navbar-right">
            <li><a href="#" data-toggle="modal" data-target="#login">Sign in</a></li>
            <li><a href = "#logout" data-toggle="modal"><span class = "glyphicon glyphicon-log-out"></span> Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="tab-content">

    <!--***** HOME *****-->
      <div role="tabpanel" class="tab-pane active" id="home">
        <div class="row" style="margin-bottom:20px;"><center>
<?php
  if(isset($_SESSION['username'])){
    echo '<button type="button" class="btn btn-default" data-toggle="modal" data-target="#uploadModal">Upload</button>';
  } else {
    echo '<button type="button" class="btn btn-default" disabled="disabled">Upload</button>';
  }
?>
            <div class="btn-group" role="group">
            <!--<div class="dropdown">-->
              <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Sort by 
              <span class="caret"></span></button>
              <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                <li role="presentation"><a role="menuitem" href="#" onclick="loadPhotos(10)">Upload date</a></li>
                <li role="presentation"><a role="menuitem" href="#" onclick="loadPhotos(1)">View count</a></li>
              </ul>
            </div>

            <div class="btn-group" role="group">
              <button class="btn btn-default dropdown-toggle" type="button" id="menu2" data-toggle="dropdown">Browse by 
              <span class="caret"></span></button>
              <ul class="dropdown-menu" role="menu" aria-labelledby="menu2">
                <li role="presentation"><a role="menuitem" href="#" onclick="loadPhotos(0)">All Categories</a></li>
                <li role="presentation"><a role="menuitem" href="#" onclick="loadPhotos(7)">Image</a></li>
                <li role="presentation"><a role="menuitem" href="#" onclick="loadPhotos(8)">Video</a></li>
                <li role="presentation"><a role="menuitem" href="#" onclick="loadPhotos(9)">Audio</a></li>
              </ul>
            </div>

          </center>
        </div>

        <div class="container">
          <div class="row" style="display:flex; flex-wrap: wrap;" id="front-photos">
          </div>
        </div>
      </div>

    <!--***** PROFILE *****-->
      <div role="tabpanel" class="tab-pane" id="profile">        
        <div class="jumbotron">
          <div class="media">
            <div class="col-lg-4">
              <center><img class="media-object" src="./imgs/preview_default_profile.png" alt="profile picture"></center>
            </div>
            <div class="col-lg-8">
<?php
  if (isset($_SESSION['username'])){
    $username = $_SESSION['username'];

	$result = mysql_query("SELECT * FROM members WHERE username = '$username'")
		or die(mysql_error()); 

  $row = mysql_fetch_array($result);
      echo'
        <h1 class="media-heading">'.$row[4].' '.$row[5].'</h1>
      ';


    echo '<h2 class="media-heading">('.$username.')</h2><br>';
    echo '<h4 class="media-heading">Email: '.$row[3].'</h4>';
  } else {
    echo '<h1 class="media-heading">First Last</h1>';
  }
?>
              <br><br>
              <button type="button" class="btn btn-default" data-toggle="modal" data-target="#updateModal">Update User Information</button>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-2">
            <center><h2>Friends</h2></center>
              <div class="container" id="profile-contacts">

              </div>
          </div>
          <div class="col-lg-10">
            <center><h2>Uploads</h2></center>
            <div class="container">
              <div class="row" style="display:flex; flex-wrap: wrap;" id="profile-photos">

              </div>
            </div><hr>
            <center><h2>Favorites</h2></center>
            <div class="container">
              <div class="row" style="display:flex; flex-wrap: wrap;" id="profile-favorites">

              </div>
            </div><hr>
            <center><h2>Playlists</h2></center>
            <div class="container" id="profile-playlists">

            </div>
          </div>

        </div>
      </div>

    <!--***** MESSAGES *****-->
      <div role="tabpanel" class="tab-pane" id="messages">
        <center><button type="button" class="btn btn-default" data-toggle="modal" data-target="#messageModal">Send a Message</button></center><br><br>
        <div class="container">
            <center><table class="table table-striped table-bordered" id="message-table">
            </table></center>
        </div>
      </div>

    <!--***** SUBSCRIPTIONS *****-->
      <div role="tabpanel" class="tab-pane" id="subscriptions">
        <div class="container" id="sub-photos">

        </div>
      </div>

    </div>


    <!--***** SIGN IN MODAL WINDOW *****-->
    <div class="modal fade" id="login" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <form class = "form-horizontal"  action="index.php" method="post">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Sign in or <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#signup">create an account</a>.</h4>
          </div>
          <div class="modal-body">
            <div class = "form-group">
              <label for = "loginUsername" class = "col-lg-3 control-label">Username: </label>
              <div class = "col-lg-9">
                <input type = "text" class = "form-control" name = "loginUsername" placeholder = "Username">
              </div>
            </div>
            <div class = "form-group">
              <label for = "loginPassword" class = "col-lg-3 control-label">Password: </label>
              <div class = "col-lg-9">
                <input type = "password" class = "form-control" name = "loginPassword" placeholder = "Password">
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <a class = "btn btn-default" data-dismiss = "modal">Close</a>
            <input class="btn btn-primary" name="login" type="submit" value="Sign In">
            <!--<button type="button" class="btn btn-default" data-dismiss="modal">Sign in</button>-->
          </div>
          </form>
        </div>
      </div>
    </div>

    <!--***** REGISTER MODAL WINDOW *****-->
    <div class="modal fade" id="signup" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <form class = "form-horizontal"  action="index.php" method="post">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Register for MeTube.</h4>
          </div>
          <div class="modal-body">
            <div class = "form-group">
              <label for = "regFirst" class = "col-lg-3 control-label">First Name: </label>
              <div class = "col-lg-9">
                <input type = "text" class = "form-control" name = "regFirst" placeholder = "First Name">
              </div>
            </div>
            <div class = "form-group">
              <label for = "regLast" class = "col-lg-3 control-label">Last Name: </label>
              <div class = "col-lg-9">
                <input type = "text" class = "form-control" name = "regLast" placeholder = "Last Name">
              </div>
            </div>
            <div class = "form-group">
						  <label for = "regEmail" class = "col-lg-3 control-label">Email: </label>
							<div class = "col-lg-9">
								<input type = "email" class = "form-control" name = "regEmail" placeholder = "user@example.com">
						  </div>
						</div>
            <div class = "form-group">
              <label for = "regUsername" class = "col-lg-3 control-label">Username: </label>
              <div class = "col-lg-9">
                <input type = "text" class = "form-control" name = "regUsername" placeholder = "Username">
              </div>
            </div>
            <div class = "form-group">
              <label for = "regP1" class = "col-lg-3 control-label">Password: </label>
              <div class = "col-lg-9">
                <input type = "password" class = "form-control" name = "regP1" placeholder = "Password">
              </div>
            </div>
            <div class = "form-group">
              <label for = "regP2" class = "col-lg-3 control-label">Confirm: </label>
              <div class = "col-lg-9">
                <input type = "password" class = "form-control" name = "regP2" placeholder = "Password">
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <a class = "btn btn-default" data-dismiss = "modal">Close</a>
            <input class="btn btn-primary" name="register" type="submit" value="Register">
          </div>
        </form>
        </div>
      </div>
    </div>

    <!--***** MESSAGE MODAL WINDOW *****-->
    <div class="modal fade" id="mediaModal" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>           
             <h4 class="modal-title">Send a Message</h4>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="messageUsername" class="col-lg-3 control-label">To: </label>
                <div class="col-lg-9">
                  <input type="text" class="form-control" name="messageUsername" placeholder="Username">
              </div>
            </div>
            <div class="form-group">
              <label for="message" class="col-lg-3 control-label">Message: </label>
                <div class="col-lg-9">
                  <textarea class="form-control" rows="6" name="message" placeholder="Message"></textarea>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <a class="btn btn-default" data-dismiss="modal">Close</a>
            <!--<input class="btn btn-primary" name="sendMessage" type="submit" value="Send">-->
          </div>
        </div>
      </div>
    </div>

    <!--***** UPDATE MODAL WINDOW *****-->
    <div class="modal fade" id="updateModal" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <form class="form-horizontal" action="index.php" method="post">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Update user information.</h4>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="updateFirst" class="col-lg-3 control-label">First Name: </label>
              <div class="col-lg-9">
                <input type="text" class="form-control" name="updateFirst" placeholder="First Name">
              </div>
            </div>
            <div class = "form-group">
              <label for = "updateLast" class = "col-lg-3 control-label">Last Name: </label>
              <div class = "col-lg-9">
                <input type = "text" class = "form-control" name = "updateLast" placeholder = "Last Name">
              </div>
            </div>
            <div class = "form-group">
						  <label for = "updateEmail" class = "col-lg-3 control-label">Email: </label>
							<div class = "col-lg-9">
								<input type = "email" class = "form-control" name = "updateEmail" placeholder = "user@example.com">
						  </div>
						</div>
            <div class = "form-group">
              <label for = "updateP1" class = "col-lg-3 control-label">Password: </label>
              <div class = "col-lg-9">
                <input type = "password" class = "form-control" name = "updateP1" placeholder = "Password">
              </div>
            </div>
            <div class = "form-group">
              <label for = "updateP2" class = "col-lg-3 control-label">Confirm: </label>
              <div class = "col-lg-9">
                <input type = "password" class = "form-control" name = "updateP2" placeholder = "Password">
              </div>
            </div>

          </div>
          <div class="modal-footer">
            <a class = "btn btn-default" data-dismiss = "modal">Close</a>
            <input class="btn btn-primary" name="update" type="submit" value="Update">
          </div>
        </form>
        </div>
      </div>
    </div>

    <!--***** MESSAGE MODAL WINDOW *****-->
    <div class="modal fade" id="messageModal" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <form class="form-horizontal" action="index.php" method="post">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>           
             <h4 class="modal-title">Send a Message</h4>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="messageUsername" class="col-lg-3 control-label">To: </label>
                <div class="col-lg-9">
                  <input type="text" class="form-control" name="messageUsername" placeholder="Username">
              </div>
            </div>
            <div class="form-group">
              <label for="message" class="col-lg-3 control-label">Message: </label>
                <div class="col-lg-9">
                  <textarea class="form-control" rows="6" name="message" placeholder="Message"></textarea>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <a class="btn btn-default" data-dismiss="modal">Close</a>
            <input class="btn btn-primary" name="sendMessage" type="submit" value="Send">
          </div>
          </form>
        </div>
      </div>
    </div>

    <!--***** UPLOAD MODAL WINDOW *****-->
    <div class="modal fade" id="uploadModal" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
        <form method="post" action="media_upload_process.php" enctype="multipart/form-data">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Upload media.</h4>
          </div>
          <div class="modal-body">
            <input name="file" type="file" size="50"/><br>
            <label for="title">Title</label>
			      <input type="text" class="form-control" name="title" placeholder="Title"><br>
            <label for="description">Description</label>
            <textarea class="form-control" rows="3" name="description" placeholder="Description"></textarea><br>
            <label for="keywords">Keywords (seperated by commas)</label>
            <input type="text" class="form-control" name="keywords" placeholder="Keywords e.g. Funny, Cat, Landscape"><br>
          </div>
          <div class="modal-footer">
            <a class = "btn btn-default" data-dismiss = "modal">Close</a>
            <input class="btn btn-primary" name="upload" type="submit" value="Upload">
          </div>
        </form>
        </div>
      </div>
    </div>

    <!--***** LOGOUT MODAL WINDOW *****-->
    <div class = "modal fade" id = "logout" role = "dialog">
      <div class = "modal-dialog">
        <div class = "modal-content">
        <form class = "form-horizontal" action="index.php" method="post">
          <div class = "modal-header">
            <h4>Logout</h4>
          </div>
          <div class = "modal-body">
            Are you sure you want to log out?
          </div>
          <div class = "modal-footer">
            <input class="btn btn-primary" name="logout" type="submit" value="Yes">
            <a class = "btn btn-default" data-dismiss = "modal">No</a>
          </div>
        </form>
        </div>
      </div>
    </div>

    <!--***** GALLERY MODAL WINDOW *****-->
    <div class="modal fade" id="image-gallery" tabindex="-1" role="dialog" aria-labelledby="galleryLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content" id="test-gallery">
      </div>
    </div>
  </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="./backend.js"></script>
  </body>
</html>
