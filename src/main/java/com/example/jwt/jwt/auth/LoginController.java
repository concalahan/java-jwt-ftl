package com.example.jwt.jwt.auth;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jwt.jwt.model.User;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    private static final Map<String, String> credentials = new HashMap<>();

    public LoginController() {
        credentials.put("hellokoding", "hellokoding");
        credentials.put("hellosso", "hellosso");
    }

    @RequestMapping("/")
    public String home(){
    	System.out.println("vao day");
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(){
    	System.out.println("day nua");
        return "login";
    }
    
    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    //@RequestMapping(value = "login", method = RequestMethod.POST)
    //     public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){
    @RequestMapping(value = "login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String login(HttpServletResponse httpServletResponse,@RequestBody User user){   	
    	String username = user.getUsername();
    	String password = user.getPassword();
    	
    	System.out.println("username " + username);
    	System.out.println("password " + password);
        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
            //model.addAttribute("error", "Invalid username or password!");
        	System.out.println("vao cmnr");
            return "cmnr";
        }

        System.out.println("httpServletResponse " + httpServletResponse);
        
        String token = JwtUtil.generateToken(signingKey, username);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
        
        System.out.println("token " + token);
        
        return "abc";
//        return "redirect:" + redirect;
    }
    
    @RequestMapping("/protected-resource")
    public String protectedResource() {
        return "protected-resource";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse) {
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return "hello";
    }
}
