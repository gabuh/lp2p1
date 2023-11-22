package servlets;

import com.google.gson.JsonObject;
import dao.impl.TecnicoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tecnico;
import util.InputConstraints;
import util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

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
        String dataNascimento = req.getParameter("dataNascimento");
        String nacionalidade = req.getParameter("nacionalidade");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String username = req.getParameter("username");

System.out.println(nome + " "+ dataNascimento + " " + nacionalidade + " " + email + " " + senha + " "+ username);
        System.out.println(validateInputs(nome, dataNascimento , nacionalidade,  email,  senha,  username));

        if (validateInputs(nome, dataNascimento , nacionalidade,  email,  senha,  username)){
            Tecnico tecnico = new Tecnico(
                    nome,
                    Util.stringToLocalDate(dataNascimento),
                    nacionalidade,
                    email,
                    senha,
                    username);

            tecnicoDao.persist(tecnico);

            req.getSession().setAttribute("register", true);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            req.setAttribute("registermsg","Usuário registrado com sucesso! ");
            requestDispatcher.forward(req, resp);

        } else {

            req.getSession().setAttribute("register", true);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            req.setAttribute("registermsg","Usuário não registrado!");
            requestDispatcher.forward(req, resp);

        }


//        System.out.println(
//                "nome: " + nome
//                +"Nascimento: " + dataNascimento
//                +"Nacionalidade: " + nacionalidade
//                +"Email:" + email
//                +"Senha: " + senha
//                + "username: " + username);
//
//        resp.sendRedirect("github.com/gabuh");
    }



    private boolean validateInputs(String nome, String dataNascimento ,String nacionalidade, String email, String senha, String username){
        if (!InputConstraints.validateUsername(username)){
            System.out.println(username);
            return false;
        }


        if (!InputConstraints.validateEmail(email)){
            System.out.println(email);
            return false;
        }

        if (!InputConstraints.validatePassword(senha)){
            System.out.println(senha);
            return false;
        }

        if (!InputConstraints.validateName(nome)){
            System.out.println(nome);
            return false;
        }

        if (!InputConstraints.validateDataNascimento(dataNascimento)){
            System.out.println(dataNascimento);
            return false;
        }

        if (!InputConstraints.validateNacionalidade(nacionalidade)){
            System.out.println(nacionalidade);
            return false;
        }


        return true;
    }





    @Override
    public void destroy() {
        tecnicoDao.close();
    }
}
