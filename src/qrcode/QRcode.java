
package qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import javafx.scene.control.TextField;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
/**
 *
 * @author Usman
 */
public class QRcode extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        Button btn = new Button();
        btn.setText("Submit");
        btn.setOnAction((ActionEvent event) -> {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            
            String data = textField.getText();
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null;
            try {
                BitMatrix byteMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
                bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                bufferedImage.createGraphics();
                
                Graphics2D image = (Graphics2D) bufferedImage.getGraphics();
                image.setColor(Color.WHITE);
                image.fillRect(0, 0, width, height);
                image.setColor(Color.BLACK);
                
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (byteMatrix.get(i, j)) {
                            image.fillRect(i, j, 1, 1);
                        }
                    }
                }
                
                System.out.println("QR created successfully....");
                
            } catch (WriterException ex) {
                 //Todo
            }
            
            ImageView qr = new ImageView();
            qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
            StackPane obj = new StackPane();
            obj.getChildren().add(qr);
            Scene scene = new Scene(obj, 300, 250);
            
            primaryStage.setTitle("QRCode");
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        
        
        
        FlowPane root = new FlowPane();
        root.getChildren().addAll(textField, btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("QRCode");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
