package com.movie.util;

import com.movie.config.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileUtil {

    private final String posterUploadDir = Paths.get("C:", "upload", "poster").toString();
    private final String eventUploadDir = Paths.get("C:", "upload", "event").toString();

    private String getUploadDir() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);

        return str.replace("-", File.separator);

    }

    // 파일 저장
    public String saveFile(MultipartFile file, boolean isPoster) throws IOException {

        String baseDir = isPoster ? posterUploadDir : eventUploadDir;

        String uploadFolderPath = getUploadDir();

        File uploadPath = new File(baseDir, uploadFolderPath);

        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFilename = "movie_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + fileExtension;

        File saveFile = new File(uploadPath, uniqueFilename);

        file.transferTo(saveFile);

        return Paths.get(uploadFolderPath, uniqueFilename).toString();
    }

/*    @Autowired
    public FileUtil(FileProperties fileProperties) {
        this.uploadDir = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize().toString();
        
        // 디렉토리 유효성 검증
        if (!Files.isDirectory(Paths.get(uploadDir))) {
            throw new IllegalArgumentException("업로드 디렉토리를 생성할 수 없음: " + uploadDir);
        }
    }*/



/*    // 파일 목록 조회
    public List<String> listFiles() throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(uploadDir), 1)) {
            return paths.filter(Files::isRegularFile) // 정규 파일만 필터링
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("파일 목록 조회 오류 업로드 경로: " + uploadDir, e);
        }
    }*/

/*    // 파일 삭제
    public boolean deleteFile(String fileName) {
        File file = new File(Paths.get(uploadDir, fileName).toString());
        return file.exists() && file.delete();
    }*/
}
