package com.example.upload.game.json.controller;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/quiz")
@CrossOrigin("http://localhost:3000/")
public class GenerateController {
    private final String[] images = {"apple.png", "banana.png", "orange.png"};

    @GetMapping("/quiz")
    public ResponseEntity<String> getQuiz() throws Exception {
        // Step 1: Generate a random image from the available images
        String questionImage = images[ThreadLocalRandom.current().nextInt(images.length)];
//        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/" + questionImage));
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", baos);
//        byte[] imageData = baos.toByteArray();
//        String base64Image = Base64.getEncoder().encodeToString(imageData);
        System.out.println(questionImage);
        // Step 2: Create a Quiz object with the data
//        Quiz quiz = new Quiz();
//        quiz.setCode("G-002");
//        quiz.setType(new QuizType(2L, "Counting"));
//        quiz.setInstruction(new QuizInstruction("Please count the picture", "voice.mp3", "video.mp4", "image.png"));
//        quiz.setQuestion(new QuizQuestion("How many apples are there?", "voice.mp3", "video.mp4", base64Image));
//        quiz.setAnswer(new QuizAnswer(6));

        // Step 3: Return the Quiz object in the response body
        return new ResponseEntity<>(questionImage,HttpStatus.OK);
    }
}
