package net.gaelixinfo.Journal.App.service;

import net.gaelixinfo.Journal.App.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        User.builder().username("rabby").password("rabby").build(),
                        User.builder().username("shyam").password("").build()
                )

        );
    }
}
