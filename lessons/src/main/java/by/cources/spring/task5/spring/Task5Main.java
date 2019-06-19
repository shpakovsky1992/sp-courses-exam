package by.cources.spring.task5.spring;

//import io.undertow.Handlers;
//import io.undertow.Undertow;
//import io.undertow.servlet.Servlets;
//import io.undertow.servlet.api.DeploymentInfo;
//import io.undertow.servlet.api.DeploymentManager;
//import io.undertow.servlet.api.InstanceFactory;
//import io.undertow.servlet.api.ListenerInfo;
//import io.undertow.servlet.util.ImmediateInstanceFactory;
//import javax.servlet.ServletException;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Task5Main {

  private static final String MAPPING_URL = "/*";
  private static final String CONTEXT_PATH = "/myapp";
  private static final String PKG_NAME = "myapp.war";

//  public static void main(String[] args) throws ServletException {
//    Task5Main task5Main = new Task5Main();
//    DeploymentManager deploymentManager = task5Main.deploymentManager();
//    deploymentManager.deploy();
//    Undertow.builder().addHttpListener(8080, "localhost")
//        .setHandler(Handlers
//            .path(Handlers.redirect(CONTEXT_PATH))
//            .addPrefixPath(CONTEXT_PATH, deploymentManager.start()))
//        .build()
//        .start();
//  }
//
//  private DeploymentManager deploymentManager() {
//    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//    context.scan("by.cources.spring.task5");
//
//    DeploymentInfo servletBuilder = Servlets.deployment()
//        .setClassLoader(Task5Main.class.getClassLoader())
//        .setContextPath(CONTEXT_PATH).setDeploymentName(PKG_NAME)
//        .addServlets(Servlets
//            .servlet("DispatcherServlet", DispatcherServlet.class, getServlet(context))
//            .addMapping(MAPPING_URL))
//        .addListener(getListener(context));
//
//    return Servlets.defaultContainer().addDeployment(servletBuilder);
//  }
//
//  private InstanceFactory<DispatcherServlet> getServlet(AnnotationConfigWebApplicationContext context) {
//    return new ImmediateInstanceFactory<>(new DispatcherServlet(context));
//  }
//
//  private ListenerInfo getListener(AnnotationConfigWebApplicationContext context) {
//    ContextLoaderListener listener = new ContextLoaderListener(context);
//    return new ListenerInfo(ContextLoaderListener.class, new ImmediateInstanceFactory<>(listener));
//  }
}

