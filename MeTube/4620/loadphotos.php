<?php

session_start();
include_once "new_function.php";

  $context = intval($_POST['context']);

  $gallery = "loadGallery";

  switch($context){
    case 0: //default
      $query = "SELECT * FROM media ORDER BY upload_date";
      break;
    case 1: //view_count
      $query = "SELECT * FROM media ORDER BY view_count DESC";
      break;
    case 2: //profile uploads
      $query = "SELECT * FROM media WHERE username='".$_SESSION['username']."'";
      $gallery = "loadEditGallery";
      break;
    case 3: //keyword search
      $keywords = strval($_POST['keywords']);
      if ($keywords != ""){
        $keyword_tokens = explode(' ', $keywords);
        $keyword_tokens = array_map(
            function($keyword) {
                return mysql_real_escape_string(trim($keyword));
            }, 
            $keyword_tokens
        );
        $query = "SELECT * FROM media,keywords WHERE (keywords.keyword LIKE'%";
        $query .= implode("' OR keywords.keyword LIKE '%", $keyword_tokens) . "'";
        $query .= ") AND keywords.media_id = media.media_id GROUP BY media.media_id";
      } else {
        $query = "SELECT * FROM media";
      }
      break;

    case 4: // subscriptions
      $query = "SELECT * FROM media, subscriptions
                  WHERE subscriptions.subscriber='".$_SESSION['username']."'
                    AND media.username = subscriptions.subscribed_to
                  ORDER BY subscription_id";
      break;

    case 5: // favorites
      $query = "SELECT * FROM media, playlist_media, members
                  WHERE members.username = '".$_SESSION['username']."'
                    AND members.fav_list=playlist_media.playlist_id
                    AND media.media_id = playlist_media.media_id";
      break;

    case 6: // playlists
      $query = "SELECT * FROM media, playlists, playlist_media
                  WHERE media.media_id = playlist_media.media_id
                    AND playlists.playlist_id = playlist_media.playlist_id
                    AND playlists.username = '".$_SESSION['username']."'
                    AND playlists.list_name != 'Favorites'
                  ORDER BY playlists.playlist_id";
      break;

    case 7:
      $query = "SELECT * FROM media WHERE type LIKE 'image%'";
      break;

    case 8:
      $query = "SELECT * FROM media WHERE type LIKE 'video%'";
      break;

    case 9:
      $query = "SELECT * FROM media WHERE type LIKE 'audio%'";
      break;

    case 10: //upload_date
      $query = "SELECT * FROM media ORDER BY upload_date DESC";
      break;

    default:
      $query = "SELECT * FROM media";
      break;
  }
	$result = mysql_query($query)
		or die(mysql_error()); 

  $sub_user = "";
  $pl = "";

	while ($row = mysql_fetch_array($result)){
		if ($row[3] != null){

      if ($context == 4 && $sub_user != $row[1]){
        $sub_user = $row[1];
        echo '</div><div class="row"><h2>'.$sub_user.'</h2></div><div class="row" style="display:flex; flex-wrap: wrap;">';
      }

      if ($context == 6 && $pl != $row[10]){
        $pl = $row[10];
        echo '</div><div class="row"><h4>'.$pl.'</h4></div><div class="row" style="display:flex; flex-wrap: wrap;">';
      }


      switch($row[2]){
        case 'audio/mp3':
          echo'
            <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
              <a class="thumbnail" href="#" data-image-id="'.$row[0].'" data-toggle="modal" data-title="'.$row[4].'" data-caption="'.$row[5].'" data-image="'.$row[3].'" data-type="'.$row[2].'" data-user="'.$row[1].'" data-target="#image-gallery" onclick="upViewCount('.$row[0].');'.$gallery.'('.$row[0].')">
                <img class="img-responsive" src="./imgs/play.png" alt="'.$row[4].'"></a>
                <div class="caption text-center">
                  <h4>'.$row[4].'</h4>
                </div>
            </div>
          ';
          break;
        case 'video/mp4':
          echo'
            <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
              <a class="thumbnail" href="#" data-image-id="'.$row[0].'" data-toggle="modal" data-title="'.$row[4].'" data-caption="'.$row[5].'" data-image="'.$row[3].'" data-type="'.$row[2].'" data-user="'.$row[1].'" data-target="#image-gallery" onclick="upViewCount('.$row[0].');'.$gallery.'('.$row[0].')">

                <video class="img-responsive" src="./'.$row[3].'" alt="'.$row[4].'"></a>

                <div class="caption text-center">
                  <h4>'.$row[4].'</h4>
                </div>
            </div>
          ';

          break;
        default:
          echo'
            <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
              <a class="thumbnail" href="#" data-image-id="'.$row[0].'" data-toggle="modal" data-title="'.$row[4].'" data-caption="'.$row[5].'" data-image="'.$row[3].'" data-type="'.$row[2].'" data-user="'.$row[1].'" data-target="#image-gallery" onclick="upViewCount('.$row[0].');'.$gallery.'('.$row[0].')">
                <img class="img-responsive" src="./'.$row[3].'" alt="'.$row[4].'"></a>
                <div class="caption text-center">
                  <h4>'.$row[4].'</h4>
                </div>
            </div>
          ';
          break;
      }

		}
	}
  mysql_free_result($result);
?>
