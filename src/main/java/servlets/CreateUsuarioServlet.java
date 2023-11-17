package servlets;

import com.google.gson.JsonObject;
import dao.impl.TecnicoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Util;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/register")
public class CreateUsuarioServlet extends HttpServlet {

    private TecnicoDao tecnicoDao;

    @Override
    public void init(){
        tecnicoDao = new TecnicoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean usernameExists = tecnicoDao.checkIfUsernameExist(username);
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("usernameExists", usernameExists);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter out = resp.getWriter()){
            out.write(jsonResponse.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        LocalDate dataNascimento = Util.stringToLocalDate(req.getParameter("dataNascimento"));
        String nacionalidade = req.getParameter("nacionalidade");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String username = req.getParameter("username");

        System.out.println(
                "nome: " + nome
                +"Nascimento: " + dataNascimento
                +"Nacionalidade: " + nacionalidade
                +"Email:" + email
                +"Senha: " + senha
                + "username: " + username);

        resp.sendRedirect("github.com/gabuh");
    }

    @Override
    public void destroy() {
        tecnicoDao.close();
    }
}