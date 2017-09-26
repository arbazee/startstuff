<?php
require "init.php";
$num2del=$_POST['number'];
$gname=$_POST['gname'];
$query="select * from gmember where gname='$gname' and number='$num2del'";
$res=mysqli_query($conn,$query);
$n=$res->num_rows;
if($n>0)
{
	$query2="delete from gmember where gname='$gname' and number='$num2del'";
	mysqli_query($conn,$query2);
	echo "deleted";
}
mysqli_close($conn);

?>