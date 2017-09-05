# Inventory App

## Specifications

### Layout
* __Overall Layout:__ The app contains a list of current products and a button to add a new product.
* __List Item Layout:__ Each list item displays the product name, current quantity, and price. Each list item also contains a Sale Button that reduces the quantity by one (include logic so that no negative quantities are displayed).
* __Detail Layout:__ 
  * The Detail Layout for each item displays the remainder of the information stored in the database.
  * The Detail Layout contains buttons that increase and decrease the available quantity displayed.
  * The Detail Layout contains a button to order from the supplier.
  * The detail view contains a button to delete the product record entirely.
* __Layout Best Practices:__ The code adheres to all of the following best practices:
  * Text sizes are defined in sp
  * Lengths are defined in dp
  * Padding and margin is used appropriately, such that the views are not crammed up against each other.
* __Default Textview:__ When there is no information to display in the database, the layout displays a TextView with instructions on how to populate the database. 

### Functionality
* __ListView Population:__ The listView populates with the current products stored in the table.
* __Add product button:__ The Add product button prompts the user for information about the product and a picture, each of which are then properly stored in the table.
* __Input Validation:__ User input is validated. In particular, empty product information is not accepted. If user inputs product information (quantity, price, name, image), instead of erroring out, the app includes logic to validate that no null values are accepted. If a null value is inputted, add a Toast that prompts the user to input the correct information before they can continue.
* __Sale Button:__ In the activity that displays a list of all available inventory, each List Item contains a Sale Button` which reduces the available quantity for that particular product by one (include logic so that no negative quantities are displayed).
* __Detail View Intent:__ Clicking on the rest of each list item sends the user to the detail screen for the correct product.
* __Modify Quantity Buttons:__ The Modify Quantity Buttons in the detail view properly increase and decrease the quantity available for the correct product.The student may also add input for how much to increase or decrease the quantity by.
* __Order Button:__ The ‘order more’ button sends an intent to either a phone app or an email app to contact the supplier using the information stored in the database.
* __Delete Button:__ The delete button prompts the user for confirmation and, if confirmed, deletes the product record entirely and sends the user back to the main activity. 
* __External Libraries and Packages:__ The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries for core functionality will not be permitted to complete this project.
