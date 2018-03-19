package org.wjc.maven.view;

import org.jetbrains.annotations.NotNull;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import org.wjc.maven.constant.CommonConstants;

public class LoginView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    private TextField usernameText = new TextField();
    private PasswordField passwordText = new PasswordField();
    private Button submitButton = new Button("Submit");
    private Label loginStatus = new Label();

    @NotNull
    private String mUsername = "";
    @NotNull
    private String mPassword = "";

    public LoginView() {
        setSizeFull();

        usernameText.setPlaceholder("enter username");
        usernameText.setValueChangeMode(ValueChangeMode.LAZY);
        usernameText.addValueChangeListener(e -> {
            mUsername = e.getValue();
            checkAndEnableSubmitButton();
        });

        passwordText.setPlaceholder("enter password");
        passwordText.setValueChangeMode(ValueChangeMode.EAGER);
        passwordText.addValueChangeListener(e -> {
            mPassword = e.getValue();
            checkAndEnableSubmitButton();
        });

        submitButton.setEnabled(false);
        submitButton.setClickShortcut(KeyCode.ENTER);
        submitButton.addClickListener(e -> {
//			boolean success = LoginUtil.validateUsernameAndPassword(mUsername, mPassword);
            // Disable checking with db until we figure out the issue
            boolean success = true;
            if (!success) {
                loginStatus.setValue("Login failed, try again");
            } else {
                getUI().getNavigator().navigateTo(CommonConstants.LOCATION_VIEW);
            }
            loginStatus.setVisible(true);
        });

        loginStatus.setVisible(false);

        final VerticalLayout layout = new VerticalLayout(usernameText, passwordText, submitButton, loginStatus);
        addComponent(layout);
        setComponentAlignment(layout, Alignment.TOP_LEFT);
    }

    private void checkAndEnableSubmitButton() {
        if (!mUsername.isEmpty() && !mPassword.isEmpty()) {
            submitButton.setEnabled(true);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // no-op
    }
}
