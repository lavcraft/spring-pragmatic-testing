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

* Add `@MockMvcTest`. Avoid boileplate code. 
* Add test with full context load `ApplicatoinTests.java`
* Some words about MockMvc integrations with SpringSecuriry

## Step 3

    http :8080/pokemon/bulbosaur
    
    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Date: Sun, 11 Sep 2016 14:21:05 GMT
    Transfer-Encoding: chunked
    X-Application-Context: pragmatic-testing
    
    {
        "name": "bulbosaur",
        "power": 0.4175532166907524
    }
    
* Add `PokemonControllerTest.java`. 
* If you try run this test without @MockBean, you have a error:

```
ResourceAccessException: I/O error on GET request for 
"http://localhost:10000": Connection refused; nested exception is java.net.ConnectException: Connection refused
```

Fix test with `@MockBean` or `@SpyBean` 

## Step 4

Do you remember about external call? No? But we have a call to pokemon rest repository!

    ResponseEntity<Double> forEntity = restTemplate
        .getForEntity(
            pokemonHome.getHome(), 
            Double.class
        );

Do you want test it? Wiremock help you! But you have to say some words about `@RestClientTest` and `MockRestServiceServer`
Let`s go:

* Add WireMock dependency `compile 'com.github.tomakehurst:wiremock-standalone:2.1.9'` to build.gradle
* Use WireMock JUnit rule in our tests. See `MockMvcBaseTest.java`


    @Rule
    public WireMockRule wireMockRule = new WireMockRule(
        WireMockConfiguration.wireMockConfig()
            .port(12000)
            .notifier(new ConsoleNotifier(true))
    );
    
* Add stub or verify logic in some tests:
 
Stub:

    stubFor(WireMock.get(
        urlEqualTo("/pokemone/Bulbasaur"))
        .willReturn(aResponse()
            .withStatus(200)
            .withBody("14.5")
        )
    );
    
And verify:
    
    wireMockRule.verify(
        getRequestedFor(
            urlEqualTo("/pokemonRepository/Bulbasaur")
        ).withoutHeader("Content-Type"));