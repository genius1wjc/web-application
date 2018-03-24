package org.wjc.maven;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.wjc.maven.constant.CommonConstants;
import org.wjc.maven.logging.LogUtil;
import org.wjc.maven.view.LocationView;
import org.wjc.maven.view.LoginView;
import org.wjc.maven.view.PostgreView;
import org.wjc.maven.view.UploadView;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Level;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            getPage().setTitle("Book Share Ninja");
            addViews();
        } catch (Exception e) {
            LogUtil.getLogger(MyUI.class.getName()).log(Level.SEVERE, "Encountered exception in init:", e);
        }
    }

    private void addViews() {
        Navigator navigator = new Navigator(this, this);
        navigator.addView(CommonConstants.LOGIN_VIEW, new LoginView());
        navigator.addView(CommonConstants.LOCATION_VIEW, new LocationView());
        navigator.addView(CommonConstants.UPLOAD_VIEW, new UploadView());
        navigator.addView(CommonConstants.POSTGRE_VIEW, new PostgreView());
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
        private static final long serialVersionUID = 1L;
    }
}
