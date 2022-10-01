package ir.moke.javaee.servlet;

import ir.moke.javaee.annotation.Command;
import ir.moke.javaee.annotation.CustomAPI;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

@WebServlet("/")
public class ApiController extends HttpServlet {

    @Inject
    private BeanManager beanManager;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqApi = req.getRequestURI().substring(1);

        Set<Bean<?>> beans = beanManager.getBeans(Object.class);
        for (Bean<?> bean : beans) {
            Class<?> beanClass = bean.getBeanClass();
            if (beanClass.isAnnotationPresent(CustomAPI.class)) {
                Method[] methods = beanClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Command.class)) {
                        Command command = method.getAnnotation(Command.class);
                        if (command.value().equals(reqApi)) {
                            Bean<?> next = beanManager.getBeans(beanClass).iterator().next();
                            CreationalContext<?> creationContext = beanManager.createCreationalContext(next);
                            Object reference = beanManager.getReference(bean, beanClass, creationContext);

                            try {
                                method.invoke(reference);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        PrintWriter writer = resp.getWriter();
        writer.write("Okey\n");
        writer.flush();
        writer.close();
    }
}
