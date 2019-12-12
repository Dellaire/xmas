package net.sprd.xmas.logic;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class CallbackBroker {

    private Consumer<String> callback;

    public void callback() {

        if (this.callback != null) {
            this.callback.accept("");
        }
    }

    public void register(Consumer<String> callback) {

        this.callback = callback;
    }
}
