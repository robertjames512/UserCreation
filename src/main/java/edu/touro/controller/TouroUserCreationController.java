package edu.touro.controller;

import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.touro.util.DataCollection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class TouroUserCreationController {
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://Users//rjames4//Documents//UserCreation//"; 

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    //@RequestMapping(value = "/upload", method = RequestMethod.POST)
   
    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
    	
    	DataCollection readFile = new DataCollection(); 
    	StringBuffer message = new StringBuffer();

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            /*redirectAttributes.addFlashAttribute("message", 
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");*/
            
            message = readFile.collectID(file.getOriginalFilename(), UPLOADED_FOLDER);
            
            redirectAttributes.addFlashAttribute("message", message.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }


}
