<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="veri.DBKatmani" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hoş Geldiniz</title>
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
        
        .user-info {
            font-size: 18px;
            margin-right: 20px;
        }
        
        .system-info {
            font-size: 14px;
        }
        
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            padding: 20px;
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 80%;
            max-width: 800px;
        }
      
        h1 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #333;
        }
        
        .user-panel {
            display: flex;
            align-items: center;
            padding: 10px 20px;
            background-color: #ececec;
            border-radius: 6px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        
        .user-panel img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }
        
        .user-name {
            font-size: 18px;
            font-weight: bold;
        }
        
        .user-options {
            margin-left: auto;
            display: flex;
            align-items: center;
        }
        
        .user-options a {
            margin-right: 20px;
            text-decoration: none;
            color: #3498db;
            font-weight: bold;
            transition: color 0.3s ease;
        }
        
        .user-options a:hover {
            color: #2980b9;
        }
        
        .content-panel {
            display: flex;
            margin-bottom: 20px;
        }
        
        .content-panel .sidebar {
            background-color: #f4f4f4;
            border-radius: 6px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 20%;
            padding: 20px;
            margin-right: 20px;
        }

        .content-panel .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .content-panel .sidebar li {
            margin-bottom: 10px;
        }

        .content-panel .sidebar a {
            display: block;
            padding: 10px;
            text-decoration: none;
            color: #333;
            border-radius: 6px;
            background-color: rgba(0, 0, 0, 0.05); /* Hafif arka plan rengi */
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .content-panel .sidebar a:hover {
            background-color: #3498db;
            color: #fff;
            background-color: rgba(0, 0, 0, 0.1); /* Hafif arka plan rengi değişikliği */
        }

        .content-panel .main-content {
            flex-grow: 1;
            background-color: #fff;
            border-radius: 6px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        
        .profile-info {
            font-size: 16px;
            color: #666;
            margin-bottom: 20px;
        }
        
        .info-block {
            margin-bottom: 15px;
        }
        
        .info-title {
            font-weight: bold;
        }
        .logo a {
            text-decoration: none;
            color: #fff; /* Beyaz renk */
            transition: color 0.3s ease;
        }
    </style>
</head>
<%
   String username = (String) session.getAttribute("username");
   String email = (String) session.getAttribute("email");
   DBKatmani dbkatmani = new DBKatmani();
   String[] bilgiler = dbkatmani.getOgretmenProfil(email);
%>
<body>
    <div class="top-panel">
        <div class="logo">
            <a href="ogretmenAnaSayfa.jsp" class="logo">
                <img src="resimler/logo.png" alt="Üniversite Logosu">
                <div class="university-name">Phoenix Üniversitesi</div>
            </a>
        </div>
    </div>
    <div class="container">
        <div class="user-panel">
            <div class="user-name"><%=username%></div>
            <div class="user-options">
                <a href="ogretmenAnaSayfa.jsp" >Ana Sayfa</a>
                <a href="#" style="color: #e74c3c;" onclick="cikis()">Çıkış</a>
            </div>
        </div>
        <div class="content-panel">
            <div class="main-content">
                <h1>Profil Bilgileri</h1>
                <div class="profile-info">
                    <div class="info-block">
                        <div class="info-title">Ünvan: <%=bilgiler[0]%></div>
                    </div>
                    <div class="info-block">
                        <div class="info-title">Ad Soyad: <%=bilgiler[1]%></div>
                    </div>
                    <div class="info-block">
                        <div class="info-title">Fakülte: <%=bilgiler[2]%></div>
                    </div>
                    <div class="info-block">
                        <div class="info-title">Bölüm: <%=bilgiler[3]%></div>
                    </div>
                    <div class="info-block">
                        <div class="info-title">E-posta: <%=bilgiler[4]%></div>
                    </div>
                    <div class="info-block">
                        <div class="info-title">Telefon: <%=bilgiler[5]%></div>
                    </div>
                    <div class="info-block">
                        <div class="info-title">Adres: <%=bilgiler[6]%></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
    function cikis(){
        window.location.href = "giris.jsp";
    }
</script>
</body>
</html>