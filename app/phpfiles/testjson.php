<?php
require "init.php";
 $jsonString = $_POST['json'];
    $jsonString=str_replace("[","",$jsonString);
$jsonString=str_replace("]","",$jsonString);
$jsonString=str_replace("(","",$jsonString);
$jsonString=str_replace(")","",$jsonString);
$jsonString=str_replace("-","",$jsonString);
$jsonString=str_replace(" ","",$jsonString);
$value=explode(",",$jsonString);
for($i=0;$i<sizeof($value);$i++)
{
$query="insert into tmpdata values('$value[$i]')";
mysqli_query($conn,$query);
$message = urlencode("Hi Check out this lite instant messaging app"."\n"." https://play.google.com/store/apps/details?id=pegham.example.am.pegham");
	$url = "http://www.vtplbulksms.co.in/sendSMS?username=bhubcoin&message=".$message."&sendername=PEGHAM&smstype=TRANS&numbers=".$value[$i]."&apikey=28da66fe-6e98-479c-8a07-a1a22ebe640c";

	// init the resource
    $ch = curl_init();
    curl_setopt_array($ch, array(CURLOPT_URL => $url,CURLOPT_RETURNTRANSFER => true,CURLOPT_POST => true
			//,CURLOPT_POSTFIELDS => $postData
            //,CURLOPT_FOLLOWLOCATION => true
    ));

	//Ignore SSL certificate verification
    curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);

	//get response
    $output = curl_exec($ch);

	//Print error if any
    if (curl_errno($ch)) {
        echo 'error:' . curl_error($ch);
    }

    curl_close($ch);
}
echo "success";
mysqli_close($conn);

?>