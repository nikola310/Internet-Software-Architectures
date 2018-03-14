/**
 * 
 */
package com.management.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController{
	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    public String getErrorPath() {
        return PATH;
    }
}
