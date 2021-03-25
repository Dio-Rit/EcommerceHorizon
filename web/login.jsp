<%-- 
    Document   : login
    Created on : 22 de mar de 2021, 22:39:35
    Author     : yNot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
  

    <link href="../src/java/css/bootstrap.min.css" rel="stylesheet">
    <link href="../src/java/css/signin.css" rel="stylesheet">
    <link href="../src/java/css/login.css" rel="stylesheet">
    
  </head>
  <body class="text-center">
    
<main class="form-signin">
    
  <form>
    
    <label for="inputText" class="visually-hidden">Login</label>
    <input type="text" id="inputText" class="form-control" placeholder="Digite seu login" required>
    
    <label for="inputPassword" class="visually-hidden">Senha</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Digite sua senha" required>

    <button class="w-100 btn btn-lg btn-primary" type="submit">Entrar</button>
    
  </form>
</main>

  </body>
</html>
