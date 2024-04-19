
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="veri.DBKatmani" %>
<!DOCTYPE html>
<%  
    String dersNotlar[][] = (String[][]) session.getAttribute("sinavNotlari");
    String dersAdi = (String) session.getAttribute("dersAdi");
    String action = request.getParameter("action");
    DBKatmani dbkatmani = new DBKatmani();
    
    for (int i = 0; i < dersNotlar.length; i++) {
        String vizeValue = request.getParameter("vize" + i);
        String finalValue = request.getParameter("final" + i);
        String butValue = request.getParameter("but" + i);

        if (vizeValue != null && !vizeValue.isEmpty()) {
            dersNotlar[i][2] = vizeValue;
        }
        if (finalValue != null && !finalValue.isEmpty()) {
            dersNotlar[i][3] = finalValue;
        }
        if (butValue != null && !butValue.isEmpty()) {
            dersNotlar[i][4] = butValue;
        }
    } 
    
    session.setAttribute("sinavNotlari", dersNotlar);
    
    if ("kaydet".equals(action)) {
        dbkatmani.sinavKaydet(dersNotlar,dersAdi);
        response.sendRedirect("uyariKaydet.jsp");
    } else if ("sonuclandir".equals(action)) {
        dbkatmani.sinavKaydet(dersNotlar,dersAdi);
        dbkatmani.sonuclandir(dersNotlar,dersAdi);
        response.sendRedirect("uyariSonuclandir.jsp");
    }
    
%>