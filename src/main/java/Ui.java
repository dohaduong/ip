package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.task.*;
//import duke.MissingContentException;

public class Ui {
    public Ui(){}

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("File not existed");
    }

    public void showLine() {
        System.out.println("_____________________________");
    }


    /**
     * Prints out if input array is not empty
     *
     * @param arr input array.
     * @print instruction.
     * @throw MissingContentException if input array is empty.
     */
    public void findWordIntro(String[] arr, boolean containsKeyword) throws DukeException{
        if (arr.length >= 1) {
            if (containsKeyword) {
                System.out.println("Here are the matching tasks in your list:");
            } else {
                System.out.println("Sorry! No task found!");
            }
        } else {
            throw new DukeException("OPPS! The content can not be left empty!");
        }
    }
}