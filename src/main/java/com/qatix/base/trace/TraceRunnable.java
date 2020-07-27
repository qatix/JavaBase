package com.qatix.base.trace;

import com.qatix.base.trace.context.TraceContext;
import com.qatix.base.trace.context.TraceContextUtil;

public class TraceRunnable implements Runnable {
    //在初始化TraceRunnable时会获取调用线程的上下文
    private final Object context = TraceContext.getContext();
    private final Runnable runnable;

    public TraceRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
    @Override
    public void run() {
        //运行时把父线程的上下文保存
        Object backup = TraceContextUtil.backupAndSet(this.context);

        try {
            this.runnable.run();
        } finally {
            TraceContextUtil.restoreBackup(backup);
        }
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public static TraceRunnable get(Runnable runnable) {
        if (runnable == null) {
            return null;
        } else {
            return runnable instanceof TraceRunnable ? (TraceRunnable)runnable : new TraceRunnable(runnable);
        }
    }
}