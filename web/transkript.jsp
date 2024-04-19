<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="veri.DBKatmani" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transkript</title>
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
            top: 100px;
            left: 50%;
            transform: translate(-50%);
            width: 80%;
            max-width: 2000px;
            overflow: auto;
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
        .transcript-table {
            border-collapse: collapse;
            width: 48%; /* Tabloların yüzde 48'sini kaplasın */
            margin-bottom: 20px; /* Tablolar arasında boşluk bırak */
        }

        .transcript-table th,
        .transcript-table td {
            border: 1px solid #ddd;
            padding: 1px;
        }

        .transcript-table th {
            background-color: #f2f2f2;
        }
        .table-container {
            display: flex;
            flex-wrap: wrap; /* Satırlarda taşmayı engelle */
            justify-content: space-between; /* Tabloları yan yana yerleştir */
        }
    </style>
</head>
<%
    String username = (String) session.getAttribute("username");
    String email = (String) session.getAttribute("email");
    DBKatmani dbkatmani = new DBKatmani();
    String [][] transkript = dbkatmani.getTranskript(email);
    int rowCount = transkript.length;
    int colCount = transkript[0].length;
    
%>
<body>
    <div class="top-panel">
        <div class="logo">
            <a href="ogrenciAnaSayfa.jsp" class="logo">
                <img src="resimler/logo.png" alt="Üniversite Logosu">
                <div class="university-name">Phoenix Üniversitesi</div>
            </a>
        </div>
        <div class="user-info"><%=username%></div>
    </div>
    <div class="container">
        <div class="content-panel">
            <div class="sidebar">
                <ul>
                    <li><a href="ogrenciAnaSayfa.jsp">Ana Sayfa</a></li>
                    <li><a href="alinanDersler.jsp">Alınan Dersler</a></li>
                    <li><a href="dersNotlari.jsp">Ders Notları</a></li>
                    <li><a href="transkript.jsp">Transkript</a></li>
                </ul>
            </div>
            <div class="main-content">
    <h1>Transkript</h1>
    <div class="table-container">
        <% 
        int currentDonemID = -1; 

        for (int i = 0; i < rowCount; i++) {
             String donemIDStr = transkript[i][0]; 
            int donemID = Integer.parseInt(donemIDStr); 
            
            if (donemID != currentDonemID) {
                if (currentDonemID != -1) {
                    %></table><%
                }
                
                %>
                <table class="transcript-table">
                    <tr>
                        <th>Ders Kodu</th>
                        <th>Ders Adı</th>
                        <th>Kredi</th>
                        <th>AKTS</th>
                        <th>Katsayı</th>
                        <th>Harf Not</th>
                    </tr>
                <% 
                currentDonemID = donemID;
            }
            
            %>
            <tr>
                <td><%=transkript[i][1]%></td>
                <td><%=transkript[i][2]%></td>
                <td><%=transkript[i][3]%></td>
                <td><%=transkript[i][4]%></td>
                <td><%=transkript[i][5]%></td>
                <td><%=transkript[i][6]%></td>
            </tr>
            <%
        }
        
        if (currentDonemID != -1) {
            %></table><%
        }
        %>
    </div>
</body>
</html>
