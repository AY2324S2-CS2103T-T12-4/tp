package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transaction.exceptions.DuplicateTransactionException;

/**
 * A list of transactions that enforces uniqueness between its elements and does not allow nulls.
 * A transaction is considered unique by comparing using {@code Transaction#isSameTransaction(Transaction)}.
 * As such, adding and updating of transactions uses Transaction#isSameTransaction(Transaction) for equality so as to
 * ensure that the transaction being added or updated is unique in terms of identity in the UniqueTransactionList.
 * However, the removal of a transaction uses Transaction#equals(Object) so as to ensure that the transaction with
 * exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Transaction#isSameTransaction(Transaction)
 */
public class UniqueTransactionList implements Iterable<Transaction> {

    private final ObservableList<Transaction> internalList = FXCollections.observableArrayList();
    private final ObservableList<Transaction> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent transaction as the given argument.
     */
    public boolean contains(Transaction toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTransaction);
    }

    /**
     * Adds a transaction to the list.
     * The transaction must not already exist in the list.
     */
    public void add(Transaction toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTransactionException();
        }
        internalList.add(toAdd);
    }

    public void setTransactions(UniqueTransactionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code transactions}.
     * {@code transactions} must not contain duplicate transactions.
     */
    public void setTransactions(List<Transaction> transactions) {
        requireAllNonNull(transactions);
        if (!transactionsAreUnique(transactions)) {
            throw new DuplicateTransactionException();
        }

        internalList.setAll(transactions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Transaction> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Transaction> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTransactionList)) {
            return false;
        }

        UniqueTransactionList otherUniqueTransactionList = (UniqueTransactionList) other;
        return internalList.equals(otherUniqueTransactionList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code transactions} contains only unique transactions.
     */
    private boolean transactionsAreUnique(List<Transaction> transactions) {
        for (int i = 0; i < transactions.size() - 1; i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                if (transactions.get(i).isSameTransaction(transactions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
