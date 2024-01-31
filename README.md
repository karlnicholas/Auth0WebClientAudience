### Springframework Security for OAuth2 with Auth0.com authorization service. 

When using [Auth0.com](https://auth0.com) an application needs to pass in requested audience . When an  application calls an API the Auth0 developer platform requires an "audience" parameter in the request header that Spring Security OAuth2 doesn't include by default.

This example shows how to set the audience request parameter when using SpringFramework Security OAuth2 to do Client Credentials flows, i.e., calling an API or Resource Server. 

The `SecurityConfiguration` class is configured to use JWT access token. This is standard. The application.yml property file has an optional audience parameter set. The `WebClientConfiguration` class includes code to pass the header when making a call to an API with `WebClient`.

A simple `MovieApi` class gives a list of movies for the Front End to display for a given user.

Auth0.com has proprietary libraries that their tutorials cover but this is not helpful for getting experience with SpringFramework Security and OAuth0 security providers.

It's probably more common to stand up a KeyCloak authorization server in Docker and experiment against that.

