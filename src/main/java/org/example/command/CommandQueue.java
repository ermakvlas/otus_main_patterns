package org.example.command;

/**
 * Очередь команд FIFO
 */
public interface CommandQueue {

    void addLast(Command command);

    Command getFirst();
}
