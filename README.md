Elwha
======

<img src="./docs/logo.png" align="right" width="300" />

# Introduction
Elwha is a Java application for monitoring topics, sentiment and events on Twitter streams 
with the ability to generate notification messages before passing the presence of such 
events on to some additional workflow. An example of an additional workflow could be 
an [Apache Nutch](http://nutch.apache.org) crawl, or something similar.

The name Elwha itself relates to the [River Elwha](https://en.wikipedia.org/wiki/Elwha_River), a 
45-mile (72 km) river on the Olympic Peninsula in the U.S. state of Washington. From its source 
at Elwha snowfinger in the Olympic Range of Olympic National Park, it flows generally north to 
the Strait of Juan de Fuca. Most of the river is in Olympic National Park.

# Build, Installation and Usage

This is a rest.li application using gradle as the build system.

To build for the first time, use gradle 1.8 or greater and run:

```
gradle build
```

You can then run the server with:

`gradle JettyRunWar`

Once running, you can send a GET request to the server with:

`curl http://localhost:8080/elwha-server/elwha/1`

# Features

# License
Elwha is licensed under the [Apache License v2.0](http://www.apache.org/licenses/LICENSE-2.0).
A copy of that license is included with this source code.
