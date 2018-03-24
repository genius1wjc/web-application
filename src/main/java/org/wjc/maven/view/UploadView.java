package org.wjc.maven.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UploadView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public UploadView() {
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // Show uploaded file in this placeholder
        final Image image = new Image("Uploaded Image");

        // Implement both receiver that saves upload in a file and
        // listener for successful upload
        class ImageUploader implements Receiver, SucceededListener {
            private static final long serialVersionUID = 1L;
            public File file;
            OutputStream outputStream = null;

            public OutputStream receiveUpload(String filename, String mimeType) {
                try {
                    file = new File("test");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    outputStream = new FileOutputStream(file);
                    return outputStream;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void uploadSucceeded(SucceededEvent event) {
                // Show the uploaded file in the image viewer
                image.setSource(new FileResource(file));
            }

            @Override
            protected void finalize() {
                try {
                    super.finalize();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Throwable exception) {
                    exception.printStackTrace();
                }
            }
        }

        ImageUploader receiver = new ImageUploader();
        // Create the upload with a caption and set receiver later
        Upload upload = new Upload("Upload Image Here", receiver);
        upload.setReceiver(receiver);

        addComponent(upload);
    }
}
