# Quiz App

## Specifications

### Layout
* __Overall Layout:__ App contains 4 - 10 questions, including at least one check box, one radio button, and one text entry. 
* __Question types:__
  * Questions are in a variety of formats such as free text response, checkboxes, and radio buttons.
  * Checkboxes are only used for questions with multiple right answers. Radio buttons are only used for questions with a single right answer. 
* __Submit button:__ App includes a button for the user to submit their answers and receive a score. 
* __Layout best practices:__ The code adheres to all of the following best practices:
  * Text sizes are defined in sp
  * Lengths are defined in dp
  * Padding and margin is used appropriately, such that the views are not crammed up against each other.
* __View variety:__ The app includes at least four of the following Views: TextView, ImageView, Button, Checkbox, EditText, LinearLayout, RelativeLayout, ScrollView, RadioButton, RadioGroup.If applicable, the app uses nested ViewGroups to reduce the complexity of the layout.
* __Rotation:__ The app gracefully handles displaying all the content on screen when rotated. Either by updating the layout, adding a scrollable feature or some other mechanism that adheres to Android development guidelines. 

### Functionality
* __Question Answers:__ Each question has a correct answer.
* __Radio Button Implementation:__ Any question which uses radio buttons allows only one to be checked at once. 
* __Control Statements:__ The app contains at least one if/else statement
* __Grading Button Function:__ The grading button displays a toast which accurately displays the results of the quiz.
* __Grading Logic:__ The grading logic checks each answer correctly. The app accurately calculates the number of correct answers and does not include incorrect answers in the count. When applicable, in the grading logic remember to check that the correct answers are checked AND the incorrect answers are not checked.
