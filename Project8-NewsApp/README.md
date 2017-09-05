# News App

## Specifications

### Layout
* __Main Screen:__ App contains a main screen which displays multiple news stories
* __List Item Contents:__ Each list item on the main screen displays relevant text and information about the story.Required fields include the title of the article and the name of the section that it belongs to.If available, author name and date published should be included. Please note not all responses will contain these pieces of data, but it is required to include them if they are present.
* __Layout Best Practices:__ The code adheres to all of the following best practices:
  * Text sizes are defined in sp
  * Lengths are defined in dp
  * Padding and margin is used appropriately, such that the views are not crammed up against each other.

### Functionality
* __Main Screen Updates:__ Stories shown on the main screen update properly whenever new news data is fetched from the API.
* __Story Intents:__ Clicking on a story opens the story in the user’s browser.
* __API Query:__ App queries the content.guardianapis.com api to fetch news stories related to the topic chosen by the student, using either the ‘test’ api key or the student’s key.
* __Use of Loaders:__ Networking operations are done using a Loader rather than an AsyncTask.
* __External Libraries and Packages:__ The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries for the core functionality will not be permitted to complete this project.
