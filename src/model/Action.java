package model;

public interface Action {
    boolean execute() throws ExitException;
}
