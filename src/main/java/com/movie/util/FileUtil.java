package com.movie.util;

import com.movie.config.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileUtil {

    private final String uploadDir;

    @Autowired
    public FileUtil(FileProperties fileProperties) {
        this.uploadDir = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize().toString();
        
        // 디렉토리 유효성 검증
        if (!Files.isDirectory(Paths.get(uploadDir))) {
            throw new IllegalArgumentException("업로드 디렉토리를 생성할 수 없음: " + uploadDir);
        }
    }

    // 파일 저장
    public String saveFile(String fileName, byte[] fileData) throws IOException {
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent()); // 디렉토리 생성
        Files.write(filePath, fileData); // 파일 저장
        return filePath.toString();
    }

    // 파일 목록 조회
    public List<String> listFiles() throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(uploadDir), 1)) {
            return paths.filter(Files::isRegularFile) // 정규 파일만 필터링
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("파일 목록 조회 오류 업로드 경로: " + uploadDir, e);
        }
    }

    // 파일 삭제
    public boolean deleteFile(String fileName) {
        File file = new File(Paths.get(uploadDir, fileName).toString());
        return file.exists() && file.delete();
    }
}
