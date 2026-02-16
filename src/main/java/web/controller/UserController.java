package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Главная страница со списком всех пользователей
    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    // Показать форму для добавления нового пользователя
    @GetMapping("/add")
    public String showAddForm() {
        return "user-form";
    }

    // Добавить пользователя (POST)
    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("email") String email,
                          @RequestParam("age") int age) {
        User user = new User(name, email, age);
        userService.add(user);
        return "redirect:/";
    }

    // Показать форму для редактирования пользователя (используем RequestParam)
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    // Обновить пользователя (POST)
    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("age") int age) {
        User user = new User(name, email, age);
        user.setId(id);
        userService.update(user);
        return "redirect:/";
    }

    // Удалить пользователя (POST - для безопасности)
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}