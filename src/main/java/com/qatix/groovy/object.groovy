package com.qatix.groovy

car = {
    name: 'HSV Maloo'
    make: 'Holden'
    year: 2006
    country: 'Australia'
    homepage:
    new URL('http://example.org')
    record:
    {
        type: 'speed'
        description: 'production pickup truck with speed of 271kph'
    }
}

println(car.name)
