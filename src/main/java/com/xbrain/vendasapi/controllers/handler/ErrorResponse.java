package com.xbrain.vendasapi.controllers.handler;

import java.util.Map;

public record ErrorResponse (
        String userMessage,
        String devMessage,
        Map<String, String> errors
) {

}
