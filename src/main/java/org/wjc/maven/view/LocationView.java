package org.wjc.maven.view;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.wjc.maven.constant.CommonConstants;
import org.wjc.maven.model.Location;
import org.wjc.maven.service.LocationService;

import java.util.Collections;

public class LocationView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public LocationView() {
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Location location = LocationService.getCityResponse();

        Grid<Location> grid = new Grid<>();
        grid.setItems(Collections.singletonList(location));
        grid.addColumn(Location::getCountry).setCaption("Country");
        grid.addColumn(Location::getState).setCaption("State");
        grid.addColumn(Location::getCity).setCaption("City");
        grid.addColumn(Location::getPostal).setCaption("Postal");

        Button nextButton = new Button("Next");
        nextButton.setClickShortcut(KeyCode.ENTER);
        nextButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(CommonConstants.UPLOAD_VIEW);
        });

        addComponent(new VerticalLayout(grid, nextButton));
    }
}
