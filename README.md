# WeatherApp Sample Project

## Deliverable description

For “WeatherApp” project, the MVVM architecture was used since it allows easier distinction between network (Repository, ApiClient), View (Activity Fragments) and business logic (ViewModel). It also makes the application more testable, compared to other architectures such as MVC or MVP.  This was combined with Single Activity so that every functionality belongs to a certain fragment, achieving further modularity. 
Some standard design patters were used, for example Singleton for the RetrofitService that is responsible for the network, Observers that “listen” to any change coming from the network along with MutableLiveData and Adapters for every List item.

Also RecyclerView was used for the lists, the Navigation component for Navigation through the app, SearchView for the search element and SnackBar of Materials for the error messages. As for the third-party libraries, Retrofit was used for the network due to its high stability as an easy and convenient okhttp wrapper. Junit was also used for the unit tests, while for loading images from the web, Glide library was used due to its easy-to-use implementation.
## Things for future improvement

Having limited time upon the project, there are things that could be added as features and some others that could be optimized for better maintainability.
One of these things is to optimize the queries of the API calls. There are lots of different parameters for the developer to customize each call to his liking, in order for him to include only the data he needs, excluding any extra data that is not required.
Another one of this type is to make call for fetching the data the displaying fragment requires each time. Right now, a generic call is being done when entering the fragment which displays the 5-day forecast but doesn’t update after the user enters into a day to check the hourly forecast.
Also, would not use deprecated “onActivityCreated” in “SearchAreasFragment”. Instead, either the favourites would get inside a ViewModel so that the fragment can observe when changes happen or a lifecycle observer component might have been implemented to observer when the “onCreate” method of the Activity will finish.
Buisness logic would be moved inside the ViewModel. Right now, most if not all of it is inside the fragments, which is not optimal if reusability is required.
Some other improvements are:

- Add different styles depending on the type of TextView. For example, a title TextView would have different attributes from an informational TextView.
- Explore the possibility of adding a theme to the application.
- Common layout components would be made in separate files and included where necessary.
- Make “Favourite” feature more centralized (methods onFavouriteChangeDrawable, onFavouriteFunctionality can go to activity and get parameters the elements they work with).
- Add “Favourite” functionality while searching for area.
- Have option to use Locale for days and time.
- Addition of splash screen.

