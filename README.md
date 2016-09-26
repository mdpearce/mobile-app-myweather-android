# mobile-app-myweather-android
My Weather (Gumtree)

Notes
-----

The following functions are implemented:
* Search by city name or zip code
* Search by GPS (using FusedLocationProvider)
* Most recent search location loads automatically
* Multi-market support

Due to time constraints, a list of recent searches and the ability to delete them has not been implemented.

If I were to add this functionality, I would likely do it by providing a simple storage provider,
most likely a ContentProvider backed by a SQLite database, and write to that each time a search
was performed.

This would allow a simple page to be built using a RecyclerView backed by the ContentProvider to display
recent searches.

To delete them, either a simple long-press to bring up a delete confirmation dialog or a swipe mechanism
would be used. This would remove the item from the ContentProvider and notify the Adapter.

Future enhancements
-------------------

In a perfect world with more time, I would look at adding the following enhancements:
* Ability to search different countries zip/post codes
* Add local caching into the WeatherRepository (shouldn't have any effect on the public API)
* Add proper instance state management when rotating device (currently Activity is restarted and data is re-fetched)
* Add ability to set units from metric to imperial