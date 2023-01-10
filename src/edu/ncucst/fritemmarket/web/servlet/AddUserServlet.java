package edu.ncucst.fritemmarket.web.servlet;

import edu.ncucst.fritemmarket.web.beans.Fruit;
import edu.ncucst.fritemmarket.web.beans.User;
import edu.ncucst.fritemmarket.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2023-${MONTG}-08-16:57
 */
@WebServlet(name = "AddUserServlet",urlPatterns ="/AddUserServlet" )
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserService service=new UserService();
        String username=request.getParameter("username").trim();
        String password=request.getParameter("password");
        if (username!=null&&username.length()>0){
            User user=new User(username,password);
            if (service.addUser(user)){
                request.setAttribute("msg", "创建用户成功");
                request.getRequestDispatcher("zhucemsg.jsp").forward(request, response);
            }else{
                request.setAttribute("msg", "用户名不能重复，请重新输入");
                request.getRequestDispatcher("zhucefail.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("msg", "用户名不能为空，请重新输入");
            request.getRequestDispatcher("zhucefail.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
