package board.board.controller;

import board.board.domain.User;
import board.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/join")
    public String createForm(){
        return "users/createUser";
    }

    @PostMapping("/join")
    public String create(UserForm form, Model model){
        User user = new User();
        user.setId(form.getId());
        user.setPassword(form.getPassword());

        try{
            userService.join(user);
            return "redirect:/login";
        } catch (IllegalStateException e){
            model.addAttribute("error",e.getMessage());
            return "users/createUser";
        }
    }

    @GetMapping("/login")
    public String loginForm(){
        return "users/login";
    }

    @PostMapping("/login")
    public String authenticate(UserForm form, Model model){
        User user = new User();
        user.setId(form.getId());
        user.setPassword(form.getPassword());

        boolean ck = userService.login(user);
        if(ck){
            return "redirect:/board";
        } else{
            model.addAttribute("error","아이디 또는 비밀번호가 일치하지 않습니다.");
            return "users/login";
        }
    }
}
