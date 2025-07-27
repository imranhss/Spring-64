package com.emranhss.project.service;

import com.emranhss.project.entity.JobSeeker;
import com.emranhss.project.entity.Role;
import com.emranhss.project.repository.IUserRepo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emranhss.project.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JobSeekerService jobSeekerService;


    @Value("src/main/resources/static/images")
    private String uploadDir;


    public void saveOrUpdate(User user, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = saveImage(imageFile, user);
            user.setPhoto(filename);
        }

        user.setRole(Role.ADMIN);
        userRepo.save(user);
        sendActivationEmail(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(int id) {
        return userRepo.findById(id).get();
    }

    public void delete(User user) {
        userRepo.delete(user);
    }


    private void sendActivationEmail(User user) {
        String subject = "Welcome to Our Service – Confirm Your Registration";

        String mailText = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "  body { font-family: Arial, sans-serif; line-height: 1.6; }"
                + "  .container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; }"
                + "  .header { background-color: #4CAF50; color: white; padding: 10px; text-align: center; border-radius: 10px 10px 0 0; }"
                + "  .content { padding: 20px; }"
                + "  .footer { font-size: 0.9em; color: #777; margin-top: 20px; text-align: center; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "  <div class='container'>"
                + "    <div class='header'>"
                + "      <h2>Welcome to Our Platform</h2>"
                + "    </div>"
                + "    <div class='content'>"
                + "      <p>Dear " + user.getName() + ",</p>"
                + "      <p>Thank you for registering with us. We are excited to have you on board!</p>"
                + "      <p>Please confirm your email address to activate your account and get started.</p>"
                + "      <p>If you have any questions or need help, feel free to reach out to our support team.</p>"
                + "      <br>"
                + "      <p>Best regards,<br>The Support Team</p>"
                + "    </div>"
                + "    <div class='footer'>"
                + "      &copy; " + java.time.Year.now() + " YourCompany. All rights reserved."
                + "    </div>"
                + "  </div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendSimpleEmail(user.getEmail(), subject, mailText);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send activation email", e);
        }
    }


    // for User folder
    public String saveImage(MultipartFile file, User user) {

        Path uploadPath = Paths.get(uploadDir + "/users");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String fileName = user.getName() + "_" + UUID.randomUUID().toString();


        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;

    }

    // for User folder
    public String saveImageForJobSeeker(MultipartFile file, JobSeeker jobSeeker) {

        Path uploadPath = Paths.get(uploadDir + "/jobSeeker");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String jobSeekerName = jobSeeker.getName() ;
        String fileName = jobSeekerName.trim().replaceAll("\\s+", "_") ;

        String savedFileName = fileName+ "_" + UUID.randomUUID().toString();

        try {
            Path filePath = uploadPath.resolve(savedFileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedFileName;

    }


    public void registerJobSeeker(User user, MultipartFile imageFile, JobSeeker jobSeekerData) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = saveImage(imageFile, user);
            String jobSeekerPhoto = saveImageForJobSeeker(imageFile, jobSeekerData);
            jobSeekerData.setPhoto(jobSeekerPhoto);
            user.setPhoto(filename);
        }

        user.setRole(Role.JOBSEEKER);
        User savedUser = userRepo.save(user); // Save User first

        // Set user to jobSeeker and save it
        jobSeekerData.setUser(savedUser);

        jobSeekerService.save(jobSeekerData);

        sendActivationEmail(savedUser);
    }


}
