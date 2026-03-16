package com.example.phamchiloc.content;

import com.example.phamchiloc.content.persistence.SiteContentEntity;
import com.example.phamchiloc.content.persistence.SiteContentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SiteContentService {

    private static final long CONTENT_ID = 1L;

    private final ObjectMapper objectMapper;
    private final SiteContentRepository repository;
    private final AtomicReference<SiteContent> cached = new AtomicReference<>();

    public SiteContentService(ObjectMapper objectMapper, SiteContentRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        SiteContent loaded = loadOrCreate();
        cached.set(loaded);
    }

    public SiteContent getContent() {
        // Always refresh from DB (so what you edit in /admin is what you see).
        SiteContent loaded = loadOrCreate();
        cached.set(loaded);
        return loaded;
    }

    public synchronized void save(SiteContent content) {
        if (content == null) {
            throw new IllegalArgumentException("content must not be null");
        }
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(content);
            SiteContentEntity entity = new SiteContentEntity(CONTENT_ID, json);
            repository.save(entity);
            cached.set(content);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to save content to database", e);
        }
    }

    private synchronized SiteContent loadOrCreate() {
        return repository.findById(CONTENT_ID)
                .map(SiteContentEntity::getContentJson)
                .flatMap(json -> {
                    try {
                        return java.util.Optional.ofNullable(objectMapper.readValue(json, SiteContent.class));
                    } catch (Exception ignored) {
                        return java.util.Optional.empty();
                    }
                })
                .orElseGet(() -> {
                    SiteContent defaults = defaultContent();
                    save(defaults);
                    return defaults;
                });
    }

    private static SiteContent defaultContent() {
        SiteContent c = new SiteContent();
        c.setName("Phạm Chí Lộc");
        c.setTagline("Backend Developer");
        c.setRoleLine("Sinh viên Công nghệ Phần mềm · HUTECH K22");

        c.setDob("06/02/2004");
        c.setGender("Nam");
        c.setHometown("TP. Hồ Chí Minh");
        c.setUniversity("Đại học Công nghệ TP.HCM (HUTECH)");
        c.setMajor("Công nghệ Phần mềm");
        c.setCourse("K22");

        c.setEmail("phamloc629@gmail.com");
        c.setPhone("0832090304");
        c.setAddress("TP. Hồ Chí Minh, Việt Nam");

        c.setGithub("github.com/phamchiloc");
        c.setFacebook("facebook.com/phamchiloc");

        c.setGoal("Trở thành một Backend Developer chuyên nghiệp, có khả năng xây dựng các hệ thống web hiệu năng cao, bảo mật và dễ bảo trì. Tôi muốn đóng góp vào các dự án thực tế và không ngừng học hỏi công nghệ mới.");

        c.setHobbies(List.of(
                "Lập trình và xây dựng các dự án cá nhân",
                "Tìm hiểu công nghệ mới (Cloud, Microservices)",
                "Đọc sách kỹ thuật và xem tutorial",
                "Chơi thể thao"
        ));

        c.setProjects(List.of(
                "Website bán quần áo bóng đá - Spring Boot + MySQL",
                "Hệ thống quản lý sinh viên - Java + SQL Server",
                "Web mã hóa thuật toán - Python Flask"
        ));

        c.setSkills(List.of(
                new Skill(
                        "Java Spring Boot",
                        80,
                        "Xây dựng REST API, MVC web app, tích hợp Database với JPA/Hibernate, bảo mật với Spring Security."
                ),
                new Skill(
                        "HTML / CSS / JavaScript",
                        85,
                        "Thiết kế giao diện web responsive, hiệu ứng CSS, xử lý sự kiện DOM với JavaScript."
                ),
                new Skill(
                        "PHP Laravel",
                        70,
                        "Phát triển ứng dụng web MVC, Eloquent ORM, Blade template, RESTful routing."
                ),
                new Skill(
                        "MySQL / SQL Server",
                        75,
                        "Thiết kế database, viết truy vấn SQL phức tạp, tối ưu hóa index và join."
                ),
                new Skill(
                        "Docker",
                        65,
                        "Đóng gói ứng dụng thành container, viết Dockerfile, dùng Docker Compose để quản lý multi-service."
                ),
                new Skill(
                        "Git / GitHub",
                        80,
                        "Quản lý source code, làm việc với branch, pull request, merge conflict trong dự án nhóm."
                )
        ));

        return c;
    }
}
