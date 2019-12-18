# Ixigo Search

### General Guidelines

Feel free to use any libraries, frameworks or tools of your choice.
We would really like a ​well-organized and ​well-commented codebase. This will help us understand your thought process.
We want to see a high quality codebase that follows standard Java/Android coding patterns. The codebase should be modular, reusable, scalable and to the point. In a nutshell, ​code quality and design​ are of utmost importance here.
Bonus points will be awarded if your app supports orientation change.
Do not worry about Android 4.x. Your app should support Android 5.0 and higher.
Ideally it should not take you more than a couple of hours to finish this challenge.
If you have any doubts, questions or feedback, please feel free to approach us.

### Submission Guidelines

The test solution is to be submitted using a Git repository hosted on ​gitlab.com​.
The repo should be shared with the Gitlab user ​androidchallenge@ixigo.com​ for review.
The repo should follow the naming convention: android_challenge_<firstname>_<lastname>​. 
For example: ​android_challenge_ajay_gupta
The app module of the project should contain an APK directory containing the compiled .apk file of the test solution.

### The Challenge

We would like you to build a ​Flight Search app​. The app should display a list of sample flights between Delhi and Mumbai. Each flight in the list should have fares from multiple booking providers.

Example:
An Air India flight from Delhi (DEL) to Mumbai (BOM) can have two fares: ​₹​4500/- @ MakeMyTrip and ₹4600 @ Cleartrip. Similarly, a DEL-BOM SpiceJet flight can have three fares: ₹​4500/- @ MakeMyTrip, ₹4600 @ Cleartrip and ₹4400 @ Musafir.
Revision 2.3

Sample flight results can be requested from the following URL:
http://www.mocky.io/v2/5979c6731100001e039edcb3
Description of the response:
The ​"flights" array holds a list of sample flights objects. Each object contains information about a specific flight.
```
  {
    "originCode": "DEL", "destinationCode": "BOM", "departureTime": 1396614600000, "arrivalTime": 1396625400000, "fares": [{
    "providerId": 1,
    "fare": 11500 }, {
    "providerId": 2,
    "fare": 10500 }],
    "airlineCode": "G8", "class": "Business"
  }
```

Where:
```
  “originCode”​: The origin airport code (IATA)
  “destinationCode”​: The destination airport code (IATA)
  “departureTime”​: The flight departure timestamp
  “arrivalTime”​: The flight arrival timestamp
  “fares”​: An array of providerId and corresponding fare, i.e an array containing flight fares from different booking providers
  “airlineCode”​: The airline code
  “class”​: The class of travel (Economy/Business)
  The ​“appendix” object contains details about airlines, airports, and booking providers. This object should be used to reference airline, airport and provider names.
```

Using this data, create an app that displays flights in a list with all relevant information. The app needs to:
Display every flight clearly with relevant information like provider-wise fares, travel class, take-off/landing times, airport names, flight duration, etc.
Support simple sorting options to sort the flights based on their minimum fare, take-off and landing times.
Apply various techniques to keep the scrolling and overall experience smooth and fluid.

Please note that the supplied JSON data ​MUST be loaded asynchronously from the provided URL on a background thread.
You can take a look at our flights app (​link​) for inspiration.​ Happy coding! :)





