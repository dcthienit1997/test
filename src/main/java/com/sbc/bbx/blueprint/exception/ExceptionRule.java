package com.sbc.bbx.blueprint.exception;

import org.springframework.http.HttpStatus;

public record ExceptionRule(Class<?> exceptionClass, HttpStatus status) {}
