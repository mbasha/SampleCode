<?php
session_start();
include_once "new_function.php";

/******************************************************
*
* upload document from user
*
*******************************************************/

$username=$_SESSION['username'];
$title = $_POST['title'];
$description = $_POST['description'];


//Create Directory if doesn't exist
if(!file_exists('uploads/')){
	mkdir('uploads/');
	chmod('uploads', 0755);
}
$dirfile = 'uploads/'.$username.'/';
if(!file_exists($dirfile))
	mkdir($dirfile);
	chmod($dirfile, 0755);
	if($_FILES["file"]["error"] > 0 )
	{ 	$result=$_FILES["file"]["error"];} //error from 1-4
	else
	{
		$upfile = $dirfile.urlencode($_FILES["file"]["name"]);
	  
	  if(file_exists($upfile))
	  {
	  	$result="5"; //The file has been uploaded.
	  }
	  else{
			if(is_uploaded_file($_FILES["file"]["tmp_name"]))
			{
				if(!move_uploaded_file($_FILES["file"]["tmp_name"],$upfile))
				{
					$result="6"; //Failed to move file from temporary directory
				}
				else /*Successfully upload file*/
				{
					//insert into media table
					$insert = "insert into media values(NULL,'$username','".$_FILES["file"]["type"]."','$upfile','$title', '$description',NOW(), 0, 0)";

					$queryresult = mysql_query($insert)
						  or die("Insert into Media error in media_upload_process.php " .mysql_error());

					$result="0";
					chmod($upfile, 0644);

					$keywordText = $_POST["keywords"];
          if ($keywordText != ""){
					  $keywordList = explode(",", $keywordText);
					  $numKeywords = count($keywordList);
					  $query = "select media_id from media where username='$username' and path='$upfile'";
					  $idresult = mysql_query($query);

					  $zero = 0;
					  $mediaid = mysql_result($idresult, $zero, "media_id");
					  $i = 0;
					  while($i < $numKeywords){
						  $keyword = $keywordList[$i];
                $insertKeys = "insert into keywords values(0,'$mediaid','$keyword')";
						  $queryres = mysql_query($insertKeys);
						  $i++;
					  }
          }

				}
			}
			else  
			{
					$result="7"; //upload file failed
			}
		}
	}

?>

<meta http-equiv="refresh" content="0;url=index.php">
