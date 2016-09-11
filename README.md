# Spring pragmatic testing

## Step 1

    $ http :8080/greet/kirill
    
    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Date: Sun, 11 Sep 2016 11:04:53 GMT
    Transfer-Encoding: chunked
    X-Application-Context: application
    
    {
        "name": "kirill",
        "say": "Hello kirill"
    }

See the GreetingControllerTest.java. Say some words about Unit test problems and go to write integration tests.

### Results

* `./gradlew build`
* `open build/reports/tests/index.html`

## Step 2