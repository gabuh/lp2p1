package servlets;

import com.google.gson.JsonObject;
import dao.impl.TecnicoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Tecnico;
import util.InputConstraints;
import util.Util;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/register")
public class CreateUsuarioServlet extends HttpServlet {

    private TecnicoDao tecnicoDao;

    @Override
    public void init(){
        tecnicoDao = new TecnicoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        HttpSession session = req.getSession();

//System.out.println(nome + " "+ dataNascimento + " " + nacionalidade + " " + email + " " + senha + " "+ username);
//        System.out.println(validateInputs(nome, dataNascimento , nacionalidade,  email,  senha,  username));

        if (validateInputs(nome, dataNascimento , nacionalidade,  email,  senha,  username)){
            Tecnico tecnico = new Tecnico(
                    nome,
                    Util.stringToLocalDate(dataNascimento),
                    nacionalidade,
                    email,
                    senha,
                    username);

            tecnicoDao.persist(tecnico);

            session.setAttribute("modalMsg", true);
            session.setAttribute("modalMsgContent","Usuário registrado com sucesso! ");
            resp.sendRedirect("/");
        } else {

            session.setAttribute("register", true);
            session.setAttribute("modalMsgContent","Usuário não registrado!");
            resp.sendRedirect("/");

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

        if (tecnicoDao.checkIfUsernameExist(username)){
            return false;
        }


        return true;
    }





    @Override
    public void destroy() {
        tecnicoDao.close();
    }
}
