package org.wjc.maven.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.wjc.maven.logging.LogUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;

public class PostgreView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    private static final String DB_NAME = "shorttermrentals";

    public PostgreView() {
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Label status = new Label("Opened database successfully");
        status.setVisible(false);

        Button button = new Button("Test Postgre");
        button.addClickListener(l -> {
            try {
                Class.forName("org.postgresql.Driver");
                Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DB_NAME,
                        "postgres", "admin");
            } catch (Exception e) {
                LogUtil.getLogger(PostgreView.class.getName()).log(Level.SEVERE, "Failed to open db", e);
            }
            status.setVisible(true);
        });

        addComponent(new VerticalLayout(button, status));
    }
}
