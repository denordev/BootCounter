“Boot counter” app

In this exam, you are asked to write the “Boot counter” Android app.

Guidelines

● Take 3 hours to do this exam. Writing this app can take more than 3 hours, and so finish whatever 
you can in the 3 hours, and provide a list mentioning the TODOs left for you to finish this task.
● The exam should be written in Kotlin.
● Use proper OOP design and follow SOLID principles.
● The app’s minSdkVersion should be 24.
● The app’s targetSdkVersion should be 34.
● Use a database for persisting values.
● Address edge cases.

● When done, share:
    ○ Source code (git, not zip or any other archive)
    ○ APK
    ○ List of TODOs (in case you do not finish the task)


The app

The aim of this application is to monitor when the device boots up and to set up a repeating task for
displaying notifications containing boot event details. Users can dismiss these notifications, which will prompt the app to reschedule them accordingly.

Boot event

The application must listen for the RECEIVE_BOOT_COMPLETED event.
Once the event is triggered, the application must persist such an event with the relevant information 
(required in the next sections).

Application

Any time the application is getting awakened, it must schedule the action to be executed.
General information about the action:
    It must run every 15 minutes (15 min rule).
    On run it must show the notification with the “special” body.
    Restore the notification with the updated information on the boot event ONLY IF the notification was present before the (re)boot.

Notification dismiss action:
    It should be rescheduled based on the configuration and shouldn’t take into account the 15 minutes rule.

Note
The title, channel and others missing information does not have any limitations/requirements.
“Special” body

There are 3 possible text within the notification body:
    - If no boot events were detected within the app’s lifetime - the body text should be = “No boots detected”
    - If only 1 boot event was detected, then the text must be = 
        “The boot was detected = ${date_of_the_boot_event}”. “date_of_the_boot_event” is DD/MM/YYYY HH:MM:SS
    - If multiple events were detected, then the text should be = 
        “Last boots time delta = ${time_between_2_last_boot_events}”. “Time_between_2_last_boot_events” must be the delta between last and pre-last boot events.

Notification dismiss action
    Upon dismissal, the notification should be rescheduled to reappear according to the following setup:
    Total dismissals allowed: 5
    Interval between dismissals: Dismissal count * 20 minutes

Notification content: must adhere to the "Special" body requirements.
If the total number of dismissals exceeds the limit, notification scheduling should revert to the 15-minute rule. 
For example, if the user dismisses the notification 5 times, the next notification should appear in 15 minutes, and further dismissals should trigger the behavior outlined above.

UI
Any View component can be used to display the info. For example it can be a simple TextView.

The UI should show different text depending on the information:
If no boot events were triggered within the app’s lifetime, then text must be = “No boots detected”
If there were boot events during the app’s lifetime then text view must be populated with the info of each boot event:
Date of the boot events (e.g. 01/04/2024)
Number of boot events per day (e.g. 5, 2, 3).
Should have the ability to change the “Total dismissals allowed” and “Interval between dismissals” using UI components (can be simple edit texts).
Example of the UI text
01/04/2024 - 5
02/04/2024 - 10
06/04/2024 - 3


Note
Consider adding “--receiver-include-background” if it’s planned to verify “boot events” via ADB. 


