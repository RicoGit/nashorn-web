package com.solovev.nashorn.server;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: Constantine Solovev
 * Created: 18.01.15  12:14
 */



@WebServlet("/")
public class JsServlet extends HttpServlet {

    ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        nashorn.put("request", req);
        nashorn.put("response", resp);

        PrintWriter writer = resp.getWriter();

        try {
            String path = req.getServletPath().substring(1);
            Object result = nashorn.eval(String.format("load('%s')", getPath(path)));
            writer.print(result);

        } catch (ScriptException e) {
            writer.print(e.getMessage());
        }

        writer.close();
        super.doGet(req, resp);
    }


    public String getPath(String scriptName) {
        return String.format(
                "%s/webapps/nashorn/WEB-INF/classes/scripts/%s.js",
                System.getProperty("catalina.base"),
                scriptName
        );
    }

}
