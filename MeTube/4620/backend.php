<?php
session_start();
include_once "new_function.php";

  $flag = strval($_POST['flag']);

  switch($flag){
    case 'view_count':
      $media_id = strval($_POST['media_id']);

      $update = 'UPDATE media SET view_count = view_count+1 WHERE media_id='.$media_id;
      $result = mysql_query($update)
        or die(" Error: " .mysql_error());
      break;

    case 'delete_msg':
      $msg_id = strval($_POST['msg_id']);

      $query = 'DELETE FROM messages WHERE message_id='.$msg_id;
      $result = mysql_query($query)
        or die(" Error: " .mysql_error());
      break;

    case 'comment':
      $media_id = strval($_POST['media_id']);
      $username = $_SESSION['username'];
      $text = strval($_POST['text']);

      $query = 'INSERT INTO comments VALUES (0,'.$media_id.',"'.$username.'","'.$text.'")';
      $result = mysql_query($query)
        or die(" Error: " .mysql_error());
      break;

    case 'load_comments':
      $media_id = strval($_POST['media_id']);

      $query = 'SELECT * FROM comments WHERE media_id='.$media_id;
      $result = mysql_query($query)
        or die(" Error: " .mysql_error());

      while($row = mysql_fetch_array($result)){
        echo '<p><b>'.$row[2].' </b>'.$row[3].'</p>';
      } 
      break;

    case 'load_messages':

      echo'<tr>
            <th></th>
            <th>From</th>
            <th>Message</th>
            <th><center>Delete</center></th>
           </tr>';

      $username = $_SESSION['username'];

      $update = 'SELECT * FROM messages WHERE receiver="'.$username.'"';
      $result = mysql_query($update)
        or die(" Error: " .mysql_error());

      while($row = mysql_fetch_array($result)){
        echo '<tr>
              <td><center><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></center></td>
              <td>'.$row['sender'].'</td>
              <td>'.$row['message'].'</td>
              <td><center><button class="btn btn-default" onclick="deleteMsg('.$row[0].')">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></buton></center>
              </td>
            </tr>
            ';
      }
      break;

    case 'load_gallery':
      $media_id = strval($_POST['media_id']);

      $query = 'SELECT * FROM media WHERE media_id='.$media_id;
      $result = mysql_query($query)
        or die(" Error: " .mysql_error());

      $row = mysql_fetch_array($result);
        echo '<div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h3 class="modal-title" id="image-gallery-title">'.$row[4].'</h3>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class ="col-md-8" id="gallery-image">';
        switch ($row[2]){
          case 'audio/mp3':
            echo'<center><img id="image-gallery-image" class="img-responsive" src="./imgs/play.png">
                  <audio controls>
                    <source src="'.$row[3].'" type="audio/mpeg">
                  Your browser does not support the audio element.
                  </audio></center>';
            break;
          case 'video/mp4':
            echo '<center><iframe id="image-gallery-image" class="img-responsive" src="'.$row[3].'"></iframe></center>';
            break;
          default:
            echo '<center><img id="image-gallery-image" class="img-responsive" src="'.$row[3].'"></center>';
            break;
        }

        echo'</div>
              <div class ="col-md-4">
                <h4><i>'.$row[5].'</i></h4>
                <h5>Uploaded by: '.$row[1];
        if(isset($_SESSION['username'])){
          $js_call = "'".$row[1]."'";
          echo '<br>(<a href="#" onclick="addFriend('.$js_call.')" data-dismiss="modal">add friend</a> / <a href="#" onclick="channelSubscribe('.$js_call.')" data-dismiss="modal">subscribe</a>)';
        } 

        echo '</h5><hr>
                <h4>Comments</h4>
                <div class="container" id="gallery-comments-section">
                </div>';
        if(isset($_SESSION['username'])){
          echo '<input type="text" id="comments" comment-id="'.$row[0].'" placeholder="Write a comment...">';
        }

        echo'</div>
            </div>
          </div>
          <div class = "modal-footer">
            <center><a href="'.$row[3].'" download>Download</a></center>
          </div>
        </div>';
      break;

    case 'subscribe':
      $channel = strval($_POST['channel']);
      $username = $_SESSION['username'];

      $query = "select * from subscriptions where subscriber='".$username."' and subscribed_to='".$channel."'";
      $result = mysql_query( $query )
        or die(" Error: " .mysql_error());

      if(mysql_num_rows($result) == 0){
        $query = 'INSERT INTO subscriptions VALUES (0,"'.$username.'","'.$channel.'")';
        $result = mysql_query($query)
          or die(" Error: " .mysql_error());
      }
      break;

    case 'add_friend':
      $friend = strval($_POST['friend']);
      $username = $_SESSION['username'];

      $query = "select * from contacts where rhs='".$username."' and lhs='".$friend."'
                  union
                select * from contacts where rhs='".$friend."' and lhs='".$username."'";
      $result = mysql_query( $query )
        or die(" Error: " .mysql_error());

      if(mysql_num_rows($result) == 0 && $friend != $username){
        $query = 'INSERT INTO contacts VALUES (0,"'.$username.'","'.$friend.'",1)';
        $result = mysql_query($query)
          or die(" Error: " .mysql_error());
      }

      break;

    case 'load_edit':
      $media_id = strval($_POST['media_id']);

      $query = 'SELECT * FROM media WHERE media_id='.$media_id;
      $result = mysql_query($query)
        or die(" Error: " .mysql_error());

      $row = mysql_fetch_array($result);
        echo '<div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h3 class="modal-title" id="image-gallery-title">'.$row[4].'</h3>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class ="col-md-8" id="gallery-image">';
        switch ($row[2]){
          case 'video/mp4':
            echo '<center><iframe id="image-gallery-image" class="img-responsive" src="'.$row[3].'"></iframe></center></div>';
            break;
          default:
            echo '<center><img id="image-gallery-image" class="img-responsive" src="'.$row[3].'"></center></div>';
            break;
        }

        echo'<div class ="col-md-4">
                <h4><i>'.$row[5].'</i></h4>
                <h5>Uploaded by: '.$row[1];

        echo '</h5><hr>
                <h4>Add to playlist</h4>
                <div class="container" id="sidebar">
                </div>';
        //if(isset($_SESSION['username'])){
          //echo '<input type="text" id="testsidebar" comment-id="'.$row[0].'" placeholder="Write a comment...">';
        //}

        echo'</div>
            </div>
          </div>
        </div>';
      break;

    case 'load_side':
      $media_id = strval($_POST['media_id']);
      $username = $_SESSION['username'];
      $query = 'SELECT * FROM playlists WHERE username="'.$username.'"'; // GROUP BY playlist_id
      $result = mysql_query($query)
        or die(" Error: " .mysql_error());

      echo '<div class="input-group"><input type="radio" name="playlist-option" value="create" checked>
              <input type="text" id="new-playlist" placeholder="Create a new playlist"><br><br>
            <input type="radio" name="playlist-option" value="choose">
					    <select id="ext-playlists">
						    <option disabled selected>Choose from existing:</option>';
						    //<option value="favorites">Favorites</option>';
      while($row = mysql_fetch_array($result)){
        echo '<option value="'.$row[0].'">'.$row[1].'</option>';
      } 
				echo'	</select><br><br><center><button type="button" class="btn btn-default" onclick="addToPlaylist('.$media_id.');loadPhotos(6)" data-dismiss = "modal">Add</button></center></div>';
      break;

    case 'add_to_playlist':
      $option = strval($_POST['option']);
      $username = $_SESSION['username'];
      $media_id = strval($_POST['media_id']);

      switch ($option){
        case "create":
          $list = strval($_POST['playlist']);
          $query = 'INSERT INTO playlists VALUES (0,"'.$list.'","'.$username.'")';
          $result = mysql_query($query)
            or die(" Error: " .mysql_error());
          echo 'create list '.$list.' succesful';
          $query = 'INSERT INTO playlist_media (pm_id, playlist_id, media_id) 
                    VALUES(0, (SELECT playlist_id FROM playlists WHERE list_name="'.$list.'" and username="'.$username.'"), '.$media_id.')';
          $result = mysql_query($query)
            or die(" Error: " .mysql_error());
          echo 'insert into list succesful';
          break;
        case "add":
          $list = intval($_POST['playlist']);
          $query = 'INSERT INTO playlist_media VALUES (0,'.$list.','.$media_id.')';
          $result = mysql_query($query)
            or die(" Error: " .mysql_error());
          echo 'add success';
          break;
        default:
          echo '<script>console.log("In backend.php: add_to_playlist failed");</script>';
          break;
      }
      break;

      case 'show_friends':
        $username = $_SESSION['username'];
        $query = 'select rhs as f from contacts where lhs="'.$username.'"
                    union
                  select lhs as f from contacts where rhs="'.$username.'"';
        $result = mysql_query($query)
          or die(" Error: " .mysql_error());

        while($row = mysql_fetch_array($result)){
          echo '<h4><span class="glyphicon glyphicon-user" aria-hidden="true"></span> '.$row[0].'</h4><br>';
        } 
        break;

    default:
      echo 'In backend.php: default flag';
      break;
  }
?>
