import builder.ArduinoBuilder;
import kernel.ArduinoApp;
import kernel.generator.Generator;
import kernel.generator.Visitor;

import static builder.ArduinoBuilder.*;

public class Main {

    public static void main (String[] args) {
        ArduinoApp arduinoApp =
                arduino("monPremierCode")
                    .setup(sensor("led",2))
                    .setup(actuator("button",3))
                    .stateTable()
                    .endStateTable()
                .build();

        Generator generator = new Generator();
        arduinoApp.accept(generator);
        System.out.println(generator.getGeneratedCode());
    }

}