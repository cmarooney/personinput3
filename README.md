# personinput3
Exercise in creating validated input form in Java. 
Exercise was to create an input form with fields for:
* given name
* surname 
* phone number
* email
* address line 1
* address line 2
* county
* country
* postcode

and validate them on input.

Comments:

* Chose to group inputs in two bordered groups, 
one for personal details, one for address
* Separated GUI display and validation 
by having an array of input labels and textfields 
which are added later to the GUI panels, and can be
validated all at once from the array
* Current signalling of validation is partly by 
output to console and partly by colour-coding the
panel fields. There is insufficient output to 
tell the user exactly what is wrong.
* There is no intent in this exercise to use the
data but it would be helpful to have such a rudimentary
use to validate the design and its ability to be
incorporated in a larger structure.
* Validation is against a single pattern and more could
be done to sanity check the input (even before 
considering more advanced features like locale)
