package io.micronaut.security.oauth2.docs.openid

import io.micronaut.core.annotation.Nullable
import io.micronaut.context.annotation.Requires
import io.micronaut.security.authentication.AuthenticationResponse;

//tag::clazz[]

import io.micronaut.security.oauth2.endpoint.authorization.state.State
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdClaims
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdTokenResponse
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdAuthenticationMapper

import io.micronaut.core.annotation.NonNull
import io.micronaut.security.token.config.TokenConfiguration
import jakarta.inject.Named
import jakarta.inject.Singleton

@Singleton
@Named("okta") // <1>
//end::clazz[]
@Requires(property = "docs.classes")
//tag::clazz[]
class OktaAuthenticationMapper implements OpenIdAuthenticationMapper {

    private final TokenConfiguration tokenConfiguration

    OktaAuthenticationMapper(TokenConfiguration tokenConfiguration) {
        this.tokenConfiguration = tokenConfiguration
    }

    @Override
    @NonNull
    AuthenticationResponse createAuthenticationResponse(String providerName, // <2>
                                                        OpenIdTokenResponse tokenResponse, // <3>
                                                        OpenIdClaims openIdClaims, // <4>
                                                        @Nullable State state) { // <5>
        AuthenticationResponse.build("name", tokenConfiguration) // <6>
    }
}
//end::clazz[]