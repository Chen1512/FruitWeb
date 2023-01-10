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
 * @create 2023-${MONTG}-07-14:28
 */
@WebServlet(name = "FruitInfoServlet",urlPatterns = "/FruitInfoServlet")
public class FruitInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FruitService service=new FruitService();
        String number=request.getParameter("number").trim();
        Fruit fruit=new Fruit();
        fruit.setNumber(number);
        ArrayList<Fruit> fruits = service.queryFruitByCond(fruit);
        fruit = fruits.get(0);
        if (fruit!=null){
            request.setAttribute("fruit", fruit);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }else {
            request.setAttribute("msg", "找不到该水果编号");
            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
