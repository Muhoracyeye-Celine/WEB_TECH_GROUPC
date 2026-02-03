<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Login</title>
  <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
        }

        form {
            border: 1px solid #000;
            padding: 20px;
            width: 250px;
            text-align: center;
        }

        input {
            width: 100%;
            margin-bottom: 10px;
            padding: 5px;
        }
    </style>
</head>
<body>

  <form action="login" method="post">

    <h2>Login Page</h2>
    <label>Username:</label>
    <input type="text" name="username" required />
    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required />
    <br><br>

    <button type="submit">Login</button>
  </form>
</body>
</html>
