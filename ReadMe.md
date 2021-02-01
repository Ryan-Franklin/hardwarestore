# Welcome to the Assign02 ReadMe!

In this Markdown file in I will be going over the Small hardware store project. A project, which needs a graphical user interface to keep track of items in the inventory of the store, and to keep track of the employees and customers.
# Authors:
* Ryan Franklin
* Tory Brewer
#  Folder Used:
* hardwarestore
## Files in hardwarestore:

* Appliances.java
* Customer.java
* Employee.java
* HardwareStore.java
* Item.java
* User.java
* Transaction.java
* SmallHardwareItems.java
* Main.java

## How to compile:
* cd to dir above hardwarestore folder
* javac hardwarestore\\*.java
## How to run:
* cd to hardwarestore folder
* java Main

## UML Sequence Diagram:
UML diagram using [Mermaid](https://mermaidjs.github.io/). 


```Mermaid
sequenceDiagram
participant Emplolyee
participant interface
participant HardwareStore
participant transaction
Emplolyee ->> interface: 1. enters item id
interface ->> HardwareStore: 2. DNE: add item
Emplolyee ->> interface: 3. enters sale quantity
Note right of Emplolyee: Loop until valid<br/>input.
Emplolyee ->> interface: 4. enters employee ID#
interface ->> HardwareStore: 5. DNE: add employee user
Emplolyee ->> interface: 6. enters user ID
interface ->> HardwareStore: 7. DNE: add customer user
Emplolyee ->> interface: 8. valid transaction
interface ->> HardwareStore: 9. transact
HardwareStore ->> transaction: new Transaction
Note right of HardwareStore: Finished a valid<br/>transaction, so on<br/>to the next<br/> transaction.

```

