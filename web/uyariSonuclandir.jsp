<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            margin: 90px auto;
            max-width: 400px;
        }
        
        h1 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #333;
        }
        
        .options {
            margin-top: 30px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        
        .option {
            margin: 10px 0;
            padding: 15px 40px;
            background-color: #e0e0e0;
            border-radius: 8px;
            font-size: 18px;
            font-weight: bold;
            color: #333;
            text-decoration: none;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        
        .option:hover {
            background-color: #3498db;
            color: #fff;
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
        <h1>Sınavlar Sonuçlandırlırdı!</h1>
    </div>
<script>
    setTimeout(function() {
            window.location.href = "ogretmenAnaSayfa.jsp";
        }, 2000);
</script>
</body>
</html>
