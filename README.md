## Introduction
This project is a simple task manager using arrays, stacks, and linked lists to track tasks and their completion status.

## Environment Setup
JDK 23.0.2
VS Code (lightweight for my system)
Git for version control

## Challenges
Index Out of Bounds & Input Errors
Had issues with invalid indexes and input mismatches. Fixed it by checking index ranges and reusing a method to validate integer inputs.

Duplicate Task Completion
Marking one task as done affected all tasks with the same name. Solved by tracking tasks using unique indexes instead.

Linked List Index Shift
Removing tasks caused index shifts, breaking the completion tracking. Fixed by adjusting how tasks are referenced.
