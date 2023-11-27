package servlets;


import dao.impl.CampeonatoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.InputConstraints;

import java.io.IOException;

@WebServlet("/campeonato")
public class CampeonatoServlet extends HttpServlet {

    private CampeonatoDao campeonatoDao;

    @Override
    public void init()  {
        campeonatoDao = new CampeonatoDao();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
                if (
                        (id = req.getParameter("id")) == null
                ){


                }

        if (validID(id)){
                RequestDispatcher requestDispatcher =req.getRequestDispatcher("/detalharCampeonato.jsp");
                req.setAttribute("campeonato", campeonatoDao.findById(Integer.parseInt(id)));
                requestDispatcher.forward(req, resp);
        } else {
            req.getSession().setAttribute("modalMsg", true);
            req.getSession().setAttribute("modalMsgContent", "Erro, campeonato não existente");
            resp.sendRedirect("/");
        }
    }

    private boolean validID(String id){
        if (id == null)
            return false;

        if (id.isEmpty() || id.equals(" ") || id.equals(""))
            return false;

        if (!InputConstraints.validNumber(id))
            return false;

        int idInt = Integer.parseInt(id);
        if (!campeonatoDao.checkIfIdExist(idInt))
            return false;


        return true;
    }


    @Override
    public void destroy() {
        campeonatoDao.close();
    }
}
