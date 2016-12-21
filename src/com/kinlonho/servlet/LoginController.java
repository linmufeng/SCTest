package com.kinlonho.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.kinlonho.bean.UserBean;
import com.kinlonho.framework.bean.ActionMapping;
import com.kinlonho.framework.bean.ActionMappingManager;
import com.kinlonho.framework.bean.Result;
import com.kinlonho.framework.interceptor.Action;
import com.kinlonho.framework.interceptor.ActionProxy;
import com.kinlonho.framework.interceptor.ActionProxyFactory;
import com.sun.org.apache.xml.internal.security.transforms.TransformationException;

/**
 * Servlet implementation class LoginController
 */
// @WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private ActionMappingManager actionMappingManager;

    // 只执行一次 (希望启动时候执行)
    @Override
    public void init() throws ServletException {
        System.out.println("LoginController.init()");
        actionMappingManager = new ActionMappingManager();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            
            // 1.获取请求uri,得到请求路径名称 如login
            String uri = request.getRequestURI();
            // 得到login
            String actionName = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".scaction"));

            System.out.println("ActionName：" + actionName);
            // 2.根据路径名称，读取配置文件，得到类的全名 【com.kinlonho.servlet.LoginController】
            ActionMapping actionMapping = actionMappingManager.getActionMapping(actionName);
            String className = actionMapping.getClassName();

            System.out.println("Inte:" + actionMapping.getInterceptors());

            // 当前请求的处理方法 【method="login"】
            // String method = actionMapping.getMethod();
            String method = "execute";

            // 3.反射：创建对象，然后调用方法，获取方法返回的标记
            Class<?> clazz = Class.forName(className);
            Object obj = clazz.newInstance();

            ActionProxy handler = ActionProxyFactory.createActionProxy(obj, actionMapping.getInterceptors(), actionName,
                    method, false);// 生成代理
            Action proxyObj = (Action) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                    obj.getClass().getInterfaces(), handler);
            // String returnFlag = (String)m.invoke(proxyObj,
            // HttpServletRequest.class,HttpServletResponse.class);
            String returnFlag = (String) proxyObj.execute(request, response);

            System.out.println("returnFlag:" + returnFlag);
            // 4.用该标记，读取配置文件得到标记对应的页面，跳转类型
            Result result = actionMapping.getResults().get(returnFlag);
            String type = result.getType(); // 类型
            String page = result.getValue(); // 页面
            System.out.println("type:" + type);
            System.out.println("page:" + page);
            // 跳转页面
            if ("redirect".equals(type)) { // 重定向类型
                response.sendRedirect(request.getContextPath() + "/" + page);
            } else { // 转发类型
                if (page.endsWith(".xml")) {// 如果是xml文件
                    // String xmlPath = page; //转发过来的xml就是需要转换的xml
//                    String xmlPath = page;
                    String xmlPath = "pages/success_view.xml";
                    String xslPath = "pages/success_view.xsl";
                    xml2html(xmlPath, xslPath, request, response);
                }
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    /**
     * @info 将xml通过xsl转换成html并将result直接传给用户
     * @param xmlPath
     * @param xslPath
     * @param req
     * @param resp
     */
    private void xml2html(String xmlPath, String xslPath, HttpServletRequest req, HttpServletResponse resp) {
        try {
            String rootPath = req.getSession().getServletContext().getRealPath("/");
            TransformerFactory factory = TransformerFactory.newInstance();
            Templates template = factory.newTemplates(new StreamSource(new FileInputStream(rootPath + xslPath)));
            Transformer xformer = template.newTransformer();
            Source source = new StreamSource(new FileInputStream(rootPath + xmlPath));
            StreamResult result = new StreamResult(resp.getOutputStream());
            xformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
