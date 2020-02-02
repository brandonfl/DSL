package kernel.logic.statements.transition;

import kernel.logic.statements.Statement;
import kernel.logic.statements.transition.condition.Condition;

public class Transition extends Statement {

  private final Condition condition;
  private String nextState;
  private final Exception exception;

  public Transition(Condition condition, String nextState, Exception exception) {
    this.condition = condition;
    this.nextState = nextState;
    this.exception = exception;
  }

  public String generateCode(Integer delay) {
    String delayInstr = "";

    if(delay != null && delay > 0){
      delayInstr = String.format("delay(%d);\n\t\t", delay);
    }

    if (this.exception != null) {
      nextState = this.exception.generateSetupCode();
    } else {
      nextState = exception.toString();
    }

    if(nextState.getClass().equals(Exception.class)) {
      nextState += "();";
    }

    if(condition == null) {
      return String.format("{%s}\n\t{%s}", delayInstr, nextState);
    }

    return String.format(
        "guard =  millis() - time > debounce;\n"
            + "    if ({%s}  && guard) {{\n"
            + "        time = millis();\n"
            + "        {%s}{%s}\n"
            + "    }}",
        this.condition.toString(),
        delayInstr,
        nextState
    );
  }

  @Override
  public String generateSetupCode() {
    return "";
  }
}
