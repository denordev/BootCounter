package pl.denordev.bootcounter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()


/*TODO:
    1. find out why Broadcast receiver doesn't work *_*
    2. add worker for notifications
    3. refactor Room to flow, to make code reactive
    4. Add logic to ask user enable notifications
    5. implement notifications handling according to the requirements ( cover all cases)
*  */