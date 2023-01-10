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
 * @create 2023-${MONTG}-07-10:55
 */
@WebServlet(name = "ListServlet",urlPatterns = "/ListServlet")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitService service =new FruitService();
        ArrayList<Fruit> fruits = service.queryAllFruit();
        request.setAttribute("fruits",fruits);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
