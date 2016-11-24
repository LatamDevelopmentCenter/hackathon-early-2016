<?php
session_start();
$host = "localhost";
$user = "root";
$pass = ""; //Password here
$banco = "Hackathon_Testing_Challenge";

$conexao = mysql_connect($host, $user, $pass)
	or die("Error connecting to MySql:<br/>".mysql_error());

mysql_select_db($banco) 
	or die("Error connecting to MySql:<br/>".mysql_error());
?>

<?php
$username=$_POST['username'];
$password=$_POST['password'];

$query= mysql_query("SELECT * FROM TAB_Users WHERE LOWER(Login_User)=LOWER(LTRIM(RTRIM('$username'))) AND LOWER(Password_User)=LOWER(LTRIM(RTRIM('$password')));") 
	or die("Error executing MySql Query:<br/>".mysql_error());

$count= mysql_num_rows($query);

if($count > 0){
	$_SESSION['username'] = $username;
	echo 1;
}
else
{
	$_SESSION['username'] = null;
	echo 0;
}
?>
