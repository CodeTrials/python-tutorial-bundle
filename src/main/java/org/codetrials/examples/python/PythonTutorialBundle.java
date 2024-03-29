package org.codetrials.examples.python;

import org.codetrials.bundle.Task;
import org.codetrials.bundle.engines.PythonEngine;
import org.codetrials.bundle.entities.ExecutionResult;
import org.codetrials.bundle.entities.TaskDescription;
import org.codetrials.bundle.entities.TaskReaction;
import org.codetrials.bundle.helpers.SimpleBundleContainer;
import org.codetrials.bundle.helpers.tasks.FreeTask;
import org.codetrials.bundle.helpers.tasks.SandboxTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Polyarnyi Nikolay
 */
public class PythonTutorialBundle extends SimpleBundleContainer {

    public PythonTutorialBundle() {
        super(new PythonEngine());
    }

    private TaskDescription load(int id) throws IOException {
        return resourceLoader.loadTaskDescription(id);
    }

    @Override
    protected List<Task> createTasks() {
        try {
            Task task1 = new FreeTask(load(0), "next");
            Task task2 = new Task(load(1)) {

                boolean completed = false;

                @Override
                public boolean isCommandExecutable(String command) {
                    return !command.equals("about");
                }

                @Override
                public boolean isCompleted() {
                    return completed;
                }

                @Override
                public TaskReaction onCommandExecuted(String s, ExecutionResult executionResult) {
                    if (s.equals("about")) {
                        completed = true;
                        return new TaskReaction("Made at JetBrains EdTech Hackathon, May 1st-3rd, 2014 in St. Petersburg, Russia");
                    } else {
                        return new TaskReaction("Enter \"about\"!");
                    }
                }
            };
            Task task3 = new FreeTask(load(2), "next");
            Task task4 = new SandboxTask(load(3));
            ArrayList<Task> list = new ArrayList<>();
            list.add(task1);
            list.add(task2);
            list.add(task3);
            list.add(task4);
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public String getBundleName() {
        return "Python tutorial";
    }

    @Override
    public String getBundleDescription() {
        return "Python beginner tutorial.\n" +
                "From simple variables and math to some interesting things!";
    }
}
