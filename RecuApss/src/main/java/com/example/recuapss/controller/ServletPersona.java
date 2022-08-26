package com.example.recuapss.controller;
import com.example.recuapss.ServicePersona;
import com.example.recuapss.model.BeanPersona;
import com.example.recuapss.model.DaoPersona;
import com.example.recuapss.utils.ResultAction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@WebServlet(name = "ServletPersona",
        urlPatterns = {
                "/get-usuarios",
                "/add-user",
                "/eliminar",
                "/create-user",})
public class ServletPersona extends HttpServlet {
    Logger logger = Logger.getLogger("ServletPersona");
    String action;
    DaoPersona daoPersona = new DaoPersona();
    ServicePersona servicePersona = new ServicePersona();
    String urlRedirect = "/get-usuarios";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        action = request.getServletPath();
        logger.log(Level.INFO, "Path-> "+action);
        switch (action){
            case "/get-usuarios":
                List<BeanPersona> personas = daoPersona.findAll();
                request.setAttribute("personas", personas);
                urlRedirect = "/views/index.jsp";
                break;
            case "/create-user":
                urlRedirect = "/views/create.jsp";
                break;
            default:
                request.setAttribute("personas", daoPersona.findAll());
                urlRedirect = "/get-personas";
                break;
        }
        request.getRequestDispatcher(urlRedirect).forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        action = request.getServletPath();
        switch (action){
            case "/add-user":
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                String birthday = request.getParameter("birthday");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                BeanPersona persona = new BeanPersona();
                persona.setNombre(nombre);
                persona.setApellidos(apellidos);
                persona.setBirthday(birthday);
                persona.setEmail(email);
                persona.setPhone(phone);
                persona.setUsername(username);
                persona.setPassword(password);
                daoPersona.add(persona);
                urlRedirect = "/get-usuarios";
                break;
            case "/eliminar":
                String idUser = request.getParameter("id");
                ResultAction deleteResult = servicePersona.delete(idUser);
                urlRedirect = "/get-usuarios?result="+
                        deleteResult.isResult()+"&message="+
                        URLEncoder.encode(deleteResult.getMessage(), StandardCharsets.UTF_8.name())
                        +"&status="+deleteResult.getStatus();
                break;
            default:
                urlRedirect = "/get-usuarios";
                break;
        }
        response.sendRedirect(request.getContextPath()+urlRedirect);
    }
}
