package ru.dega;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * HtmlBodyParse class.
 *
 * @author Denis
 * @since 09.08.2017
 */
public class HtmlBodyParse {
    /**
     * Parse http servlet request.
     *
     * @param req HttpServletRequest
     * @return map contains 'name', 'login' and 'email'
     */
    public static Map<String, String> getLoginDataFromRequestBody(HttpServletRequest req) {
        Map<String, String> result = new HashMap<>();
        try {
            BufferedReader reader = req.getReader();
            String line = reader.readLine();
            if (line != null) {
                if (req.getServletPath().equals("/create")) {
                    result.put("name", line.substring(5, line.indexOf("&login=")));
                    result.put("login", line.substring(line.indexOf("&login=") + 7,
                            line.indexOf("&email=")));
                    result.put("email", line.substring(line.indexOf("&email=") + 7));
                } else if (req.getServletPath().equals("/edit")) {
                    result.put("login", line.substring(6, line.indexOf("&name=")));
                    result.put("name", line.substring(line.indexOf("&name=") + 6,
                            line.indexOf("&email=")));
                    result.put("email", line.substring(line.indexOf("&email=") + 7));
                } else if (req.getServletPath().equals("/delete")) {
                    result.put("login", line.substring(6));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}