package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.YearJoined;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.UniqueTransactionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison) and (by .isSameTransaction comparison)
 */
public class PayBack implements ReadOnlyPayBack {

    private final UniquePersonList persons;
    private final UniqueTransactionList transactions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        transactions = new UniqueTransactionList();
    }

    public PayBack() {}

    /**
     * Creates an PayBack using the Persons in the {@code toBeCopied}
     */
    public PayBack(ReadOnlyPayBack toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the transaction list with {@code transactions}.
     * {@code transactions} must not contain duplicate transactions.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions.setTransactions(transactions);
    }

    /**
     * Resets the existing data of this {@code PayBack} with {@code newData}.
     */
    public void resetData(ReadOnlyPayBack newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTransactions(newData.getTransactionList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns a list of persons with the same identity as {@code person} in the address book.
     */
    public List<Person> getDuplicatePersons(Person person) {
        requireNonNull(person);
        return persons.getDuplicatePersons(person);
    }

    /**
     * Returns true if a person with the same ID as {@code id} exists in the address book.
     */
    public boolean hasPersonId(Id id) {
        requireNonNull(id);
        return persons.containsId(id);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code PayBack}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// transaction-level operations

    /**
     * Returns true if a transaction with the same ID as {@code transaction} exists in the address book.
     */
    public boolean hasTransaction(Transaction transaction) {
        requireNonNull(transaction);
        return transactions.contains(transaction);
    }

    /**
     * Adds a transaction to the address book.
     * The transaction must not already exist in the address book.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Removes the transaction from the address book.
     */
    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("transactions", transactions)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    public int getLastIdOnYear(YearJoined yearJoined) {
        return getPersonList().stream()
                .filter(person -> person.getYearJoined().equals(yearJoined))
                .mapToInt(person -> person.getId().value)
                .max()
                .orElse(yearJoined.value % 100 * 10000);
    }

    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PayBack)) {
            return false;
        }

        PayBack otherPayBack = (PayBack) other;
        return persons.equals(otherPayBack.persons)
                && transactions.equals(otherPayBack.transactions);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
