package com.redhat;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

@Liveness
@ApplicationScoped
public class LivenessProbe implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] threadIds = bean.findDeadlockedThreads(); // Returns null if no threads are deadlocked.

        if (threadIds != null) {
            ThreadInfo[] infos = bean.getThreadInfo(threadIds);

            for (ThreadInfo info : infos) {
                StackTraceElement[] stack = info.getStackTrace();
                // Log or store stack trace information.
            }

            return HealthCheckResponse.down("dead lock.");
        }
        return HealthCheckResponse.up("success.");
    }
}
