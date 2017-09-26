<?php
require "init.php";
$name=$_POST['name'];
$mobile=$_POST['mobile'];
$token=$_POST['token'];
$query2="select * from register where mobile='$mobile'";
//echo $query2;
$res2=mysqli_query($conn,$query2);
$test=0;
if($test=$res2->num_rows>0)
{
	$query3="update register set token='$token' where mobile='$mobile'";
	mysqli_query($conn,$query3);
}
else
{
$query="insert into register (name,mobile,token) values('$name','$mobile','$token')";
//echo $query;
mysqli_query($conn,$query);
mysqli_close($conn);
echo "success";
}


?>