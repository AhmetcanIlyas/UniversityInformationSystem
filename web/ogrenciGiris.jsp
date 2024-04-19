<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="veri.DBKatmani" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Üniversite Bilgilendirme Sistemi</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #2193b0, #6dd5ed);
        }
        
        .top-panel {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #2c3e50;
            color: #fff;
            padding: 10px 20px;
        }
        
        .logo {
            display: flex;
            align-items: center;
        }
        
        .logo img {
            width: 50px;
            margin-right: 10px;
        }
        
        .university-name {
            font-size: 24px;
        }
        
        .system-info {
            font-size: 14px;
        }
        
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            padding: 40px;
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 30%;
            max-width: 400px;
        }
      
        h1 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #333;
        }
        
        .login-form label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #666;
        }
        
        .input-group {
            margin-bottom: 20px;
        }
        
        .input-group label {
            font-size: 16px;
        }
        
        .input-group input {
            width: 95%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        
        .submit-btn {
            background-color: #3498db;
            color: #fff;
            border: none;
            border-radius: 4px;
            padding: 12px 20px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        
        .submit-btn:hover {
            background-color: #2980b9;
        }
        .logo a {
            text-decoration: none;
            color: #fff; /* Beyaz renk */
            transition: color 0.3s ease;
        }
    </style>
</head>
<body>
    <div class="top-panel">
        <div class="logo">
            <a href="giris.jsp" class="logo">
                <img src="resimler/logo.png" alt="Üniversite Logosu">
                <div class="university-name">Phoenix Üniversitesi</div>
            </a>
        </div>
        <div class="system-info">Üniversite Bilgilendirme Sistemi</div>
    </div>
    <div class="container">
        <h1>Öğrenci Girişi</h1>
        <form method="post" class="login-form">
            <div class="input-group">
                <label for="username">E-Posta:</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="input-group">
                <label for="password">Parola:</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <input type="submit" value="Giriş Yap" onclick="giris()" class="submit-btn">
        </form>
    </div>
<% DBKatmani dbkatmani = new DBKatmani(); %>
<script>
    function giris(){
      <% String email = request.getParameter("email"); 
         String password = request.getParameter("password"); 
         String girisBilgi = dbkatmani.getOgrenciGiris(email);
         if (girisBilgi.equals(password)) {
            String isim = dbkatmani.getOgrenciIsim(email);;
            session.setAttribute("email", email);
            session.setAttribute("username", isim);
            response.sendRedirect("ogrenciAnaSayfa.jsp"); 
            }else if(girisBilgi!=password && password!=null){
            response.sendRedirect("uyariGiris.jsp"); 
            }
       %>
    }
</script>
</body>
</html>

