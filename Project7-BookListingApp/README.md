# Book Listing App

## Specifications

### Layout
* __Overall Layout:__ App contains a ListView which becomes populated with list items.
* __List Item Layout:__ List Items display at least author and title information.
* __Layout Best Practices:__ The code adheres to all of the following best practices:
  * Text sizes are defined in sp
  * Lengths are defined in dp
  * Padding and margin is used appropriately, such that the views are not crammed up against each other.
* __Text Wrapping:__ Information displayed on list items is not crowded.
* __Rotation:__ Upon device rotation:
  * The layout remains scrollable.
  * The app should save state and restore the list back to the previously scrolled position.
  * The UI should adjust properly so that all contents of each list item is still visible and not truncated.
  * The Search button should still remain visible on the screen after the device is rotated.

### Functionality
* __API Call:__ The user can enter a word or phrase to serve as a search query. The app fetches book data related to the query via an HTTP request from the Google Books API, using a class such as HttpUriRequest or HttpURLConnection.
* __Response Validation:__ The app checks whether the device is connected to the internet and responds appropriately. The result of the request is validated to account for a bad server response or lack of server response.
* __Async Task:__ The network call occurs off the UI thread using an AsyncTask or similar threading object.
* __JSON Parsing:__ The JSON response is parsed correctly, and relevant information is stored in the app.
* __ListView Population:__ The ListView is properly populated with the information parsed from the JSON response.
* __No Data Message:__ When there is no data to display, the app shows a default TextView that informs the user how to populate the list.
* __External Libraries and Packages:__ The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries for core functionality will not be permitted to complete this project.

