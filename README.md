# Payment-methods
This is a java application that gets a list of payment methods from a Payoneer API and displays them by inflating a recyclerView with the data gotten from the network call.

## Libraries used
* [Retrofit2](https://square.github.io/retrofit/) a REST Client library (Helper Library) used to process the HTTP response from a REST API.
* [ViewModel](https://developer.android.com/reference/android/arch/lifecycle/ViewModel#:~:text=Application%20context%20aware%20ViewModel%20.,calling%20the%20business%20logic%20classes) a class that is responsible for preparing and managing the data for an Activity or a Fragment.
* [Android navigation component](https://developer.android.com/guide/navigation) to make navigations between fragments and passing of data between destinations easier.
* [Glide](https://github.com/bumptech/glide) a fast and efficient open source media management and image loading framework for Android.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.