### Springframework Security for OAuth2 with Auth0.com authorization service. 

When using [Auth0.com](https://auth0.com) an application needs to pass in requested audience for authorization and client credential. In other words, when a user does login and the application calls an API the Auth0 developer platform requires an "audience" parameter in the request header that Spring Security OAuth2 doesn't include by default.

This example shows how to set the audience request parameter when using SpringFramework Security OAuth2 to do Authorization Code and Client Credentials flows. 

The `SecurityConfiguration` class includes additional code to pass in the "audience" header when login. The `WebClientConfiguration` class includes code to pass the header when making a call to an API with `WebClient`.

A simple `MovieApi` class gives a list of movies for the Front End to display for a given user. The user ID is retrieved from the `sub` of JWT token so that a user ID does not have to be passed to the API call.

Auth0.com has proprietary libraries that their tutorials cover but this is not helpful for getting experience with SpringFramework Security and OAuth0 security providers.

It's probably more common to stand up a KeyCloak authorization server in Docker and experiment against that.

