<!DOCTYPE html>
<html lang="en">
  <head>
  <meta charset="utf-8">
  <title>Login</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"/>

    <!-- Personalized stylesheet -->
    <style type="text/css">
        .margin-top-30px{
            margin-top: 30px;
        }
    </style>

  </head>
  <body>

    <div class="container">
      <div class="row">
        
        <div class="col-md-4">
        </div>
        
        <!-- Login form -->
        <div class="col-md-4 jumbotron margin-top-30px">
          <!-- Username text field -->
          <div class="form-group">
            <label for="username">Username:</label>
            <input name="username" class="form-control" id="username" placeholder="Username" maxlength="17">
          </div>
          <!-- Password text field -->
          <div class="form-group">
            <label for="password">Password:</label>
            <input name="password" type="password" class="form-control" id="password" placeholder="Pasword" maxlength="17">
          </div>
          <!-- Login button -->
          <button type="button" class="btn btn-primary" id="login">Login</button>
      </div>

        <div class="col-md-4">
        </div>
      
      </div>
    </div>
    <!-- Development account; User:dev-usr;Password:dev-pass;-->

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

    <script type="text/javascript">
      
      function Login() {
        $.ajax({
        type: "POST",
        url: "authenticate.php",
        data: {
            username: $("#username").val(),
            password: $("#password").val(),
        }
        }).done(function(result){
          if(result == 1)
            window.location="home.php"; 
          else
            alert(result == 0 ? "Wrong credentials" : result);
        });
      }

      function UserLogged () {
        $.ajax({
        type: "GET",
        url: "userLogged.php"
        }).done(function(result){
          if(result == 0)
            alert("Your session may have expired please login again");
        });
      }

      $(document).ready(function(){
        UserLogged();

        $("#login").on("click", function () {
          Login();
        })
      });

    </script>

  </body>
</html>