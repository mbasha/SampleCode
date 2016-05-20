$(document).ready(function(){
    $("#search-keywords").keyup(function(event){
        if(event.keyCode == 13){
          loadPhotos(3);
        }
    });
    loadPhotos(0);
}); 

//This function disables buttons when needed
function disableButtons(counter_max, counter_current){
    $('#show-previous-image, #show-next-image').show();
    if(counter_max == counter_current){
        $('#show-next-image').hide();
    } else if (counter_current == 1){
        $('#show-previous-image').hide();
    }
}

function loadGallery(media_id){
  var flag = 'load_gallery';
  //console.log("loading gallery for " + media_id);
  $.ajax({
    url: "backend.php",
    type: "POST",
    data: { media_id: media_id, flag: flag },
    success: function (response)	{
      $('#test-gallery').html(response);
      //console.log(response);
      loadComments(media_id);
      $("#comments").keyup(function(event){
          var flag = 'comment';
          var comment_text = $('#comments').val();
          var media_id = $('#comments').attr('comment-id');
          if(event.keyCode == 13){
              //console.log("comment: " + comment_text);
              //console.log("id: " + media_id);
              var flag = 'comment';
              $.ajax({
                url: "backend.php",
                type: "POST",
                data: { media_id: media_id, flag: flag, text: comment_text },
                success: function (response)	{
                  //console.log(response);
                  $('#comments').val('');
                  loadComments(media_id);
                }
              });
          }
      });
    }
  });
}

function loadEditGallery(media_id){
  var flag = 'load_edit';
  //console.log("loading edit gallery for " + media_id);
  $.ajax({
    url: "backend.php",
    type: "POST",
    data: { media_id: media_id, flag: flag },
    success: function (response)	{
      $('#test-gallery').html(response);
      loadEditPhoto(media_id);
    }
  });
}

function loadEditPhoto(media_id){
  var flag = 'load_side';
  $.ajax({
    url: "backend.php",
    type: "POST",
    data: { media_id: media_id, flag: flag },
    success: function (response)	{
      $('#sidebar').html(response);
    }
  });
}

function loadPhotos(context){
	var formData = new FormData();
	formData.append("context", context);

    //console.log("BALH" + $('#search-keywords').val());
  var keywords = $('#search-keywords').val();
  formData.append("keywords", keywords);

  $.ajax({
    url: "loadphotos.php",
    type: "POST",
    dataType: 'text',
    contentType: false,
    processData: false,
    cache: false,
    data: formData,
    success: function (response)	{
      switch (context){
        case 0: // sort by upload date
        case 1: // sort by view count
        case 7: // browse by image
        case 8: // browse by video
        case 9: // browse by audio
          $('#front-photos').html(response);
          break;
        case 2: // profile uploads 
          $('#profile-photos').html(response);
          loadPhotos(5);
          loadPhotos(6);
          showFriends();
          break;
        case 3:
          $('#front-photos').html(response);
          $('#search-keywords').val('')
          break;
        case 4: // subscriptions page
          $('#sub-photos').html(response);
          break;
        case 5:
          $('#profile-favorites').html(response);
          break;
        case 6:
          $('#profile-playlists').html(response);
          break;
        case 10:
          $('#front-photos').html(response);
          break;
        default:
          console.log("Error");
          break;
      }
    }
  });
}

function upViewCount(media_id){
  var flag = 'view_count';
  $.ajax({
    url: "backend.php",
    type: "POST",
    data: { media_id: media_id, flag: flag },
    success: function (response)	{
      //console.log(response);
    }
  });
}

function deleteMsg(msg_id){
  var flag = 'delete_msg';
  $.ajax({
    url: "backend.php",
    type: "POST",
    data: { msg_id: msg_id, flag: flag },
    success: function (response)	{
      //console.log(response);
      var ct = parseInt($('#msg-badge').html())-1;
      $('#msg-badge').html(ct.toString());
      loadMessages();
    }
  });
}

function loadComments(media_id){
    var flag = 'load_comments';
    //console.log("loading comments for " + media_id);
    $.ajax({
    url: "backend.php",
    type: "POST",
    data: { media_id: media_id, flag: flag },
    success: function (response)	{
      $('#gallery-comments-section').html(response);
      //console.log(response);
    }
  });
}

function loadMessages(){
  var flag = 'load_messages';
  $.ajax({
    url: "backend.php",
    type: "POST",
    data: { flag: flag },
    success: function (response)	{
      $('#message-table').html(response);
      //console.log(response);
    }
  });
}

function channelSubscribe(user_channel){
  var flag = 'subscribe';
  //console.log("You want to subscribe to " + user_channel +"'s channel.");
    $.ajax({
    url: "backend.php",
    type: "POST",
    data: { flag: flag, channel: user_channel },
    success: function (response)	{
      //$('#message-table').html(response);
      //console.log(response);
    }
  });
}

function addFriend(friend){
  console.log("adding friend!!");
  var flag = 'add_friend';
  $.ajax({
    url: "backend.php",
    type: "POST",
    data: { flag: flag, friend: friend },
    success: function (response)	{
      //$('#message-table').html(response);
      console.log(response);
      showFriends();
    }
  });
}

function showFriends(){
  var flag = 'show_friends';
  $.ajax({
    url: "backend.php",
    type: "POST",
    data: { flag: flag },
    success: function (response)	{
      $('#profile-contacts').html(response);
      //console.log(response);
    }
  });
}

function addToPlaylist(media_id){
  var flag = 'add_to_playlist';
  var option = '';
  var playlist;

  var val = $('input:radio[name=playlist-option]:checked').val();

  if (val == "create") {
    playlist = $('#new-playlist').val(); // playlist name
    option = "create";
  } else {
    playlist = $('#ext-playlists option:selected').val(); // playlist id
    option = "add";
  }

    $.ajax({
    url: "backend.php",
    type: "POST",
    data: { flag: flag, playlist: playlist, option: option, media_id: media_id },
    success: function (response)	{
      //console.log(response);
    }
  });
}
