package servlets;

import dao.impl.TecnicoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        req.getSession().setAttribute("username" , "Gabriel");
        resp.sendRedirect("/");
    }

    @Override
    public void destroy() {
        super.destroy();
        tecnicoDao.close();
    }
}
