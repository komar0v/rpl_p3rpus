/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_.Admin;

import DAO_.x_Peminjaman;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "doAdmin_konfirmasiPengembalianBuku", urlPatterns = {"/admin/doAdmin_konfirmasiPengembalianBuku"})
public class doAdmin_konfirmasiPengembalianBuku extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(true);
        String user_Admin = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("usernameAdmin")) {
                user_Admin = cookie.getValue();
            }
        }
        if (user_Admin != null) {
            String idPinjam_string = request.getParameter("idPinjam_");
            int idPinjam_int = Integer.parseInt(idPinjam_string);
            
            x_Peminjaman cekDayRemain = new x_Peminjaman();
            int dayRemain = Integer.parseInt(cekDayRemain.getDayRemaining(idPinjam_int));
            int dendaPerhari = 2000;
            
            if(dayRemain<=0){
                int besarDenda = abs(dayRemain*dendaPerhari);
                System.out.println("DENDA = Rp. "+besarDenda);
                
                
            }else if(dayRemain>=1){
                System.out.println("TIDAK DENDA");
            }

            

            session.setAttribute("flashMessageAdmin", "Toast.fire({icon: \"success\",title: \" cek DONE \"});");
            response.sendRedirect(request.getContextPath() + "/admin/pengembalianBuku");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
