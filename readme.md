SwingChromecast
===============

Swing client to interact with your chromecasts.
This implementation is purely the frontend, it uses [Vitaly Litvak's
API](https://github.com/vitalidze/chromecast-java-api-v2), which is an implementation
of the ChromeCast V2 protocol. 

However, since the library is pulled from Maven you don't need to have it locally to run this
client. (Provided you fetch it from Maven).


# Description

This project aims to interact with the chromecasts on your network. (Note: Does not seem to work when you're
behind a VPN!)

## Current features
* Fetch data from your chromecast
* Play / Pause media that is playing
* Terminate running application
* Change the volume

## Future features
* Play local files from your computer



# License information

## Frontend
GNU GPLv3 
Copyright (c) 2018 Dylan Meeus (meeusdylan@protonmail.com)

## Backend

[chromecast-java-api-v2](https://github.com/vitalidze/chromecast-java-api-v2) Copyright (c) 2014
Vitaly Litvak vitavaque@gmail.com 
