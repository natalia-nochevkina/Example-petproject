package com.natanight.petproject.security;

public class ApiPaths {
    public static final String[] PUBLIC = {"/auth/**", "/", "/index"};
    public static final String[] AUTHENTICATED = {"/users/**"};
}
