package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizestr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        // 2.处理参数
        int cid = 0;
        if(cidStr != null && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }
        // 当前页码
        int currentPage = 0;
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }
        int pageSize = 0;
        if(pageSizestr != null && pageSizestr.length() > 0){
            pageSize = Integer.parseInt(pageSizestr);
        }else{
            pageSize = 5;
        }

        // 3.调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize);
        // 4.将pageBean对象序列化为json，返回
        writeValue(pb, response);
    }


}
