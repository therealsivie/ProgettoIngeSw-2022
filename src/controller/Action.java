package controller;

public interface Action {
    boolean execute() throws ExitException;
}
