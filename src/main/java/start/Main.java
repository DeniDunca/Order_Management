package start;

import presentation.ControllerM;
import presentation.ViewM;

public class Main {
    public static void main(String[] args){
        ViewM view = new ViewM();

        ControllerM controller = new ControllerM(view);

        view.setVisible(true);

    }
}