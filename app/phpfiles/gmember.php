<?php
require "init.php";
$gname=$_POST['gname'];
$query="select * from gmember where gname='$gname'";
$res=mysqli_query($conn,$query);
$list=array();
$num=$res->num_rows;
for($i=0; $i<$num; $i++)
{
	$row=mysqli_fetch_row($res);
	$name=$row[1];
	$number=$row[2];
	$list[$i]=$name."@".$number;
}
$data=implode("#",$list);
echo $data;
?>