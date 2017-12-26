package pers.mao.taobaoshop.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestUtils {

    public static String getRequestBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        String input = null;
        StringBuffer requestBody = new StringBuffer();
        while((input = reader.readLine()) != null) {
            requestBody.append(input);
        }
        return requestBody.toString();
    }
}
