# display-NASA-languages

Display the diversity of programming languages NASA uses in their GitHub account.

To run, compile with maven and deploy to Glassfish. The url to load the languages is <i>path-to-app</i>/languages.

The code has been written in a very readable manner so it should be self-explanatory.

It uses Spring MVC, JUnit, RestTemplate and MockRestServiceServer.

It is a Maven project and follows the maven folder structure convention.

At the front end it uses <a href="http://www.chartjs.org">Chart.js</a> to render a bar chart displaying each language and the
number of lines of code for each language across the NASA repositories on GitHub.
