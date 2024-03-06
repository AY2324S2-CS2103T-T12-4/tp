---
layout: page
title: User Guide
---

PayBack is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PayBack can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `payback.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your application.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar payback.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `/list` : Lists all contacts.

   * `/new jennie, 12334546, 123@gmail.com, 2021` : Adds a contact named `jennie` to the Address Book.

   * `/remove 240001` : Deletes the contact with id 240001.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `/new NAME`, `NAME` is a parameter which can be used as `/new John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times at least onxe.<br>
  e.g. `[t/TAG]…​` can be used as `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order, if specified.<br>
  e.g. if the command specifies `:name :phone`, `:phone :name` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `/help`, `/list`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a new employee: `/new`

Adds a new employee to the address book.

Format:
* `/new NAME, PHONE_NUMBER, EMAIL, [YEAR_JOINED]`
* `/new :name NAME :phone PHONE_NUMBER :email EMAIL [:year YEAR_JOINED]`

Examples:
* `/new jennie, 12334546, 123@gmail.com, 2021`
* `/new :name jennie :phone 12334546 :email 123@gmail.com :year 2021`

### Listing all persons : `list`

Show workers as a list. This can be used as “refresh” (e.g. after find command)

**Format:** `list`

### Editing a person : `/edit`

Edits an existing employee in the address book.

Format: `/edit ID [:name NAME] [:phone PHONE] [:email EMAIL] [:tag TAG]`

* Edits the person of the specified `ID`. The id refers to the 6-digits identity number. The id **must be 6 digits**: 240001, 240002...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, all the existing tags of the person will be removed.
* You can remove all the person’s tags by typing `:tag` without
    specifying any tags after it.

Examples:
*  `/edit 240001 :phone 91234567 :email: johndoe@example.com` Edits the phone number and email address of the person with id 240001 to be `91234567` and `johndoe@example.com` respectively.
*  `/edit 240002 :name Betsy Crower :tag` Edits the name of the person with id 240002 to be `Betsy Crower` and clears all existing tags.

### Searching Workers by keyword: `find`

Finds workers that contains any of the given keywords. It can be `ID`, `NAME`, `EMAIL` or `PHONE NUMBER`.

**Format:**
* `Find by name: /find :name [name]`
* `Find by email: /find :email [email]`
* `Find by phone number: /find :phone [phone number]`
* `Find by worker’s ID: /find :ID [ID]`

**Acceptable Format:**
* _Any letter cases are acceptable. e.g `Patrick` will match `patrick`_
* _ID: must be 6 digits of numbers_
* _Name: can be any case (Strings)_
* _Phone: must be numbers (integers)_
* _Email: must include “@”_
* _Only full keywords will be matched. e.g `Patrick` will not match `patr`_

Examples:
* `/find :name John` returns `john` and `John Doe`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `/remove ID`

* Deletes the person with the specified `ID`.

Examples:
* `/remove 240001` deletes the person with `240001` ID.

### Tagging a person: `tag`

Tags the specified person from the address book.

Format: `/tag ID t/TAG...`

* Tags the person with the specified `ID`.
* Allowed to have more than 1 tags per person.

Examples:
* `/tag 240001 t/finance t/manager` tags the person with `240001` ID with `finance` and `manager`.

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/payback.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If modifications to the data file result in an invalid format, PayBack will discard all data and initiate the next run with an empty data file. 
Therefore, it is advisable to create a backup of the file before making any edits. 
Additionally, specific changes may lead to unexpected behavior in PayBack, such as if a value entered falls outside the acceptable range. Hence, proceed with editing the data file only if you are certain that you can make accurate updates.
</div>

## Command summary

Action | Format, Examples
--------|------------------
**New** | `/new :name NAME :phone PHONE :email EMAIL [year joined] <br> e.g., `/new jennie, 12334546, 123@gmail.com, 2021`
**Delete** | `/remove ID`<br> e.g., `/remove 240001`
**Edit** | `/edit ID [:name NAME] [:phone PHONE] [:email EMAIL] [:tag TAG]`<br> e.g.,`/edit 240001 :phone 91234567 :email: johndoe@example.com`
**Find** | `/find :KEYWORD [KEYWORDS]`<br> e.g., `find :name John`
**List** | `/list`
**Help** | `/help`
