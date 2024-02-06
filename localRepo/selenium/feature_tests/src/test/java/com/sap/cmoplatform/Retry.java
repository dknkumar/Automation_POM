package com.sap.cmoplatform;

import com.sap.cmoplatform.actions.DoAction;
import com.sap.cmoplatform.actions.GetAction;

public class Retry {

    /**
     * Invokes an action. If the action throws an exception it will retry at regular intervals
     * until it passes the timeout period.
     *
     * @param pollingInterval The interval between retrying the action
     * @param maxWait  The max wait time before an exception is thrown
     * @param action   The action to perform
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public static void doWithRetry(int pollingInterval, int maxWait, DoAction action) throws Exception {
        int totalTime = 0;
        while (true) {
            try {
                action.invoke();
                return;
            } catch (Exception e) {
                if (totalTime < maxWait) {
                    Thread.sleep(pollingInterval);
                } else {
                    throw e;
                }
            }
            totalTime += pollingInterval;
        }
    }

    /**
     * Invokes an action that returns an object. If the action throws an exception it will retry
     * at regular intervals until it passes the timeout period.
     *
     * @param pollingInterval The interval between retrying the action
     * @param maxWait  The max wait time before an exception is thrown
     * @param action   The action to perform
     * @return Object returns the result of action.invoke()
     * @throws Exception catch-all exception for a number of errors that the test may throw
     */
    public static Object getWithRetry(int pollingInterval, int maxWait, GetAction action) throws Exception {
        int totalTime = 0;
        while(true) {
            try {
                return action.invoke();
            } catch (Exception e) {
                if (totalTime < maxWait) {
                    Thread.sleep(pollingInterval);
                } else {
                    throw e;
                }
            }
            totalTime += pollingInterval;
        }
    }
}
