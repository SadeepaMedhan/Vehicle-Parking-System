package controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ObserverList;

public class ParkingSlotsFormController extends ObserverList {

    public AnchorPane lbl1;
    public AnchorPane lbl2;
    public AnchorPane lbl3;
    public AnchorPane lbl4;
    public AnchorPane lbl5;
    public AnchorPane lbl6;
    public AnchorPane lbl7;
    public AnchorPane lbl8;
    public AnchorPane lbl9;
    public AnchorPane lbl10;
    public AnchorPane lbl11;
    public AnchorPane lbl12;
    public AnchorPane lbl13;
    public AnchorPane lbl14;
    public AnchorPane slotsContext;

    public void initialize(){
        if(slots[0]!=null){setColor(lbl1);}
        if(slots[1]!=null){setColor(lbl2);}
        if(slots[2]!=null){setColor(lbl3);}
        if(slots[3]!=null){setColor(lbl4);}
        if(slots[4]!=null){setColor(lbl5);}
        if(slots[5]!=null){setColor(lbl6);}
        if(slots[6]!=null){setColor(lbl7);}
        if(slots[7]!=null){setColor(lbl8);}
        if(slots[8]!=null){setColor(lbl9);}
        if(slots[9]!=null){setColor(lbl10);}
        if(slots[10]!=null){setColor(lbl11);}
        if(slots[11]!=null){setColor(lbl12);}
        if(slots[12]!=null){setColor(lbl13);}
        if(slots[13]!=null){setColor(lbl14);}
    }

    private void setColor(AnchorPane slot) {
        slot.setStyle("-fx-background-color: #e67e22;");
    }

    public void closeParkingSlots(MouseEvent mouseEvent) {
        Stage stage = (Stage) slotsContext.getScene().getWindow();
        stage.close();
    }
}
