package servlets;

import dao.impl.TecnicoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.InputConstraints;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    TecnicoDao tecnicoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        tecnicoDao = new TecnicoDao();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("usernameLogin");
        String password = req.getParameter("passwordLogin");
        HttpSession session = req.getSession();
        if (validateInputs(username, password)){
            var tecnico = tecnicoDao.getUserByUsernameAndPassword(username, password);
            session.setAttribute("username",tecnico.getUsername());
            session.setAttribute("id", tecnico.getId());
            session.setAttribute("nome", tecnico.getNome());
            session.setAttribute("email", tecnico.getEmail());
            resp.sendRedirect("/");
        }else {
            session.setAttribute("modalMsgContent","Revise os Dados!");
            session.setAttribute("modalMsg", true);
            resp.sendRedirect("/");
        }

    }


    private boolean validateInputs(String username, String password){
        if (!InputConstraints.validateUsername(username))
            return false;

        if (!InputConstraints.validatePassword(password))
            return false;

        //validate user checking the database.
        if (!tecnicoDao.checkUsuario(username, password))
            return false;


        return true;
    }



    @Override
    public void destroy() {
        super.destroy();
        tecnicoDao.close();
    }
}
