
#Frequently asked questions on the Disruptor

#Why is it called the Disruptor?
Well there are two reasons. Primarily we wanted to disrupt the common assumptions in this space because we think that they are wrong. But, to be honest, we also couldn’t resist the temptation; There was some talk about Phasers in Java at the time when we named it and, for those of you too young to care, Phasers were the Federation weapon and Disruptors the Klingon equivalent in Star Trek.

# Are the entries exchanged in a strict FIFO manner?
Yes all entries exchanged in a strict FIFO manner based on the claim sequence.

# Do you relax the consistency model to gain performance?
No, all changes to an event are made immediately available to the EventProcessors of the RingBuffer when the sequence is published. This is key to low-latency performance.

# How do you arrange a Disruptor with multiple consumers so that each event is only consumed once?
There are 2 approaches, the first is to use the WorkerPool. The second it to use the 'striped' EventHandler approach described below.

If we have 4 handlers and assign each an ordinal (0 through 3), then the consumer need only do a modulo operation using the sequence number and the number of consumers and compare it to its ordinal value.
```
public final class MyHandler implements EventHandler<ValueEvent>
{
    private final long ordinal;
    private final long numberOfConsumers;

    public MyHandler(final long ordinal, final long numberOfConsumers)
    {
        this.ordinal = ordinal;
        this.numberOfConsumers = numberOfConsumers;
    }

    public void onEvent(final ValueEvent entry, final long sequence, final boolean onEndOfBatch)
    {
        if ((sequence % numberOfConsumers) == ordinal)
        {
            // Process the event
        }
    }
}
```
Some would ask if one consumer takes too long on a transaction it will block all queued on that ordinal. Technically this is possible but one must consider that the batching effect then kicks in thus saving cost for those behind. With this approach the concurrency costs are so low you may find even small stalls are less costly than a queue based alternative.

# What size should I make the ring buffer?
For very high performance the ring buffer, and its contents, should fit in L3 CPU cache for exchanging between threads. If the ring buffer is used for a replay scenario, such as market data or network recovery, it may be larger and have obvious performance implications from the cache misses.