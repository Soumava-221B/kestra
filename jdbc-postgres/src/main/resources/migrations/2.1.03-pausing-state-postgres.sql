-- Add the PAUSING execution state (kestra-io/kestra-ee#9838): an execution runs its Approval
-- task's onWait tasks in this state, before moving to PAUSED. state_type is a named enum used
-- only by executions.state_current, so widening it here needs no column-level ALTER.
ALTER TYPE state_type ADD VALUE IF NOT EXISTS 'PAUSING';
