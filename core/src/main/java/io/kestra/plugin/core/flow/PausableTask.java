package io.kestra.plugin.core.flow;

import io.kestra.core.models.flows.Input;
import io.kestra.core.models.flows.State;

import java.util.List;
import java.util.Map;

/**
 * A flowable task whose task run is resumed from a PAUSED state, and that can collect inputs at resume time.
 * {@link Pause} and {@link io.kestra.plugin.ee.flow.HumanTask} implement it today; a task run reaches PAUSED
 * on its own through the flowable's {@code resolveState}, and every executor and service call site that used
 * to check {@code instanceof Pause} for resume plumbing now checks {@code instanceof PausableTask} instead.
 */
public interface PausableTask {
    /**
     * Inputs collected from the user before the task run resumes, in the same shape as flow inputs.
     */
    List<Input<?>> resumeInputs();

    /**
     * Builds the outputs to store on the task run once it is resumed.
     *
     * @param inputs the values collected against {@link #resumeInputs()}
     * @param resumed who resumed the execution, when, and to which state
     */
    Map<String, Object> resumeOutputs(Map<String, Object> inputs, Pause.Resumed resumed);

    /**
     * The state this task run itself should take once the execution resumes to {@code newState}.
     */
    State.Type resumedTaskRunState(State.Type newState);
}
