package kernel.logic;

import java.util.List;
import kernel.generator.Visitable;
import kernel.generator.Visitor;
import lombok.Getter;

@Getter
public class State implements Visitable {

  String name;
  List<Transition> transitions;

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
