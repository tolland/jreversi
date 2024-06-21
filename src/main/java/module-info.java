open module org.limepepper.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires org.json;
    requires org.slf4j;

    //opens org.limepepper.demo to javafx.fxml;
    exports org.limepepper.demo;
    exports org.limepepper.demo.stuff;
    exports org.limepepper.demo.model;
    //opens org.limepepper.demo.stuff to javafx.fxml;
    //opens org.limepepper.demo.model to javafx.fxml;
}
