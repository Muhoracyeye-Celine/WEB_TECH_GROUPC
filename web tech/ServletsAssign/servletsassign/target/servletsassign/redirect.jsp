<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Fetch & Redirect</title>
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
<form action="redirect" method="get">
    <h3>Google Redirect</h3>
    <input type="text" name="q" placeholder="Search term" required>
    <button type="submit">Fetch</button>
</form>
</body>
</html>
