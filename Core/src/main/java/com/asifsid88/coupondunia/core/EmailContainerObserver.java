package com.asifsid88.coupondunia.core;

/**
 * Leveraging Observer design pattern.
 */
public interface EmailContainerObserver {
    /*
    All observers are mutually exclusive to each other. No-one knows about others' existence
    But still, we need a mechanism by which it will intimate (notify) others to perform certain action
     */
    void trigger();

    /*
    To distinguish between observers
     */
    String getName();
}
