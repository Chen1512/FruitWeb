package edu.ncucst.fritemmarket.web.servlet;

import edu.ncucst.fritemmarket.web.beans.Fruit;
import edu.ncucst.fritemmarket.web.service.FruitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author shkstart
 * @create 2023-${MONTG}-08-9:51
 */
@WebServlet(name = "SortServlet",urlPatterns = "/SortServlet")
public class SortServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FruitService service=new FruitService();
        String sort=request.getParameter("sort");
        String sx=request.getParameter("px");
        ArrayList<Fruit> fruits = service.sortFruits(sort);
        if ("DESC".equals(sx)){
            // 降序
            fruits = service.sortFruitsByDesc(sort);
        }else {
            // 升序
            fruits = service.sortFruits(sort);
        }
        request.setAttribute("fruits",fruits);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
