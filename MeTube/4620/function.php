<?php
include "mysqlClass.inc.php";


function user_exist_check ($username, $password, $email, $firstname, $lastname){
  $query = "select * from members where username='$username'";
	$result = mysql_query( $query );
	if (!$result){
		die ("user_exist_check() failed. Could not query the database: <br />". mysql_error());
	}	
	else {
		$row = mysql_fetch_assoc($result);
		if($row == 0){
      $query = "insert into playlists values ('0', 'Favorites', '$username')";
			$query = "insert into members (user_id, username, password, email, firstname, lastname, fav_list, profilepic)
                  values ('0', '$username','$password','$email','$firstname','$lastname',
                            (select playlist_id from playlists where username='$username'), '0')";
			//echo "insert query:" . $query;
			$insert = mysql_query( $query );
			if($insert)
				return 1;
			else
				die ("Could not insert into the database: <br />". mysql_error());		
		}
		else{
			return 2;
		}
	}
}

function user_update_check($username, $password, $email, $firstname, $lastname){
  $query = "select * from members where username='$username'";
  $result = mysql_query( $query );
  if (!$result){
    die ("user_exist_check() failed. Could not query the database: <br />". mysql_error());
  }
  else {
    if(mysql_num_rows($result) == 0){
      echo "No rows Found";
      exit();
    }
    $row = mysql_fetch_assoc($result);
    if($row != 0){
		  if($firstname != "" && $lastname != ""){
        $query = "UPDATE members SET FirstName = '$firstname', LastName = '$lastname' WHERE username='$username'";
        //echo "update query:" . $query;
        $update = mysql_query($query);
				if(!$update)
          die ("Could not update the database: <br />". mysql_error());
			}
			else if($firstname != ""){
		    $query = "UPDATE members SET FirstName = '$firstname' WHERE username='$username'";
       	//echo "update query:" . $query;
        $update = mysql_query($query);
				if(!$update)
         	die ("Could not update the database: <br />". mysql_error());
			}
			else if($lastname != ""){
				$query = "UPDATE members SET LastName = '$lastname' WHERE username='$username'";
        //echo "update query:" . $query;
        $update = mysql_query($query);
				if(!$update)
          die ("Could not update the database: <br />". mysql_error());
			}
			if($password == "" && $email == "")
				return 1;
			if($password != "" && $email != ""){
				$query = "UPDATE members SET password='$password', email='$email' WHERE username='$username'";
				//echo "update query:" . $query;
				$update = mysql_query($query);
				if($update)
					return 1;
				else
					die ("Could not update the database: <br />". mysql_error());
			}
			else if($password != ""){
				$query = "UPDATE members SET password='$password' WHERE username='$username'";
        //echo "update query:" . $query;
        $update = mysql_query($query);
        if($update)
          return 1;
        else
          die ("Could not update the database: <br />". mysql_error());
			}
			else if($email != ""){
        $query = "UPDATE members SET email='$email' WHERE username='$username'";
        //echo "update query:" . $query;
        $update = mysql_query($query);
        if($update)
          return 1;
        else
          die ("Could not update the database: <br />". mysql_error());
      }
		}
		else{
      return 2;
    }
  }
}

function user_pass_check($username, $password)
{
	
	$query = "select * from members where username='$username'";
	echo  $query;
	$result = mysql_query( $query );
		
	if (!$result)
	{
	   die ("user_pass_check() failed. Could not query the database: <br />". mysql_error());
	}
	else{
		$row = mysql_fetch_row($result);
		if(strcmp($row[1],$password))
			return 2; //wrong password
		else 
			return 0; //Checked.
	}	
}

function login($username, $password){
if($username == "" || $password == "") {
                        $login_error = "One or more fields are missing.";
                }
                else {
                        $check = user_pass_check($username,$password); // Call functions from function.php
                        if($check == 1) {
                                $login_error = "User ".$username." not found.";
                        }
                        elseif($check==2) {
                                $login_error = "Incorrect password.";
                        }
                        else if($check==0){
                                $_SESSION['username']=$username; //Set the $_SESSION['username']
                                header('Location: index.php');
                        }
                }
}

function signup($username, $password1, $password2, $email, $firstname, $lastname){
	 if( $password1 != $password2) {
                $register_error = "Passwords don't match. Try again?";
        }
        else if( $username == "" || $email == "" || $password1 == ""){
               $register_error = "Please fill in all fields";
        }
        else if(!filter_var($email, FILTER_VALIDATE_EMAIL)){
               $register_error = "Please enter a valid email address";
        }
        else {
                $check = user_exist_check($username, $password1, $email, $firstname, $lastname);
                if($check == 1){
                        //echo "Rigister succeeds";
                        $_SESSION['Username']=$username;
                        header('Location: index.php');
                }
                else if($check == 2){
                        $register_error = "Username already exists. Please user a different username.";
                }
        }
}

function update($username, $password1, $password2, $email, $firstname, $lastname){
  $error = 0;
  if( $password1 == "" && $password2 == "" && $email == "" && $firstname == "" && $lastname == ""){
    $register_error = "Please enter information to be updated";
    $error = 1;
  }
  if( $password1 != ""){
    if( $password1 != $password2) {
      $error = 1;
      $register_error = "Passwords don't match. Try again?";
    }
  }
  if( $email != ""){
    if(!filter_var($email, FILTER_VALIDATE_EMAIL)){
      $error = 1;
      $register_error = "Please enter a valid email address";
    }
  }
  if($error != 1){
    $check = user_update_check($username, $password1, $email, $firstname, $lastname);
    if($check == 1){
      //echo "Register succeeds";
      //header('Location: index.php');
    }
    else if($check == 2){
      $register_error = "Could not update profile. Sorry";
    }
  }
}

function uploadMessage($sender, $receiver, $message){
	$query = "insert into messages values ('0','$sender','$receiver','$message')";
//        echo  "insert query:" . $query;
        $result = mysql_query( $query );
	if($result)
		return 1;
	else
		return 2;
}

function updateMediaTime($mediaid)
{
	$query = "	update  media set lastaccesstime=NOW()
   						WHERE '$mediaid' = mediaid
					";
					 // Run the query created above on the database through the connection
    $result = mysql_query( $query );
	if (!$result)
	{
	   die ("updateMediaTime() failed. Could not query the database: <br />". mysql_error());
	}
}

function upload_error($result)
{
	//view erorr description in http://us2.php.net/manual/en/features.file-upload.errors.php
	switch ($result){
	case 1:
		return "UPLOAD_ERR_INI_SIZE";
	case 2:
		return "UPLOAD_ERR_FORM_SIZE";
	case 3:
		return "UPLOAD_ERR_PARTIAL";
	case 4:
		return "UPLOAD_ERR_NO_FILE";
	case 5:
		return "File has already been uploaded";
	case 6:
		return  "Failed to move file from temporary directory";
	case 7:
		return  "Upload file failed";
	}
}
	
?>
