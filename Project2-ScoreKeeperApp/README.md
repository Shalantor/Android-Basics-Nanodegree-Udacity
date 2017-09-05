# Score Keeper App

## Specifications

### Layout
* __Game Chosen:__ The chosen game has either multiple amounts of points that can be scored, as in american football, or multiple important metrics to track, such as fouls, outs, and innings in baseball. 
* __Overall layout:__ App is divided into two columns, one for each team.
* __Column contents:__ Each column contains a large TextView to keep track of the current score for that team.Optionally, a second TextView to track another important metric such as fouls can be added. 
* __Score buttons:__ Each column contains multiple buttons. The buttons must track either: 
  * Each track a different kind of scoring. 
  * Each track a different metric (one score, the other fouls, for instance). 
* __Reset button:__ The layout contains a ‘reset’ button.
* __Best practices:__ "The code adheres to all of the following best practices:
  * Text sizes are defined in sp
  * Lengths are defined in dp
  * Padding and margin is used appropriately, such that the views are not crammed up against each other.

### Functionality
* __Score Button Function:__ Each score button updates the score TextView in its column by adding the correct number of points.
* __Reset Button Function:__ The reset button resets the scores on both of the score TextViews.
