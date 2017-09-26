<?php
require "init.php";
$sender=$_POST['sender'];
$gname=$_POST['gname'];
$message=$_POST['message'];
$flag=0;
if($gname=="Startup" || $gname=="Entrepreneur" || $gname=="Chartered Accountant" || $gname=="Teachers" || $gname=="Human Resources"|| $gname=="Lawyers")
$flag=1;

$reg="select * from gmember where gname='$gname' and number='$sender'";
$checkthis=mysqli_query($conn,$reg);
$k=0;
if($k=$checkthis->num_rows<1)
{
$getname="select name from register where mobile='$sender'";
$nmres=mysqli_query($conn,$getname);
$nm=mysqli_fetch_row($nmres);
$uname=$nm[0];
$ins="insert into gmember values('$gname','$uname','$sender')";
mysqli_query($conn,$ins);
}


$getn="select * from register where mobile='$sender'";
$sendername=mysqli_query($conn,$getn);
$row5=mysqli_fetch_row($sendername);
$sname=$row5[0];
$gmember="select * from gmember where gname='$gname'";
$result2=mysqli_query($conn,$gmember);
$total=0;
$total=$result2->num_rows;
for($i=0; $i<$total; $i++)
{
$row=mysqli_fetch_row($result2);
$num=$row[2];
if($num!=$sender)
{
$getoken="select * from register where mobile='$num'";
$res3=mysqli_query($conn,$getoken);
$row2=mysqli_fetch_row($res3);
$token=$row2[2];
// API access key from Google API's Console
define( 'API_ACCESS_KEY', 'AAAA0koDHOI:APA91bFSzkyk2_r-vnzsreNfHtEH5PCsM_4CpK67lZVM8lUhC0FyCnniUNeASg0BEdpmwRXrSxEpI9u7tk4WRu3yJDq6reN0XRGziI-WH2h96tDoJ36_qLGGSXNmUK6v_bK2vAWIlyqE');

$msg='{
  "to" :"'.$token.'",
  "priority" : "high",
  "data":{
  "tag":"group",
  "gname":"'.$gname.'",
  "flag":"'.$flag.'",
  "message":"'.$message.'",
  "title":"'.$sname.'"
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
}
}
echo "message sent";
mysqli_close($conn);
?>