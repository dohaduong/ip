package duke.command;

import java.io.IOException;

import duke.exception.InvalidIndexException;
import duke.exception.MissingContentException;
import duke.task.Task;

/**
 * Handles data and formats into tasklist
 */
public class TaskList {
    private String[] arr;

    public TaskList() {
        arr = new String[100];
    }

    /**
     * Initialize a new TaskList object
     * @param arr
     */
    public TaskList(String[] arr) {
        this.arr = arr;
    }

    /**
     * Returns the tasklist in array
     * @return the tasklist in array
     * @throws IOException if array does not exist
     */
    public String[] readTaskList() throws IOException {
        try {
            try {
                return this.arr;
            } catch (IndexOutOfBoundsException e) {
                throw new MissingContentException();
            }
        } catch (MissingContentException e) {
            System.out.println(e.getMessage());
        }
        return this.arr;
    }

    /**
     * Checks if task list is empty or not
     * @return true if task list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.validLen() == 0;
    }

    /**
     * Lists tasks inside tasklist.
     *
     * @return task one-by-one.
     */
    public String list() {
        String res = "";
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == null) {
                break;
            }
            res += String.format("%d.%s", j + 1, arr[j]);
            res += "\n";
        }
        return("Here are the tasks in your list:" + "\n" + res);
    }

    /**
     * Returns the real length of the array.
     * Maximum index in which array at such index is not null.
     *
     * @return the first null index.
     */
    public int validLen() {
        int len = 0;
        while (arr[len] != null) {
            len++;
        }
        return len;
    }

    /**
     * Returns new task list.
     * Marks task at given index as done.
     *
     * @param num index at which task need to be marked as done.
     * @return new task list with task marked.
     */
    public String mark(int num) throws IOException {
        try {
            try {
                if (arr[num] != null) {
                    String original = arr[num];
                    arr[num] = new Task(String.valueOf(original.charAt(1)),
                            original.substring(7), true).toString();
                    return("OK, I've marked this task as done:" + "\n" + arr[num]);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
        } catch (InvalidIndexException e) {
            return(e.getMessage());
        }
        return "";
    }

    /**
     * Returns new task list.
     * Unmarks task at given index as done.
     *
     * @param num1 index at which task need to be marked as undone.
     * @return new task list with task unmarked.
     * @throw InvalidIndexException if array at specific index is null
     */
    public String unmark(int num1) throws IOException {
        try {
            try {
                //if (arr[num1] != null) {
                    String original = arr[num1];
                    Task newTask = new Task(String.valueOf(original.charAt(1)),
                            original.substring(7), false);
                    arr[num1] = newTask.toString();
                    return("OK, I've marked this task as not done yet:" + "\n" + arr[num1]);
                //}
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
        } catch (InvalidIndexException e) {
            return(e.getMessage());
        }
        //return(""); //new TaskList(arr);
    }

    /**
     * Checks if element at given index of array is valid or not.
     *
     * @param index at which content need to be checked.
     * @return boolean for content validity.
     * @throw InvalidIndexException if array at specific index is null
     */
    public boolean checkValidIndex(int index) {
        return (arr[index] != null);
    }

    /**
     * Returns new task list.
     * Deletes task at given index.
     *
     * @param num1 index at which task need to be deleted.
     * @return new task list with task deleted.
     * @throw InvalidIndexException if array at specific index is null if array at specific index is null
     */
    public String delete(int num1) throws IOException {
        try {
            try {
                if (num1 < this.validLen()) {
                    String original = arr[num1];
                    int trace = num1;
                    String[] originalList = new String[100];
                    for (int k = 0; k < 100; k++) {
                        originalList[k] = arr[k];
                    }

                    arr[trace] = arr[trace + 1];
                    trace++;

                    while ((trace >= 1) && (originalList[trace - 1] != null)) {
                        arr[trace] = originalList[trace + 1];
                        trace++;
                    }
                    return("Noted. I've removed this task:" + "\n" + original + "\n"
                            + String.format("Now you have %d "
                            + "tasks in the list", this.validLen()));
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
        } catch (InvalidIndexException e) {
            return(e.getMessage());
        }
        return new InvalidIndexException().getMessage();
        //return this;
    }


    /**
     * Returns new task list.
     * Adds task to the back.
     *
     * @param task needed to be added.
     * @return new task list with task added.
     */
    public String add(Task task) {
        int len = this.validLen();
        arr[len] = task.toString();
        return("Got it. I've added this task:" + "\n" + task.toString()
                + "\n"
                + String.format("Now you have %d "
                + "tasks in the list", this.validLen()));
        //return new TaskList(arr);
    }

    /**
     * Prints out tasks that contain the given keyword.
     *
     * @param keyWord given keyword.
     * @print tasks that contain the given keyword in task list.
     */
    public String findWord(String keyWord) {
        int trace = 0;
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            } else if (arr[i].contains(keyWord)) {
                trace++;
                res += (String.format("%d. %s", trace, arr[i]));
                res += "\n";
            }
        }
        return("WOOF! Here  are the matching tasks in your list:" + "\n" + res);
    }

    /**
     * Checks if there is any task that contains the given keyword.
     *
     * @param keyWord given keyword.
     * @return true if there is any task contains the keyword and false otherwise.
     */
    public boolean checkWord(String keyWord) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            } else if (arr[i].contains(keyWord)) {
                return true;
            }
        }
        return false;
    }
}
