# My Personal Project: Travel Planner
### Organize, Customize and Manage Your Travel Plans!

**Possible Features:**  
- itinerary creation
- itinerary management (calendar view)
- budget tracking + splitting of costs
- journaling + current weather conditions
- reminders 
- packing list
- currency converter (offline)

**Possible Users:**
- solo travellers
- groups of friends
- extended families
- travel agencies

**Why is this project of interest to you?**  
As an avid traveller and more often than not, the 
designated travel planner, I always found it 
hard to plan itineraries. Until today, I use Google 
sheets to create my itineraries. However, this is not
the most efficient nor is it user-friendly.
As such, I wanted to create an app that would 
serve as an all-in-one travel buddy. 

**User Stories:**
- PHASE 1:
  - As a user, I want to reset my itinerary (delete all plans)
  - As a user, I want to be able to remove plans from my itinerary.
  - As a user, I want to be able to add plans to my itinerary.
  - As a user, I want to be able to view my itinerary.
  
- PHASE 2:
  - As a user, I want to be able to save my itinerary list to file (if I so choose)
  - As a user, I want to be able to be able to load my itinerary from file (if I so choose)

**Instructions for Grader**

- You can generate "add multiple Xs to a Y" by clicking on the Add Plan Button
- You can generate "delete X from a Y" by clicking on the Delete Plan Button
- You can generate "reset itinerary (delete all plans)" by clicking on the Reset Itinerary Button
- You can locate my visual component by opening the app, and after going in the dashboard, 
  the visual component acts as a button to go back to the previous page.
- You can save the state of my application by clicking on the save itinerary button.
- You can reload the state of my application by load itinerary button.

**Phase 4 Task 2**
Sun Apr 07 20:00:07 PDT 2024
Added plan with location: Burnaby
Sun Apr 07 20:00:23 PDT 2024
Added plan with location: SFU
Sun Apr 07 20:00:36 PDT 2024
Added plan with location: UBC
Sun Apr 07 20:01:01 PDT 2024
Added plan with location: Downtown
Sun Apr 07 20:01:06 PDT 2024
Removed plan with location: Downtown
Sun Apr 07 20:01:18 PDT 2024
Added plan with location: Downtown
Sun Apr 07 20:01:22 PDT 2024
Itinerary Reset

**Phase 4 Task 3**
- If I had more time, I think I would use the observer design method so that my UI (dashboard) would be notified of the
  changes by being an observer. This reduces coupling so that when I change my itinerary it does not ripple through 
  the other parts and cause the other parts to break.
- How I would do it: 
  - Create two classes (Observer, Observable). 
  - Make itinerary extend Observable and DashboardPanel to implement Observer. 
  - Create an update method in the DashboardPanel which would update the panel when notified. 
  - Create a notifyObserver method to notify observers of the change.
  - Add DashboardPanel into the list of Observers itinerary.add(dashboardPanel).
