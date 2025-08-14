package com.emranhss.project.service;

import com.emranhss.project.dto.AuthenticationResponse;
import com.emranhss.project.entity.*;
import com.emranhss.project.jwt.JwtService;
import com.emranhss.project.repository.IEmployerRepository;
import com.emranhss.project.repository.ITokenRepository;
import com.emranhss.project.repository.IUserRepo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private IEmployerRepository employerRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;


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

        String activationLink="http://localhost:8085/api/user/active/"+user.getId();

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
                + "      <p>To Activate Your Account, please click the following link:</p>"
                + "      <p><a href=\"" + activationLink + "\">Activate Account</a></p>"
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

        String jobSeekerName = jobSeeker.getName();
        String fileName = jobSeekerName.trim().replaceAll("\\s+", "_");

        String savedFileName = fileName + "_" + UUID.randomUUID().toString();

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
            // Save image for both User and JobSeeker
            String filename = saveImage(imageFile, user);
            String jobSeekerPhoto = saveImageForJobSeeker(imageFile, jobSeekerData);
            jobSeekerData.setPhoto(jobSeekerPhoto);
            user.setPhoto(filename);
        }

        // Encode password before saving User
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.JOBSEEKER);
        user.setActive(false);

        // Save User FIRST and get persisted instance
        User savedUser = userRepo.save(user);

        // Now, associate saved User with JobSeeker and save JobSeeker
        jobSeekerData.setUser(savedUser);
        jobSeekerService.save(jobSeekerData);

        // Now generate token and save Token associated with savedUser
        String jwt = jwtService.generateToken(savedUser);
        saveUserToken(jwt, savedUser);

        // Send Activation Email
        sendActivationEmail(savedUser);
    }



    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLogout(false);
        token.setUser(user);

        tokenRepository.save(token);

    }

    private void removeAllTokenByUser(User user) {

        List<Token> validTokens = tokenRepository.findAllTokenByUser(user.getId());

        if (validTokens.isEmpty()) {
            return;
        }
        validTokens.forEach(t -> {
            t.setLogout(true);
        });

        tokenRepository.saveAll(validTokens);

    }


    // It is Login Method
    public AuthenticationResponse authenticate(User request) {
        // Authenticate Username & Password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Fetch User from DB
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check Activation Status
        if (!user.isActive()) {
            throw new RuntimeException("Account is not activated. Please check your email for activation link.");
        }

        // Generate JWT Token
        String jwt = jwtService.generateToken(user);

        // Remove Existing Tokens (Invalidate Old Sessions)
        removeAllTokenByUser(user);

        // Save New Token to DB (Optional if stateless)
        saveUserToken(jwt, user);

        // Return Authentication Response
        return new AuthenticationResponse(jwt, "User Login Successful");
    }



    public  String activeUser(int id){

        User user=userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("User not Found with this ID "+id));

        if(user !=null){
            user.setActive(true);

            userRepo.save(user);
            return "User Activated Successfully!";

        }else {
            return  "Invalid Activation Token!";
        }

    }



    // for User folder
    public String saveImageForEmployeer(MultipartFile file, Employer employer) {

        Path uploadPath = Paths.get(uploadDir + "/employer");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String employerName = employer.getCompanyName();
        String fileName = employerName.trim().replaceAll("\\s+", "_");

        String savedFileName = fileName + "_" + UUID.randomUUID().toString();

        try {
            Path filePath = uploadPath.resolve(savedFileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedFileName;

    }


    public void registerEmployer(User user, MultipartFile imageFile, Employer employerData) {
        if (imageFile != null && !imageFile.isEmpty()) {
            // Save image for both User and JobSeeker
            String filename = saveImage(imageFile, user);
            String employerPhoto = saveImageForEmployeer(imageFile, employerData);
            employerData.setLogo(employerPhoto);
            user.setPhoto(filename);
        }

        // Encode password before saving User
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.EMPLOYER);
        user.setActive(false);

        // Save User FIRST and get persisted instance
        User savedUser = userRepo.save(user);

        // Now, associate saved User with JobSeeker and save JobSeeker
        employerData.setUser(savedUser);
        employerRepository.save(employerData);

        // Now generate token and save Token associated with savedUser
        String jwt = jwtService.generateToken(savedUser);
        saveUserToken(jwt, savedUser);

        // Send Activation Email
        sendActivationEmail(savedUser);
    }



}
