package io.micronaut.docs.security.authentication

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationException
import io.micronaut.security.authentication.AuthenticationFailed
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.config.TokenConfiguration
import reactor.core.publisher.FluxSink
import reactor.core.publisher.Flux
import org.reactivestreams.Publisher

import jakarta.inject.Singleton

// Although this is a Groovy file this is written as close to Java as possible to embedded in the docs

@Requires(property = "spec.name", value = "authenticationparam")
//tag::clazz[]
@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final TokenConfiguration tokenConfiguration;

    public AuthenticationProviderUserPassword(TokenConfiguration tokenConfiguration) {
        this.tokenConfiguration = tokenConfiguration;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flux.create({emitter ->
            if (authenticationRequest.getIdentity().equals("user") && authenticationRequest.getSecret().equals("password")) {
                emitter.next(AuthenticationResponse.build("user", ["ROLE_USER"], tokenConfiguration));
                emitter.complete();
            } else {
                emitter.error(new AuthenticationException(new AuthenticationFailed()));
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
//end::clazz[]
