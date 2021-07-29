package wechat.core.util;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XMLUtil {

    public static <T> String beanToXML(T obj, Class<T> tClass) {
        try {
            JAXBContext context = JAXBContext.newInstance(tClass);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> void writeXmlToResponse(T obj, Class<T> tClass, HttpServletResponse response) {
        try {
            response.setContentType("application/xml;charset=utf-8");
            response.getWriter().write(beanToXML(obj, tClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
