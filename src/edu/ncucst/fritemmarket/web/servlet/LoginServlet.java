package edu.ncucst.fritemmarket.web.servlet;

import edu.ncucst.fritemmarket.web.service.UserService;
import org.springframework.messaging.rsocket.RSocketRequester;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2023-${MONTG}-06-14:55
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name=request.getParameter("name".trim());
        String password=request.getParameter("password");
        String code= request.getParameter("code").trim();
        //session.setAttribute("check_code",new String(rands));
        String saveCode = (String) request.getSession().getAttribute("check_code");

        UserService service=new UserService();
        if (saveCode.equals(code)) {
            if (service.login(name, password).equals("1")) {
                response.sendRedirect("succpage.jsp");
            } else if (service.login(name, password).equals("2")) {
                request.setAttribute("msg", "用户名或密码错误，请验证后重新登陆");
                request.getRequestDispatcher("errpage.jsp").forward(request, response);
            } else if (service.login(name, password).equals("3")) {
                request.setAttribute("msg", "数据库连接错误，请联系管理员");
                request.getRequestDispatcher("errpage.jsp").forward(request, response);
            } else if (service.login(name, password).equals("4")) {
                request.setAttribute("msg", "数据库访问错误，请联系管理员");
                request.getRequestDispatcher("errpage.jsp").forward(request, response);
            } else if (service.login(name, password).equals("5")) {
                request.setAttribute("msg", "用户名不为空，请重新输入");
                request.getRequestDispatcher("errpage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "验证码错误，请检查后重新输入");
            request.getRequestDispatcher("errpage.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
