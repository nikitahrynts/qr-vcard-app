package by.nces.qr_vcard_app.service;

import by.nces.qr_vcard_app.model.Employee;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailSenderService {

    @Value("${app.qr.path}")
    private String PATH;

    private final JavaMailSender javaMailSender;

    private final EmployeeQRService employeeQRService;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender, EmployeeQRService employeeQRService) {
        this.javaMailSender = javaMailSender;
        this.employeeQRService = employeeQRService;
    }

    public void sendEmailWithAttachment(String subject, String body, Employee employee) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(employee.getEmail());
        helper.setSubject(subject);
        helper.setText(body);

        FileSystemResource file = new FileSystemResource(new File(PATH + employee.getFirstName() + "_" + employee.getLastName() + ".png"));
        helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
        javaMailSender.send(mimeMessage);
        employeeQRService.deleteContents(new File(PATH));
    }
}
