package by.nces.qr_vcard_app.controller;

import by.nces.qr_vcard_app.model.Employee;
import by.nces.qr_vcard_app.service.EmailSenderService;
import by.nces.qr_vcard_app.service.EmployeeQRService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    private final EmployeeQRService employeeQRService;

    private final EmailSenderService emailSenderService;

    @Autowired
    public EmployeeController(EmployeeQRService employeeQRService, EmailSenderService emailSenderService) {
        this.employeeQRService = employeeQRService;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/")
    public String showLandingPage() {
        return "home";
    }

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "form";
    }

    @PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult bindingResult) throws MessagingException {
        if (bindingResult.hasFieldErrors()) {
            return "form";
        } else {
            String subject = "QR код";
            String body = "Ваш QR код находится во вложении";
            employeeQRService.createVCard(employee);
            emailSenderService.sendEmailWithAttachment(subject, body, employee);
            return "success";
        }
    }
}
