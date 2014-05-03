package org.codetrials.examples.python;

import org.codetrials.bundle.Task;
import org.codetrials.bundle.engines.BundleEngine;
import org.codetrials.bundle.engines.PythonEngine;
import org.codetrials.bundle.entities.TaskDescription;
import org.codetrials.bundle.helpers.SimpleBundleContainer;
import org.codetrials.bundle.helpers.tasks.FreeTask;
import org.codetrials.bundle.helpers.tasks.MultipleRegexpTask;
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
            Task task2 = new MultipleRegexpTask(load(1), "Try \"help\" or \"credits\"!", "go on", "(help|copyright|credits|license)");
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
