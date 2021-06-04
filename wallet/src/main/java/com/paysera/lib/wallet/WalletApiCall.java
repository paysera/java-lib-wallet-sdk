package com.paysera.lib.wallet;

import bolts.TaskCompletionSource;

public abstract class WalletApiCall {
    private TaskCompletionSource taskCompletionSource;

    public TaskCompletionSource getTaskCompletionSource() {
        return taskCompletionSource;
    }

    public void setTaskCompletionSource(TaskCompletionSource taskCompletionSource) {
        this.taskCompletionSource = taskCompletionSource;
    }
}