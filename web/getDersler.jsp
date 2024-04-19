
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="veri.DBKatmani" %>
<!DOCTYPE html>
<%
    DBKatmani dbkatmani = new DBKatmani();
    String email = (String) session.getAttribute("email");
    String selectedValue = request.getParameter("selectedValue");
    String selectedOptionIndexStr = request.getParameter("selectedOptionIndex");
    int selectedOptionIndex = 0;
    if (selectedOptionIndexStr != null && !selectedOptionIndexStr.isEmpty()) {
        selectedOptionIndex = Integer.parseInt(selectedOptionIndexStr);
    }
    if (selectedValue != null) {
        String[][] dersler = dbkatmani.getAlinanDersler(email, selectedValue);
        session.setAttribute("dersler", dersler); 
        session.setAttribute("selectedOptionIndex", selectedOptionIndex);
    }
    response.sendRedirect("donemDersler.jsp"); 
%>
