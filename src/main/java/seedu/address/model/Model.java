package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.YearJoined;
import seedu.address.model.transaction.Transaction;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Transaction> PREDICATE_SHOW_ALL_TRANSACTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getPayBackFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setPayBackFilePath(Path payBackFilePath);

    /**
     * Replaces address book data with the data in {@code payBack}.
     */
    void setPayBack(ReadOnlyPayBack payBack);

    /** Returns the PayBack */
    ReadOnlyPayBack getPayBack();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns the last id on the given year.
     */
    int getLastIdOnYear(YearJoined yearJoined);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a transaction with the same ID as {@code transaction} exists in the address book.
     */
    boolean hasTransaction(Transaction transaction);

    /**
     * Adds the given transaction.
     * {@code transaction} must not already exist in the address book.
     */
    void addTransaction(Transaction transaction);

    /** Returns an unmodifiable view of the filtered transaction list */
    ObservableList<Transaction> getFilteredTransactionList();

    /**
     * Updates the filter of the filtered transaction list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTransactionList(Predicate<Transaction> predicate);
}
