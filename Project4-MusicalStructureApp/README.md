# Musical Structure App

## Specifications

### App Design
* __Suitability:__ The app’s structure is suitable for a music player app. A similarly structured app which focuses on audiobooks, podcasts, or other audio media is also acceptable.
* __Clarity:__ Each activity is clearly labelled, using a TextView, such that the final purpose of such an activity is easy to understand. For instance, one screen might be labelled ‘song detail screen’ and another might be labelled ‘music library’
* __Plan for Creation:__ App must contain a Payment Activity. Student should find an external library or API that can be used in this situation. In the TextView of that activity, describe the library or API and how it can be used.Any other Activity that requires an external library or class not yet covered also includes the name of that library or class.
* __Number of Activities:__ The app contains 3 to 6 activities.

### Layout
* __Structure:__ The app contains multiple activities, each labelled, which together make a cohesive music app. 
* __Labelling:__ Each activity contains a TextView which explains the purpose of the activity. 
* __Buttons:__ Each activity contains button(s) which link it to other activities a user should be able to reach from that activity. For instance, a ‘Library’ activity might contain a button to move to the ‘Now Playing’ activity. 
* __Layout Best Practices__: The code adheres to all of the following best practices:
  * Text sizes are defined in sp
  * Lengths are defined in dp
  * Padding and margin is used appropriately, such that the views are not crammed up against each other.
  
### Functionality
* __OnClickListeners:__ Each button’s behavior is determined by an OnClickListener in the Java code rather than by the android:onClick attribute in the XML Layout. 
* __Intents:__ Each button properly opens the intended activity using an explicit Intent. 
