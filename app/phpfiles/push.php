<?php
require "init.php";
$sender=$_POST['sender'];
$receiver=$_POST['receiver'];
$message=$_POST['message'];

// API access key from Google API's Console
define( 'API_ACCESS_KEY', 'AAAA0koDHOI:APA91bFSzkyk2_r-vnzsreNfHtEH5PCsM_4CpK67lZVM8lUhC0FyCnniUNeASg0BEdpmwRXrSxEpI9u7tk4WRu3yJDq6reN0XRGziI-WH2h96tDoJ36_qLGGSXNmUK6v_bK2vAWIlyqE');

// prep the bundle
$query="select * from register where mobile='$receiver'";
$result=mysqli_query($conn,$query);


if($test=$result->num_rows==0)
{
	echo "Your Friend Not Installed the Applicaton";
	mysqli_close($conn);
}
else
{
	$query3="insert into chat (sender,receiver,message) values('$sender','$receiver','$message')";
	mysqli_query($conn,$query3);


	$row=mysqli_fetch_row($result);
	$token=$row[2];

	mysqli_close($conn);

$msg='{
  "to" :"'.$token.'",
  "priority" : "high",
  "data":{
  "message":"'.$message.'",
  "title":"'.$sender.'",
  "tag":"single"
  },
}';




$headers = array
(
	'Authorization: key=' . API_ACCESS_KEY,
	'Content-Type: application/json'
);

$ch = curl_init();
curl_setopt( $ch,CURLOPT_URL, 'https://android.googleapis.com/gcm/send' );
curl_setopt( $ch,CURLOPT_POST, true );
curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
curl_setopt( $ch,CURLOPT_POSTFIELDS, $msg );
$result = curl_exec($ch );
curl_close( $ch );
echo "message sent";
}