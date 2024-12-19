package com.invpoint.views.utils;

import javax.swing.*;

public class DataWorker extends SwingWorker<Void, Void> {

    private final Callback task;
    private final Callback success;

    public DataWorker(Callback task, Callback success){
        this.task = task;
        this.success = success;
        this.execute();
    }

    public static void run(Callback task, Callback success){
        DataWorker worker = new DataWorker(task, success);
        worker.execute();
    }

    @Override
    protected Void doInBackground() {
        task.execute();
        return null;
    }

    @Override
    protected void done() {
        success.execute();
    }

}
