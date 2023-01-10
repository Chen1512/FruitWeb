package edu.ncucst.fritemmarket.web.servlet;

import edu.ncucst.fritemmarket.web.beans.User;
import edu.ncucst.fritemmarket.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author shkstart
 * @create 2023-${MONTG}-07-22:58
 */
@WebServlet(name = "UpdateUserServlet",urlPatterns = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user=new User();
        String username=request.getParameter("username").trim();
        String password=request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);
        UserService service=new UserService();
        if (service.login(username, password).equals("1")) {
            String newpass=request.getParameter("newpass");
            String renewpass=request.getParameter("renewpass");
            if (newpass.equals(renewpass)){
                user.setPassword(newpass);
                service.updateUser(user);
                request.setAttribute("msg", "修改成功");
                request.getRequestDispatcher("msg.jsp").forward(request, response);
            }else {
                request.setAttribute("msg", "两次密码不一致，请重新输入");
                request.getRequestDispatcher("userfail.jsp").forward(request, response);
            }
        } else if (service.login(username, password).equals("2")) {
            request.setAttribute("msg", "用户名或密码错误，请验证后重新登陆");
            request.getRequestDispatcher("userfail.jsp").forward(request, response);
        } else if (service.login(username, password).equals("3")) {
            request.setAttribute("msg", "数据库连接错误，请联系管理员");
            request.getRequestDispatcher("userfail.jsp").forward(request, response);
        } else if (service.login(username, password).equals("4")) {
            request.setAttribute("msg", "数据库访问错误，请联系管理员");
            request.getRequestDispatcher("userfail.jsp").forward(request, response);
        } else if (service.login(username, password).equals("5")) {
            request.setAttribute("msg", "用户名不为空，请重新输入");
            request.getRequestDispatcher("userfail.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
